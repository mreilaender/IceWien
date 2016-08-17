package at.sayah.diploma;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.concurrent.ExecutionException;

public class ParlorListView extends AppCompatActivity {

    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parlor_list);

        ListView parlor_list = (ListView) findViewById(R.id.list_parlor);
        try {
            parlor_list.setAdapter(new ParlorAsync(this, parlor_list).execute().get());
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
}