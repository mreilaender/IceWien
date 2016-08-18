package at.sayah.diploma;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import at.sayah.diploma.entities.Parlor;
import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.dao.CloseableIterable;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mreilaender
 */
public class ParlorAsync extends AsyncTask<Integer, Void, List<Parlor>> {
    private CloseableIterator<Parlor> iterator;
    private static final String TAG = "ParlorAsync";

    @Override
    protected List<Parlor> doInBackground(Integer... ints) {
        JdbcConnectionSource source = null;
        try {
            source = new JdbcConnectionSource(DatabaseCredentials.JDBC_URL);
            source.setUsername(DatabaseCredentials.USERNAME);
            source.setPassword(DatabaseCredentials.PASSWORD);
            Dao<Parlor, Integer> parlorDao = DaoManager.createDao(source, Parlor.class);
            QueryBuilder<Parlor, Integer> queryBuilder = parlorDao.queryBuilder();
            queryBuilder.orderBy("name", true);
            iterator = parlorDao.iterator(queryBuilder.prepare());

        } catch (SQLException e) {
            Log.e(TAG, "Error while connecting to database: " + e.getMessage());
            e.printStackTrace();
        }
        List<Parlor> items = new ArrayList<>();
        Log.i(TAG, "Downloading Database data");
        while(iterator.hasNext()) {
            items.add(iterator.next());
        }
        return items;
        //return new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items);
    }
}
