package at.sayah.diploma;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import at.sayah.diploma.entities.Parlor;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class ParlorDetailsView extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "ParlorDetailsView";
    private Parlor parlor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parlor_details);

        Intent intent = getIntent();
        String json = (String) intent.getExtras().get("parlor");

        parlor = new Gson().fromJson(json, Parlor.class);
        ((TextView) findViewById(R.id.parlor_name)).setText(parlor.getName());

        String address = parlor.getStreet() + " " + parlor.getStreet_number();
        ((TextView) findViewById(R.id.parlor_address)).setText(address);

        String opening_time = parlor.getOpening_time() + " - " + parlor.getClosing_time();
        ((TextView) findViewById(R.id.parlor_opening_time_mo)).setText(opening_time);
        ((TextView) findViewById(R.id.parlor_opening_time_tu)).setText(opening_time);
        ((TextView) findViewById(R.id.parlor_opening_time_we)).setText(opening_time);
        ((TextView) findViewById(R.id.parlor_opening_time_th)).setText(opening_time);
        ((TextView) findViewById(R.id.parlor_opening_time_fr)).setText(opening_time);
        ((TextView) findViewById(R.id.parlor_opening_time_sa)).setText(opening_time);
        ((TextView) findViewById(R.id.parlor_opening_time_su)).setText(R.string.closed);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ImageView parlor_image = (ImageView) findViewById(R.id.parlor_image);
        parlor_image.setImageResource(R.drawable.default_parlor_image);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //googleMap.setMyLocationEnabled(true);
            double lng = Double.parseDouble(parlor.getLongitude()),
                    lat = Double.parseDouble(parlor.getLatitude());
            float zoom = 12.0f;
            googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title(parlor.getName()));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom);
            googleMap.animateCamera(cameraUpdate);
        }
    }
}
