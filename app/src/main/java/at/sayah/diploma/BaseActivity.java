package at.sayah.diploma;

import android.content.Intent;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                PopupMenu popupMenu = new PopupMenu(this, findViewById(item.getItemId()));
                popupMenu.setOnMenuItemClickListener(this);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.settings_sub, popupMenu.getMenu());
                popupMenu.show();
        }
        return false;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                Intent intent = new Intent(this, LoginView.class);
                startActivity(intent);
        }
        return false;
    }
}
