package com.example.jh.openeyes.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class Author implements Parcelable {

    public int id;
    public String icon;
    public String name;
    public String description;
    public int videoNum;

    protected Author(Parcel in) {
        id = in.readInt();
        icon = in.readString();
        name = in.readString();
        description = in.readString();
        videoNum = in.readInt();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(icon);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(videoNum);
    }
}
