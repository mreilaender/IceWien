package at.sayah.diploma;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import at.sayah.diploma.entities.Flavour;
import at.sayah.diploma.entities.Parlor;
import at.sayah.diploma.entities.Vote;
import com.google.gson.Gson;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author mreilaender
 */
public class FlavourListActivity extends AppCompatActivity {
    public static final String TAG = "FlavourListActivity";

    private Parlor parlor;
    private ListView flavour_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flavour_list);

        flavour_list = (ListView) findViewById(R.id.flavour_list_view);

        Intent intent = getIntent();
        String json = (String) intent.getExtras().get("parlor");
        if (json != null)
            parlor = new Gson().fromJson(json, Parlor.class);

        TextView heading = (TextView) findViewById(R.id.flavour_list_heading);
        heading.setText(parlor.getName());

        new GetFlavours().execute();
    }

    private class GetFlavours extends AsyncTask<Void, Void, List<Flavour>> {
        private JdbcConnectionSource source;
        private CloseableIterator<Flavour> results;

        @Override
        protected List<Flavour> doInBackground(Void... voids) {
            try {
                source = new JdbcConnectionSource(DatabaseCredentials.JDBC_URL);
                source.setUsername(DatabaseCredentials.USERNAME);
                source.setPassword(DatabaseCredentials.PASSWORD);
                Dao<Flavour, Integer> flavourDao = DaoManager.createDao(source, Flavour.class);
                QueryBuilder<Flavour, Integer> queryBuilder = flavourDao.queryBuilder();
                queryBuilder
                        .where()
                        .eq(Parlor.PARLOR_ID_FIELD_NAME, parlor);
                results = flavourDao.iterator(queryBuilder.prepare());
                ArrayList<Flavour> flavours = new ArrayList<>();
                while (results.hasNext())
                    flavours.add(results.next());
                return flavours;
            } catch (SQLException e) {
                Log.e(TAG, "Error while connecting to database: " + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Flavour> flavours) {
            new GetVotesPerFlavourTask().execute(flavours);
            try {
                source.close();
                results.close();
            } catch (SQLException e) {
                Log.e(TAG, e.getMessage());
            }

        }
    }

    private class GetVotesPerFlavourTask extends AsyncTask<List<Flavour>, Void, Vote> {

        private JdbcConnectionSource source;
        private CloseableIterator<Vote> results;
        private List<Flavour> flavours;
        HashMap<Flavour, List<Vote>> votesPerFlav;

        public GetVotesPerFlavourTask() {
            this.votesPerFlav = new HashMap<>();
        }

        @Override
        protected Vote doInBackground(List<Flavour>... flavours) {
            try {
                source = new JdbcConnectionSource(DatabaseCredentials.JDBC_URL);
                source.setUsername(DatabaseCredentials.USERNAME);
                source.setPassword(DatabaseCredentials.PASSWORD);
                Dao<Vote, Date> votesDao = DaoManager.createDao(source, Vote.class);

                this.flavours = flavours[0];
                for (Flavour flavour: this.flavours) {
                    List<Vote> votes = new ArrayList<>();
                    QueryBuilder<Vote, Date> votesQueryBuilder = votesDao.queryBuilder();
                    votesQueryBuilder
                            .where()
                            .eq(Flavour.FLAVOUR_ID_FIELD_NAME, flavour);
                    results = votesDao.iterator(votesQueryBuilder.prepare());
                    while (results.hasNext())
                        votes.add(results.next());
                    votesPerFlav.put(flavour, votes);
                }

            } catch (SQLException e) {
                Log.e(TAG, "Error while connecting to database: " + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Vote vote) {
            try {
                source.close();
                results.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            createAdapter(flavours, votesPerFlav);
        }
    }

    private void createAdapter(List<Flavour> flavours, HashMap<Flavour, List<Vote>> votes) {
        FlavourAdapter adapter = new FlavourAdapter(this, R.layout.flavour_list_view_row, flavours, votes);
        flavour_list.setAdapter(adapter);
    }
}
