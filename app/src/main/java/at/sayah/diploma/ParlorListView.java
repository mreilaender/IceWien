package at.sayah.diploma;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import at.sayah.diploma.adapter.ParlorAdapter;
import at.sayah.diploma.entities.Parlor;
import at.sayah.diploma.sessionmanager.ParlorSessionManager;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParlorListView extends BaseActivity implements AdapterView.OnItemClickListener {
    static final String TAG = "ParlorListView";
    static final String PACKAGE = "at.sayah.diploma";

    private ParlorSessionManager parlorSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parlor_list);

        parlorSessionManager = new ParlorSessionManager(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView parlor_list = (ListView) findViewById(R.id.list_parlor);
        try {
            List<Parlor> parlors = new ParlorAsync().execute().get();
            ParlorAdapter parlorAdapter = new ParlorAdapter(this, android.R.layout.simple_list_item_1, parlors);
            parlor_list.setAdapter(parlorAdapter);
            parlor_list.setOnItemClickListener(this);
        } catch (InterruptedException | ExecutionException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ArrayAdapter<Parlor> adapter = (ArrayAdapter<Parlor>) adapterView.getAdapter();
        Parlor parlor = adapter.getItem(position);
        parlorSessionManager.createParlorSession(parlor);
        Intent intent = new Intent(this, ParlorDetailsView.class);
        startActivity(intent);
    }
}