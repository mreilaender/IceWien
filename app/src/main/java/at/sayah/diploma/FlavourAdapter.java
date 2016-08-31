package at.sayah.diploma;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import at.sayah.diploma.entities.Flavour;
import at.sayah.diploma.entities.Vote;

import java.util.HashMap;
import java.util.List;

/**
 * @author mreilaender
 */
public class FlavourAdapter extends ArrayAdapter<Flavour> {
    public static final String TAG = "FlavourAdapter";

    private TextView flavour_name;
    private RatingBar flavour_rating;
    private HashMap<Flavour, List<Vote>> votes;

    public FlavourAdapter(Context context, @LayoutRes int resource, @NonNull List<Flavour> objects, @NonNull HashMap<Flavour, List<Vote>> votes) {
        super(context, resource, objects);
        this.votes = votes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.flavour_list_view_row, null);
        }

        Flavour flavour = getItem(position);
        if (flavour != null) {
            flavour_name = (TextView) convertView.findViewById(R.id.flavour_name);
            flavour_rating = (RatingBar) convertView.findViewById(R.id.flavour_rating);

            flavour_name.setText(flavour.getName());

            // Calculate mean rating
            List<Vote> votesPerFlav = votes.get(flavour);
            float meanRating = 0f;
            for (Vote vote:votesPerFlav)
                meanRating += vote.getRanking();
            meanRating = meanRating / votesPerFlav.size();
            flavour_rating.setRating(meanRating);
        } else
            Log.e(TAG, "Could not create view for ListView. Either item or view is null");
        return convertView;
    }
}
