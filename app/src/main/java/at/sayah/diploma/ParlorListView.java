package at.sayah.diploma;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import at.sayah.diploma.entities.Parlor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParlorListView extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Cursor cursor;
    private static final String TAG = "ParlorListView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parlor_list);

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
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ArrayAdapter<Parlor> adapter = (ArrayAdapter<Parlor>) adapterView.getAdapter();
        if(adapter == null) {
            Log.e(TAG, "adapter is null");
        } else {
            Log.e(TAG, adapter.getItem(position).getName());
        }
    }
}