package at.sayah.diploma;

/**
 * @author mreilaender
 */
public interface DatabaseCredentials {
    String HOST = "37.221.192.23";
    String DATABASE = "icewien";
    String USERNAME = "icewien";
    String PASSWORD = "icewien";
    int PORT = 3306;

    String JDBC_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
}
