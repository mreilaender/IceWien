package at.sayah.diploma.sessionmanager;

import android.content.Context;
import android.content.SharedPreferences;
import at.sayah.diploma.entities.User;
import com.google.gson.Gson;

/**
 * @author mreilaender
 */
public class UserSessionManager {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static final String PREF_NAME = "UserSession";
    public static final String LOGGED_IN = "isLoggedIn";
    public static final String USER_JSON = "userJson";

    /**
     * Constructor
     * @param context Used to create the SharedPreference
     */
    public UserSessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, 0);
        editor = preferences.edit();
        editor.apply();
    }

    /**
     * Stores the given user as JSON in the shared preference. In Addition it puts a boolean in the shared preference,
     * which can be used to determine whether the user is logged in or not.
     * @param user User to store
     */
    public void createLoginSession(User user) {
        editor.putBoolean(LOGGED_IN, true);
        editor.putString(USER_JSON, new Gson().toJson(user, User.class));
        editor.apply();
    }

    /**
     * Checks if there is a user saved in the shared preference (or if the user is logged in)
     * @return true when the user is logged in | false if not
     */
    public boolean isLoggedIn() {
        return preferences.getBoolean(LOGGED_IN, false);
    }

    /**
     * Clears all data saved in the shared preference
     */
    public void logout() {
        editor.clear();
        editor.apply();
    }

    /**
     * Get the user stored in the shared preference
     * @return Stored user
     */
    public User getUser() {
        return new Gson().fromJson(preferences.getString(USER_JSON, null), User.class);
    }
}
