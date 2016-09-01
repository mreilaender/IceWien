package at.sayah.diploma.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author mreilaender
 */
@DatabaseTable(tableName = "user")
public class User {
    public static final String UID_FIELD_NAME = "uid";
    public static final String EMAIL_FIELD_NAME = "email";
    public static final String PASSWORD_FIELD_NAME = "password";
    public static final String ISADMIN_FIELD_NAME = "isAdmin";

    @DatabaseField(columnName = UID_FIELD_NAME, id = true)
    private int uid;

    @DatabaseField(columnName = EMAIL_FIELD_NAME)
    private String email;

    @DatabaseField(columnName = PASSWORD_FIELD_NAME)
    private String password;

    @DatabaseField(columnName = ISADMIN_FIELD_NAME)
    private int isAdmin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getUid() {
        return uid;
    }
}
