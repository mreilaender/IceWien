package at.sayah.diploma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ParlorListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parlor_list);

        ListView parlor_list = (ListView) findViewById(R.id.list_parlor);
    }
}
