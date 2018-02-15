package example2;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

public class AlbumDB {

    private Connection conn;
    private Statement stmt;
    private ResultSet rset;

     public void openDB() {
        try {
            // Load the Oracle JDBC driver
            OracleDataSource ods = new OracleDataSource();

            ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");               
            ods.setUser("******");
            ods.setPassword("*****");
//            ods.setURL("jdbc:oracle:thin:hr/hr@localhost:1521/XE");
//            ods.setUser("hr");
//            ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (SQLException e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
    }

    public void CreateAlbum() {

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE Albums");
            System.out.println("Table Dropped");
        } catch (SQLException e) {
            System.out.println("Creating Table");
        }
        try {

            stmt.executeUpdate("CREATE TABLE Albums "
                    + "(id NUMBER PRIMARY KEY, title VARCHAR2(40), artist VARCHAR2(30))");

            stmt.executeUpdate("INSERT INTO Albums "
                    + "VALUES (1, 'Strangeland', 'Keane')");

            stmt.executeUpdate("INSERT INTO Albums "
                    + "VALUES (2, 'Hopes and Fears', 'Keane')");

            stmt.executeUpdate("INSERT INTO Albums "
                    + "VALUES (3, 'Parachutes', 'ColdPlay')");

            stmt.executeUpdate("INSERT INTO Albums "
                    + "VALUES (4, 'Pure Heroine', 'Lorde')");

            stmt.executeUpdate("INSERT INTO Albums "
                    + "VALUES (5, 'Second Coming', 'Stone Roses')");

            stmt.executeUpdate("INSERT INTO Albums "
                    + "VALUES (6, 'Golden Greats', 'Ian Brown	')");

        } catch (SQLException e) {
            System.out.print("SQL Exception Here " + e);
            System.exit(1);
        }

    }

    public void queryAlbum() {
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * FROM Albums ");
            while (rset.next()) {
                System.out.println(rset.getInt(1) + " " + rset.getString(2) + " " + rset.getString(3));
            }
        } catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }
    }

    public void closeDB() {
        try {
            stmt.close();
            rset.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.print("Could not close connection ");
        }
    }
}
