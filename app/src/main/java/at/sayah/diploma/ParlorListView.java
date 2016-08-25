package at.sayah.diploma;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import at.sayah.diploma.entities.Parlor;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParlorListView extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "ParlorListView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parlor_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView parlor_list = (ListView) findViewById(R.id.list_parlor);
        try {
            List<Parlor> parlors = new ParlorAsync().execute().get();
            ParlorAdapter parlorAdapter = new ParlorAdapter(this, android.R.layout.simple_list_item_1, parlors);
            parlor_list.setAdapter(parlorAdapter);
            parlor_list.setOnItemClickListener(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    public void showPopup(MenuItem item) {
        View view = findViewById(item.getItemId());
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.settings_sub, popupMenu.getMenu());
        popupMenu.show();
    }

    public void doLogin(MenuItem item) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ArrayAdapter<Parlor> adapter = (ArrayAdapter<Parlor>) adapterView.getAdapter();
        Parlor parlor = adapter.getItem(position);
        String json = new Gson().toJson(parlor);
        Intent intent = new Intent(this, ParlorDetailsView.class);
        intent.putExtra("parlor", json);
        startActivity(intent);
    }
}