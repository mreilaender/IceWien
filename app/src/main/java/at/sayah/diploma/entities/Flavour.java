package at.sayah.diploma.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Entity Bean representing the table flavour in the database
 * @author mreilaender
 */
@DatabaseTable(tableName = "flavour")
public class Flavour {

    public static final String FLAVOUR_ID_FIELD_NAME = "flavour_id";
    public static final String NAME_FIELD_NAME = "name";
    public static final String CONFIRMED_FIELD_NAME = "confirmed";

    @DatabaseField(columnName = FLAVOUR_ID_FIELD_NAME, id = true)
    private int flavour_id;

    @DatabaseField(columnName = NAME_FIELD_NAME, uniqueCombo = true)
    private String name;

    @DatabaseField(columnName = CONFIRMED_FIELD_NAME)
    private boolean confirmed;

    @DatabaseField(columnName = Parlor.PARLOR_ID_FIELD_NAME, foreign = true, foreignColumnName = Parlor.PARLOR_ID_FIELD_NAME, uniqueCombo = true)
    private Parlor parlor_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Parlor getParlor_id() {
        return parlor_id;
    }

    public void setParlor_id(Parlor parlor_id) {
        this.parlor_id = parlor_id;
    }

    public int getFlavour_id() {
        return flavour_id;
    }

    public void setFlavour_id(int flavour_id) {
        this.flavour_id = flavour_id;
    }
}
