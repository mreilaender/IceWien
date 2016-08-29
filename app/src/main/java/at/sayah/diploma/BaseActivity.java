package at.sayah.diploma;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * @author mreilaender
 */
public class BaseActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    final static String TAG = "BaseActivity";
    private boolean loggedIn = false;
    private UserSessionManager userSessionManager;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        userSessionManager = new UserSessionManager(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings_sub:
                //Change popup menu when logged in
                PopupMenu popupMenu = new PopupMenu(this, findViewById(item.getItemId()));
                popupMenu.setOnMenuItemClickListener(this);
                MenuInflater inflater = popupMenu.getMenuInflater();
                int resId;
                if (userSessionManager.isLoggedIn())
                    resId = R.menu.settings_sub_logged_in;
                else
                    resId = R.menu.settings_sub;
                inflater.inflate(resId, popupMenu.getMenu());
                popupMenu.show();
                break;
        }
        return false;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                Intent intent = new Intent(this, LoginView.class);
                startActivity(intent);
                break;
            case R.id.action_logout:
                userSessionManager.logout();
                startActivity(new Intent(this, ParlorListView.class));
                break;
        }
        return false;
    }
}
