package at.sayah.diploma.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * @author mreilaender
 */
@DatabaseTable(tableName = "vote")
public class Vote {
    public final static String DATE_FIELD_NAME = "date";
    public final static String RANKING_FIELD_NAME = "ranking";
    public final static String FLAVOUR_FIELD_NAME = "flavour";
    public final static String UID_FIELD_NAME = "uid";
    public final static String PARLOR_ID_FIELD_NAME = "parlor_id";

    @DatabaseField(columnName = DATE_FIELD_NAME)
    private Date date;

    @DatabaseField(columnName = RANKING_FIELD_NAME)
    private int ranking;

    @DatabaseField(columnName = FLAVOUR_FIELD_NAME)
    private String flavour;

    @DatabaseField(columnName = UID_FIELD_NAME)
    private int uid;

    @DatabaseField(columnName = PARLOR_ID_FIELD_NAME)
    private int parlor_id;

    public Vote() {
    }

    public Vote(Date date, int ranking, String flavour, int uid, int parlor_id) {
        this.date = date;
        this.ranking = ranking;
        this.flavour = flavour;
        this.uid = uid;
        this.parlor_id = parlor_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getParlor_id() {
        return parlor_id;
    }

    public void setParlor_id(int parlor_id) {
        this.parlor_id = parlor_id;
    }
}
