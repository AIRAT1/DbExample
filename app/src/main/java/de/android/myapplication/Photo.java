package de.android.myapplication;


import android.net.Uri;

public class Photo {
    private String title, tag1, tag2, tag3;
    private Uri storageLocation;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public Uri getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(Uri storageLocation) {
        this.storageLocation = storageLocation;
    }
}
