package at.sayah.diploma.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import at.sayah.diploma.DatabaseCredentials;
import at.sayah.diploma.R;
import at.sayah.diploma.entities.Flavour;
import at.sayah.diploma.entities.User;
import at.sayah.diploma.entities.Vote;
import at.sayah.diploma.sessionmanager.UserSessionManager;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.SQLException;
import java.util.*;

/**
 * @author mreilaender
 */
public class FlavourAdapter extends ArrayAdapter<Flavour> implements RatingBar.OnRatingBarChangeListener, AdapterView.OnItemClickListener, View.OnClickListener {
    public static final String TAG = "FlavourAdapter";

    private TextView flavour_name;
    private RatingBar flavour_rating;
    private HashMap<Flavour, List<Vote>> votes;
    private UserSessionManager userSessionManager;
    private HashMap<RatingBar, Flavour> ratingBarFlavours;
    private Toast toastAlreadyVoted, toastThankYou;

    public FlavourAdapter(Context context, @LayoutRes int resource, @NonNull List<Flavour> objects, @NonNull HashMap<Flavour, List<Vote>> votes) {
        super(context, resource, objects);
        this.votes = votes;
        userSessionManager = new UserSessionManager(context.getApplicationContext());
        this.ratingBarFlavours = new HashMap<>();
        Context appCont = context.getApplicationContext();
        CharSequence already_voted = appCont.getString(R.string.already_voted),
                thank_you = appCont.getString(R.string.thank_you_for_your_voting);
        toastAlreadyVoted = Toast.makeText(appCont, already_voted, Toast.LENGTH_SHORT);
        toastThankYou = Toast.makeText(appCont, thank_you, Toast.LENGTH_SHORT);
        toastAlreadyVoted.setGravity(Gravity.BOTTOM, 0, 0);
        toastThankYou.setGravity(Gravity.BOTTOM, 0, 0);
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
            // Make RatingBar unchangeable when user is not logged in
            flavour_rating.setIsIndicator(!userSessionManager.isLoggedIn());
            // TODO: set onClickListener every time when view is created? See View#hasOnClickListeners
            if (!flavour_rating.hasOnClickListeners())
                flavour_rating.setOnRatingBarChangeListener(this);

            ratingBarFlavours.put(flavour_rating, flavour);
            if(checkIfUserAlreadyVoted(flavour))
                flavour_rating.setIsIndicator(true);
        } else
            Log.e(TAG, "Could not create view for ListView. Either item or view is null");
        return convertView;
    }

    /**
     * Checks if the user has already voted for the given falvour
     * @param flavour
     * @return
     */
    private boolean checkIfUserAlreadyVoted(Flavour flavour) {
        User user = userSessionManager.getUser();
        for (Vote vote : votes.get(flavour))
            if (vote.getUid().getUid() == user.getUid())
                return true;
        return false;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if (fromUser) {
            new SetRating(ratingBar, rating).execute();
            this.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ArrayAdapter<Flavour> adapter = (ArrayAdapter<Flavour>) adapterView.getAdapter();
        Flavour flavour = adapter.getItem(position);
        if (checkIfUserAlreadyVoted(flavour))
            toastAlreadyVoted.show();
    }

    @Override
    public void onClick(View view) {

    }

    private class SetRating extends AsyncTask<Void, Void, Void> {
        private RatingBar ratingBar;
        private float rating;

        private JdbcConnectionSource source;

        public SetRating(RatingBar ratingBar, float rating) {
            this.ratingBar = ratingBar;
            this.rating = rating;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                source = new JdbcConnectionSource(DatabaseCredentials.JDBC_URL);
                source.setUsername(DatabaseCredentials.USERNAME);
                source.setPassword(DatabaseCredentials.PASSWORD);
                User user = userSessionManager.getUser();
                Flavour flavour = ratingBarFlavours.get(ratingBar);
                Dao<Vote, Date> votesDao = DaoManager.createDao(source, Vote.class);

                // Create new vote
                Vote vote = new Vote();
                vote.setDate(new Date());
                vote.setFlavour(flavour);
                vote.setRanking((int)rating);
                vote.setUid(user);
                votesDao.create(vote);

                // Update HashMap
                List<Vote> tmp = votes.get(flavour);
                    tmp.add(vote);
                votes.put(flavour, tmp);
                toastThankYou.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ratingBar.setIsIndicator(true);
            try {
                source.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
