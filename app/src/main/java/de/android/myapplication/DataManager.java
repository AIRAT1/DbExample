package de.android.myapplication;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataManager {
    private SQLiteDatabase db;

    public final static String TABLE_ROW_ID = "_id";
    public final static String TABLE_ROW_TITLE = "image_title";
    public final static String TABLE_ROW_URI = "image_uri";

    private final static String DB_NAME = "wis_db";
    private final static int DB_VERSION = 1;
    private final static String TABLE_PHOTOS = "wis_table_photos";
    private final static String TABLE_TAGS = "wis_table_tags";
    private final static String TABLE_ROW_TAG1 = "tag1";
    private final static String TABLE_ROW_TAG2 = "tag2";
    private final static String TABLE_ROW_TAG3 = "tag3";
    private final static String TABLE_ROW_TAG = "tag";

    public DataManager(Context context) {
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public void addPhoto(Photo photo) {
//        Log.d("addPhoto", photo.getTitle());
//        Log.d("addPhoto", String.valueOf(photo.getStorageLocation()));
//        Log.d("addPhoto", photo.getTag1());
//        Log.d("addPhoto", photo.getTag2());
//        Log.d("addPhoto", photo.getTag3());
        String query = "INSERT INTO " + TABLE_PHOTOS + " (" +
                TABLE_ROW_TITLE + ", " +
                TABLE_ROW_URI + ", " +
                TABLE_ROW_TAG1 + ", " +
                TABLE_ROW_TAG2 + ", " +
                TABLE_ROW_TAG3 + ") " +
                "VALUES (" +
                "'" + photo.getTitle() + "'" + ", " +
                "'" + photo.getStorageLocation() + "'" + ", " +
                "'" + photo.getTag1() + "'" + ", " +
                "'" + photo.getTag2() + "'" + ", " +
                "'" + photo.getTag3() + "'" + ");";
        Log.i("addPhoto() = ", query);
        db.execSQL(query);

        query = "INSERT INTO " + TABLE_TAGS + " (" +
                TABLE_ROW_TAG + ") " +
                "SELECT '" + photo.getTag1() + "' " +
                "WHERE NOT EXISTS (SELECT 1 FROM " +
                TABLE_TAGS +
                " WHERE " + TABLE_ROW_TAG + " = " +
                "'" + photo.getTag1() + "'" + ");";
        Log.i("addPhoto() = ", query);
        db.execSQL(query);

        query = "INSERT INTO " + TABLE_TAGS + " (" +
                TABLE_ROW_TAG + ") " +
                "SELECT '" + photo.getTag2() + "' " +
                "WHERE NOT EXISTS (SELECT 1 FROM " +
                TABLE_TAGS +
                " WHERE " + TABLE_ROW_TAG + " = " +
                "'" + photo.getTag2() + "'" + ");";
        Log.i("addPhoto() = ", query);
        db.execSQL(query);

        query = "INSERT INTO " + TABLE_TAGS + " (" +
                TABLE_ROW_TAG + ") " +
                "SELECT '" + photo.getTag3() + "' " +
                "WHERE NOT EXISTS (SELECT 1 FROM " +
                TABLE_TAGS +
                " WHERE " + TABLE_ROW_TAG + " = " +
                "'" + photo.getTag3() + "'" + ");";
        Log.i("addPhoto() = ", query);
        db.execSQL(query);
    }

    public Cursor getTitles() {
        Cursor c = db.rawQuery("SELECT " + TABLE_ROW_ID + ", " + TABLE_ROW_TITLE +
        " from " + TABLE_PHOTOS, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getTitlesWithTag(String tag) {
        Cursor c = db.rawQuery("SELECT " + TABLE_ROW_ID + ", " +
        TABLE_ROW_TITLE + " FROM " +
        TABLE_PHOTOS + " WHERE " +
        TABLE_ROW_TAG1 + " = " + "'" + tag + "'" + " or " +
        TABLE_ROW_TAG2 + " = " + "'" + tag + "'" + " or " +
        TABLE_ROW_TAG3 + " = " + "'" + tag + "'" + ";", null);
        c.moveToFirst();
        return c;
    }

    public Cursor getPhoto(int id) {
        Cursor c = db.rawQuery("SELECT * FROM " +
        TABLE_PHOTOS + " WHERE " +
        TABLE_ROW_ID + " = " + id, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getTags() {
        Cursor c = db.rawQuery("SELECT " + TABLE_ROW_ID + ", " + TABLE_ROW_TAG +
        " FROM " + TABLE_TAGS, null);
        c.moveToFirst();
        return c;
    }

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String newTableQueryString = "CREATE TABLE " +
                    TABLE_PHOTOS + " (" +
                    TABLE_ROW_ID + " integer primary key autoincrement, " +
                    TABLE_ROW_TITLE + " text not null, " +
                    TABLE_ROW_URI + " text not null, " +
                    TABLE_ROW_TAG1 + " text not null, " +
                    TABLE_ROW_TAG2 + " text not null, " +
                    TABLE_ROW_TAG3 + " text not null);";
            db.execSQL(newTableQueryString);
            Log.d("onCreate", "Complete PHOTOS");

            newTableQueryString = "CREATE TABLE " +
                    TABLE_TAGS + " (" +
                    TABLE_ROW_ID + " integer primary key autoincrement, " +
                    TABLE_ROW_TAG + " text not null);";
            db.execSQL(newTableQueryString);
            Log.d("onCreate", "Complete TAGS");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTOS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAGS);
            onCreate(db);
        }
    }
}
