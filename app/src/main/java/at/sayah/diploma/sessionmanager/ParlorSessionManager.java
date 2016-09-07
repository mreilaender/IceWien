package at.sayah.diploma.sessionmanager;

import android.content.Context;
import android.content.SharedPreferences;
import at.sayah.diploma.entities.Parlor;
import com.google.gson.Gson;

/**
 * Saves a Parlor instance in an android shared preference as JSON
 * @author mreilaender
 */
public class ParlorSessionManager {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    // Name of the preference
    public static final String PREF_NAME = "UserSession";

    // Name of the parlor preference
    public static final String PARLOR_JSON = "parlorJson";

    public ParlorSessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, 0);
        editor = preferences.edit();
        editor.apply();
    }

    /**
     * Saves a given Parlor as JSON in a shared preference using PARLOR_JSON as it's name
     * @param parlor Parlor to save as JSON
     */
    public void createParlorSession(Parlor parlor) {
        editor.putString(PARLOR_JSON, new Gson().toJson(parlor, Parlor.class));
        editor.apply();
    }

    /**
     * Returns the saved parlor instance
     * @return null if no parlor has been saved or the parlor instance
     */
    public Parlor getParlor() {
        return new Gson().fromJson(preferences.getString(PARLOR_JSON, null), Parlor.class);
    }
}
