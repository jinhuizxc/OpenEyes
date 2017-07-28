package com.example.jh.openeyes.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class Cover implements Parcelable {

    public String feed;
    public String detail;
    public String blurred;

    protected Cover(Parcel in) {
        feed = in.readString();
        detail = in.readString();
        blurred = in.readString();
    }

    public static final Creator<Cover> CREATOR = new Creator<Cover>() {
        @Override
        public Cover createFromParcel(Parcel in) {
            return new Cover(in);
        }

        @Override
        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(feed);
        dest.writeString(detail);
        dest.writeString(blurred);
    }

}
