package at.sayah.diploma;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
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
public class ParlorAsync extends AsyncTask<Integer, Void, ArrayAdapter<String>> {
    private String url = DatabaseCredentials.JDBC_URL;
    private CloseableIterator<Parlor> iterator;
    private ListView parlor_list;
    private Context context;

    public ParlorAsync(Context context, ListView parlor_list) {
        this.parlor_list = parlor_list;
        this.context = context;
    }

    @Override
    protected ArrayAdapter<String> doInBackground(Integer... ints) {
        JdbcConnectionSource source = null;
        try {
            source = new JdbcConnectionSource(url);
            source.setUsername(DatabaseCredentials.USERNAME);
            source.setPassword(DatabaseCredentials.PASSWORD);
            Dao<Parlor, Integer> parlorDao = DaoManager.createDao(source, Parlor.class);
            QueryBuilder<Parlor, Integer> queryBuilder = parlorDao.queryBuilder();
            queryBuilder.selectColumns("name");
            queryBuilder.orderBy("name", true);
            iterator = parlorDao.iterator(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<String> items = new ArrayList<>();
        while(iterator.hasNext()) {
            items.add(iterator.next().getName());
        }
        return new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items);
    }
}
