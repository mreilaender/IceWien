package at.sayah.diploma.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Entity Bean representing the table vote in the database
 * @author mreilaender
 */
@DatabaseTable(tableName = "vote")
public class Vote {
    public final static String DATE_FIELD_NAME = "date";
    public final static String RANKING_FIELD_NAME = "ranking";
    public static final String UID_FIELD_NAME = "uid";
    public static final String FLAVOUR_ID_FIELD_NAME = "flavour_id";

    @DatabaseField(columnName = DATE_FIELD_NAME, id = true)
    private Date date;

    @DatabaseField(columnName = RANKING_FIELD_NAME)
    private int ranking;

    @DatabaseField(columnName = FLAVOUR_ID_FIELD_NAME, foreign = true, foreignColumnName = Flavour.FLAVOUR_ID_FIELD_NAME)
    private Flavour flavour;

    @DatabaseField(columnName = UID_FIELD_NAME, foreign = true, foreignColumnName = User.UID_FIELD_NAME)
    private User uid;

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

    public Flavour getFlavour() {
        return flavour;
    }

    public void setFlavour(Flavour flavour) {
        this.flavour = flavour;
    }

    public User getUid() {
        return uid;
    }

    public void setUid(User uid) {
        this.uid = uid;
    }

}
