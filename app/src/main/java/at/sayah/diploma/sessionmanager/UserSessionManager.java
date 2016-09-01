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

    public UserSessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, 0);
        editor = preferences.edit();
        editor.apply();
    }

    public void createLoginSession(User user) {
        editor.putBoolean(LOGGED_IN, true);
        editor.putString(USER_JSON, new Gson().toJson(user, User.class));
        editor.apply();
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(LOGGED_IN, false);
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }

    public User getUser() {
        return new Gson().fromJson(preferences.getString(USER_JSON, null), User.class);
    }
}
