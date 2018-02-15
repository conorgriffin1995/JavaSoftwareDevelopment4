package example4;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.Scanner;

/**
 * This program uses the HR database that comes with Oracle XE
 * You can use Oracle XE on your home machine to work on database programs
 */
public class HRDatabase {
    private Connection conn;
    private Statement stmt;
    private ResultSet rset;

    public void openDB() {
        Scanner in = new Scanner(System.in);
        try {
            // Load the Oracle JDBC driver
            OracleDataSource ods = new OracleDataSource();

            //ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");               
            //ods.setUser("?????");
            //ods.setPassword("??????");
            ods.setURL("jdbc:oracle:thin:hr/hr@localhost:1521/XE");
            ods.setUser("hr");
            ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (SQLException e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
    }
    public void queryHR()
    {
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery("select * from departments ");
            while (rset.next()) {
                System.out.println(rset.getInt(1) + " " + rset.getString(2));
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
