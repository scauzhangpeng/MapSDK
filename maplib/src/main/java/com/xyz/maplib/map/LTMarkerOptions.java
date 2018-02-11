package com.xyz.maplib.map;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZP on 2018/2/9.
 */

public class LTMarkerOptions implements Parcelable {

    private int icon;

    private double longitude;

    private double latitude;

    private boolean draggable;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    private LTMarkerOptions(Builder builder) {
        icon = builder.icon;
        longitude = builder.longitude;
        latitude = builder.latitude;
        draggable = builder.draggable;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.icon);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeByte(this.draggable ? (byte) 1 : (byte) 0);
    }

    public LTMarkerOptions() {
    }

    protected LTMarkerOptions(Parcel in) {
        this.icon = in.readInt();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.draggable = in.readByte() != 0;
    }

    public static final Creator<LTMarkerOptions> CREATOR = new Creator<LTMarkerOptions>() {
        @Override
        public LTMarkerOptions createFromParcel(Parcel source) {
            return new LTMarkerOptions(source);
        }

        @Override
        public LTMarkerOptions[] newArray(int size) {
            return new LTMarkerOptions[size];
        }
    };

    public static final class Builder {
        private int icon;
        private double longitude;
        private double latitude;
        private boolean draggable;

        public Builder() {
        }

        public Builder(LTMarkerOptions copy) {
            this.icon = copy.icon;
            this.longitude = copy.longitude;
            this.latitude = copy.latitude;
            this.draggable = copy.draggable;
        }

        public Builder icon(int val) {
            icon = val;
            return this;
        }

        public Builder longitude(double val) {
            longitude = val;
            return this;
        }

        public Builder latitude(double val) {
            latitude = val;
            return this;
        }

        public Builder draggable(boolean val) {
            draggable = val;
            return this;
        }

        public LTMarkerOptions build() {
            return new LTMarkerOptions(this);
        }
    }
}
