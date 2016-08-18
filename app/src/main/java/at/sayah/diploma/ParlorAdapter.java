package at.sayah.diploma;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import at.sayah.diploma.entities.Parlor;

import java.util.List;

/**
 * @author mreilaender
 * @version 18.08.2016
 */
public class ParlorAdapter extends ArrayAdapter<Parlor> {

    private final String TAG = "ParlorAdapter";

    public ParlorAdapter(Context context, int resource, List<Parlor> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        } else {

        }
        Parlor parlor = getItem(position);
        if(parlor != null) {
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(parlor.getName());
        } else {
            Log.e(TAG, "Could not create view for ListView. Either item or view is null");
        }
        return convertView;
    }
}
