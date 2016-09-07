package at.sayah.diploma.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Entity Bean representing the table parlor in the database
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

    @DatabaseField(columnName = PARLOR_ID_FIELD_NAME, id = true)
    private int parlor_id;

    @DatabaseField(columnName = NAME_FIELD_NAME)
    private String name;

    @DatabaseField(columnName = LATITUDE_FIELD_NAME)
    private String latitude;

    @DatabaseField(columnName = LONGITUDE_FIELD_NAME)
    private String longitude;

    @DatabaseField(columnName = OPENING_TIME_FIELD_NAME)
    private String opening_time;

    @DatabaseField(columnName = CLOSING_TIME_FIELD_NAME)
    private String closing_time;

    @DatabaseField(columnName = STREET_FIELD_NAME)
    private String street;

    @DatabaseField(columnName = STREET_NUMB_FIELD_NAME)
    private int street_number;

    @DatabaseField(columnName = ZIP_FIELD_NAME)
    private int zip;

    @DatabaseField(columnName = PHONE_FIELD_NAME)
    private String phone;

    @DatabaseField(columnName = MENU_FIELD_NAME)
    private String menu;

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
