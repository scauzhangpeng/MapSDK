package com.xyz.maplib.location;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZP on 2018/1/15.
 */

public class MapLocation implements Parcelable, Cloneable {

    private double latitude;
    private double longitude;
    private String country;
    private String countryCode;
    private String province;
    private String city;
    private String cityCode;
    private String adCode;
    private String address;
    private String district;
    private String locationDescribe;
    private int operators;
    private float radius;
    private float speed;
    private String time;
    private String road;
    private String poiName;
    private String street;
    private String streetNum;
    private String aoiName;

    private boolean hasSpeed;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocationDescribe() {
        return locationDescribe;
    }

    public void setLocationDescribe(String locationDescribe) {
        this.locationDescribe = locationDescribe;
    }

    public int getOperators() {
        return operators;
    }

    public void setOperators(int operators) {
        this.operators = operators;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getAoiName() {
        return aoiName;
    }

    public void setAoiName(String aoiName) {
        this.aoiName = aoiName;
    }

    public boolean isHasSpeed() {
        return hasSpeed;
    }

    public void setHasSpeed(boolean hasSpeed) {
        this.hasSpeed = hasSpeed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.country);
        dest.writeString(this.countryCode);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.cityCode);
        dest.writeString(this.adCode);
        dest.writeString(this.address);
        dest.writeString(this.district);
        dest.writeString(this.locationDescribe);
        dest.writeInt(this.operators);
        dest.writeFloat(this.radius);
        dest.writeFloat(this.speed);
        dest.writeString(this.time);
        dest.writeString(this.road);
        dest.writeString(this.poiName);
        dest.writeString(this.street);
        dest.writeString(this.streetNum);
        dest.writeString(this.aoiName);
        dest.writeByte(this.hasSpeed ? (byte) 1 : (byte) 0);
    }

    public MapLocation() {
    }

    protected MapLocation(Parcel in) {
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.country = in.readString();
        this.countryCode = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.cityCode = in.readString();
        this.adCode = in.readString();
        this.address = in.readString();
        this.district = in.readString();
        this.locationDescribe = in.readString();
        this.operators = in.readInt();
        this.radius = in.readFloat();
        this.speed = in.readFloat();
        this.time = in.readString();
        this.road = in.readString();
        this.poiName = in.readString();
        this.street = in.readString();
        this.streetNum = in.readString();
        this.aoiName = in.readString();
        this.hasSpeed = in.readByte() != 0;
    }

    public static final Creator<MapLocation> CREATOR = new Creator<MapLocation>() {
        @Override
        public MapLocation createFromParcel(Parcel source) {
            return new MapLocation(source);
        }

        @Override
        public MapLocation[] newArray(int size) {
            return new MapLocation[size];
        }
    };

    @Override
    public String toString() {
        return "MapLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", adCode='" + adCode + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", locationDescribe='" + locationDescribe + '\'' +
                ", operators=" + operators +
                ", radius=" + radius +
                ", speed=" + speed +
                ", time='" + time + '\'' +
                ", road='" + road + '\'' +
                ", poiName='" + poiName + '\'' +
                ", street='" + street + '\'' +
                ", streetNum='" + streetNum + '\'' +
                ", aoiName='" + aoiName + '\'' +
                ", hasSpeed=" + hasSpeed +
                '}';
    }
}
