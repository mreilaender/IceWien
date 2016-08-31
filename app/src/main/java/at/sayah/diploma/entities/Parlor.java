package at.sayah.diploma.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author mreilaender
 */
@DatabaseTable(tableName = "parlor")
public class Parlor {
    public static final String PARLOR_ID_FIELD_NAME = "parlor_id";
    public static final String NAME_FIELD_NAME = "name";
    public static final String LATITUDE_FIELD_NAME = "latitude";
    public static final String LONGITUDE_FIELD_NAME = "longitude";
    public static final String OPENING_TIME_FIELD_NAME = "opening_time";
    public static final String CLOSING_TIME_FIELD_NAME  = "closing_time";
    public static final String STREET_FIELD_NAME  = "street";
    public static final String STREET_NUMB_FIELD_NAME  = "street_numb";
    public static final String ZIP_FIELD_NAME  = "zip";
    public static final String PHONE_FIELD_NAME  = "phone";
    public static final String MENU_FIELD_NAME  = "menu";

    @DatabaseField(columnName = "parlor_id", id = true)
    private int parlor_id;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "latitude")
    private String latitude;

    @DatabaseField(columnName = "longitude")
    private String longitude;

    @DatabaseField(columnName = "opening_time")
    private String opening_time;

    @DatabaseField(columnName = "closing_time")
    private String closing_time;

    @DatabaseField(columnName = "street")
    private String street;

    @DatabaseField(columnName = "street_numb")
    private int street_number;

    @DatabaseField(columnName = "zip")
    private int zip;

    @DatabaseField(columnName = "phone")
    private String phone;

    @DatabaseField(columnName = "menu")
    private String menu;

    public Parlor() {
    }

    public Parlor(String name) {
        this.name = name;
    }

    public Parlor(String name, String latitude, String longitude, String opening_time, String closing_time, String street, int street_number, int zip, String phone, String menu) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.opening_time = opening_time;
        this.closing_time = closing_time;
        this.street = street;
        this.street_number = street_number;
        this.zip = zip;
        this.phone = phone;
        this.menu = menu;
    }

    public int getParlor_id() {
        return parlor_id;
    }

    public void setParlor_id(int parlor_id) {
        this.parlor_id = parlor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(String opening_time) {
        this.opening_time = opening_time;
    }

    public String getClosing_time() {
        return closing_time;
    }

    public void setClosing_time(String closing_time) {
        this.closing_time = closing_time;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreet_number() {
        return street_number;
    }

    public void setStreet_number(int street_number) {
        this.street_number = street_number;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

}
