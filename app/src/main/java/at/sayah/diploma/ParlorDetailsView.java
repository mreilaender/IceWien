package at.sayah.diploma;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import at.sayah.diploma.entities.Parlor;
import com.google.gson.Gson;

public class ParlorDetailsView extends AppCompatActivity {

    private static final String TAG = "ParlorDetailsView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parlor_details);

        Intent intent = getIntent();
        String json = (String) intent.getExtras().get("parlor");

        Parlor parlor = new Gson().fromJson(json, Parlor.class);
        ((TextView) findViewById(R.id.parlor_name)).setText(parlor.getName());
        ((TextView) findViewById(R.id.parlor_address)).setText(parlor.getStreet());

        String opening_time = parlor.getOpening_time() + " - " + parlor.getClosing_time();
        ((TextView) findViewById(R.id.parlor_opening_time_mo)).setText(opening_time);
        ((TextView) findViewById(R.id.parlor_opening_time_tu)).setText(opening_time);
        ((TextView) findViewById(R.id.parlor_opening_time_we)).setText(opening_time);
        ((TextView) findViewById(R.id.parlor_opening_time_th)).setText(opening_time);
        ((TextView) findViewById(R.id.parlor_opening_time_fr)).setText(opening_time);
        ((TextView) findViewById(R.id.parlor_opening_time_sa)).setText(opening_time);
    }
}
