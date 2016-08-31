package at.sayah.diploma;

import android.content.Context;
import android.content.SharedPreferences;
import at.sayah.diploma.entities.Parlor;
import com.google.gson.Gson;

/**
 * @author mreilaender
 */
public class ParlorSessionManager {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    
    public static final String PREF_NAME = "UserSession";
    public static final String PARLOR_JSON = "parlorJson";

    public ParlorSessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, 0);
        editor = preferences.edit();
        editor.apply();
    }

    public void createParlorSession(Parlor parlor) {
        editor.putString(PARLOR_JSON, new Gson().toJson(parlor, Parlor.class));
        editor.apply();
    }

    public Parlor getParlor() {
        return new Gson().fromJson(preferences.getString(PARLOR_JSON, null), Parlor.class);
    }
}
