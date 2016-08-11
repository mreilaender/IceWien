package at.sayah.diploma;

/**
 * @author mreilaender
 */
public interface DatabaseCredentials {
    String HOST = "192.168.100.50";
    String DATABASE = "mydatabase";
    String USERNAME = "user";
    String PASSWORD = "password";
    int PORT = 3306;

    String JDBC_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
}
