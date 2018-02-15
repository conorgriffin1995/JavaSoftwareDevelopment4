package example3;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This program creates the CoffeeDB database.
 */
public class CreateCoffeeDB {

    private Connection conn;
    private Statement stmt;
    private ResultSet rset;

    public void openDB() {
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

    /**
     * The dropTables method drops the table if it already exists.
     */
    public void dropTables() {
        System.out.println("Checking for existing tables.");

        try {
            // Get a Statement object.
            stmt = conn.createStatement();

            try {
                // Drop the Coffee table.
                stmt.execute("DROP TABLE Coffee");
                System.out.println("Coffee table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * The buildCoffeeTable method creates the Coffee table and adds some rows to it.
     */
    public void buildCoffeeTable() {
        try {
            // Get a Statement object.
            stmt = conn.createStatement();

            // Create the table.
            stmt.executeUpdate("CREATE TABLE Coffee "
                    + "(Description VARCHAR(25),ProdNum VARCHAR(10) NOT NULL PRIMARY KEY, Price DECIMAL(5,2))");

            // Insert row #1.
            stmt.execute("INSERT INTO Coffee VALUES ( " + "'Bolivian Dark', "
                    + "'14-001', " + "8.95 )");

            // Insert row #2.
            stmt.execute("INSERT INTO Coffee VALUES ( " + "'Bolivian Medium', "
                    + "'14-002', " + "8.95 )");

            // Insert row #3.
            stmt.execute("INSERT INTO Coffee VALUES ( " + "'Brazilian Dark', "
                    + "'15-001', " + "7.95 )");

            // Insert row #4.
            stmt.execute("INSERT INTO Coffee VALUES ( "
                    + "'Brazilian Medium', " + "'15-002', " + "7.95 )");

            // Insert row #5.
            stmt.execute("INSERT INTO Coffee VALUES ( " + "'Brazilian Decaf', "
                    + "'15-003', " + "8.55 )");

            // Insert row #6.
            stmt.execute("INSERT INTO Coffee VALUES ( "
                    + "'Central American Dark', " + "'16-001', " + "9.95 )");

            // Insert row #7.
            stmt.execute("INSERT INTO Coffee VALUES ( "
                    + "'Central American Medium', " + "'16-002', " + "9.95 )");

            // Insert row #8.
            stmt.execute("INSERT INTO Coffee VALUES ( " + "'Sumatra Dark', "
                    + "'17-001', " + "7.95 )");

            // Insert row #9.
            stmt.execute("INSERT INTO Coffee VALUES ( " + "'Sumatra Decaf', "
                    + "'17-002', " + "8.95 )");

            // Insert row #10.
            stmt.execute("INSERT INTO Coffee VALUES ( " + "'Sumatra Medium', "
                    + "'17-003', " + "7.95 )");

            // Insert row #11.
            stmt.execute("INSERT INTO Coffee VALUES ( "
                    + "'Sumatra Organic Dark', " + "'17-004', " + "11.95 )");

            // Insert row #12.
            stmt.execute("INSERT INTO Coffee VALUES ( " + "'Kona Medium', "
                    + "'18-001', " + "18.45 )");

            // Insert row #13.
            stmt.execute("INSERT INTO Coffee VALUES ( " + "'Kona Dark', "
                    + "'18-002', " + "18.45 )");

            // Insert row #14.
            stmt.execute("INSERT INTO Coffee VALUES ( "
                    + "'French Roast Dark', " + "'19-001', " + "9.65 )");

            // Insert row #15.
            stmt.execute("INSERT INTO Coffee VALUES ( "
                    + "'Galapagos Medium', " + "'20-001', " + "6.85 )");

            // Insert row #16.
            stmt.execute("INSERT INTO Coffee VALUES ( " + "'Guatemalan Dark', "
                    + "'21-001', " + "9.95 )");

            // Insert row #17.
            stmt.execute("INSERT INTO Coffee VALUES ( "
                    + "'Guatemalan Decaf', " + "'21-002', " + "10.45 )");

            // Insert row #18.
            stmt.execute("INSERT INTO Coffee VALUES ( "
                    + "'Guatemalan Medium', " + "'21-003', " + "9.95 )");

            System.out.println("Coffee table created.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void queryDB() {

        String sqlStatement = "SELECT * FROM Coffee";
        try {
            stmt = conn.createStatement();
            // Send the statement to the DBMS.
            rset = stmt.executeQuery(sqlStatement);

			// Display the contents of the result set.
            // The result set will have three columns.
            while (rset.next()) {
                System.out.printf("%25s %10s %5.2f\n",
                        rset.getString("Description"),
                        rset.getString("ProdNum"), rset.getDouble("Price"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void queryStrings() {
        String sqlStatement = "SELECT * FROM Coffee WHERE UPPER(Description) = 'FRENCH ROAST DARK'";

        try {
            // Send the statement to the DBMS.
            rset = stmt.executeQuery(sqlStatement);

			// Display the contents of the result set.
            // The result set will have three columns.
            while (rset.next()) {
                System.out.printf("%25s %10s %5.2f\n",
                        rset.getString("Description"),
                        rset.getString("ProdNum"), rset.getDouble("Price"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void queryLIKE() {
        // String sqlStatement =
        // "SELECT * FROM Coffee WHERE Description LIKE '%Decaf%'";
        // String sqlStatement =
        // "SELECT * FROM Coffee WHERE ProdNum LIKE '2_-00_'";
        String sqlStatement = "SELECT * FROM Coffee WHERE Description NOT LIKE '%Decaf%'";

        try {
            // Send the statement to the DBMS.
            rset = stmt.executeQuery(sqlStatement);

            // Display the contents of the result set.
            // The result set will have three columns.
            while (rset.next()) {
                System.out.printf("%25s %10s %5.2f\n",
                        rset.getString("Description"),
                        rset.getString("ProdNum"), rset.getDouble("Price"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void queryANDOR() {
	// String sqlStatement =
        // "SELECT * FROM Coffee WHERE Price > 10.00 AND Price < 14.00";
        String sqlStatement = "SELECT * FROM Coffee WHERE Description LIKE '%Dark%' OR ProdNum LIKE '16%'";

        try {
            // Send the statement to the DBMS.
            rset = stmt.executeQuery(sqlStatement);

			// Display the contents of the result set.
            // The result set will have three columns.
            while (rset.next()) {
                System.out.printf("%25s %10s %5.2f\n",
                        rset.getString("Description"),
                        rset.getString("ProdNum"), rset.getDouble("Price"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void querySORT() {

        // String sqlStatement = "SELECT * FROM Coffee ORDER BY Price";
        String sqlStatement = "SELECT * FROM Coffee	WHERE Price > 9.95 ORDER BY Price DESC";

        try {
            // Send the statement to the DBMS.
            rset = stmt.executeQuery(sqlStatement);

			// Display the contents of the result set.
            // The result set will have three columns.
            while (rset.next()) {
                System.out.printf("%25s %10s %5.2f\n",
                        rset.getString("Description"),
                        rset.getString("ProdNum"), rset.getDouble("Price"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void queryMATHFNS() {
		// Variables to hold the lowest, highest, and
        // average prices of coffee.
        double lowest = 0.0, highest = 0.0, average = 0.0;

        try {
			// Create SELECT statements to get the lowest, highest,
            // and average prices from the Coffee table.
            String minStatement = "SELECT MIN(Price) FROM Coffee";
            String maxStatement = "SELECT MAX(Price) FROM Coffee";
            String avgStatement = "SELECT AVG(Price) FROM Coffee";

            // Get the lowest price.
            ResultSet minResult = stmt.executeQuery(minStatement);
            if (minResult.next()) {
                lowest = minResult.getDouble(1);
            }

            // Get the highest price.
            ResultSet maxResult = stmt.executeQuery(maxStatement);
            if (maxResult.next()) {
                highest = maxResult.getDouble(1);
            }

            // Get the average price.
            ResultSet avgResult = stmt.executeQuery(avgStatement);
            if (avgResult.next()) {
                average = avgResult.getDouble(1);
            }

            // Display the results.
            System.out.printf("Lowest price: $%.2f\n", lowest);
            System.out.printf("Highest price: $%.2f\n", highest);
            System.out.printf("Average price: $%.2f\n", average);
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void insert(String prodNum, double price, String description) {
        try {
            // Create a string with an INSERT statement.
            String sqlStatement = "INSERT INTO Coffee "
                    + "(ProdNum, Price, Description) "
                    + "VALUES ('"
                    + prodNum + "', "
                    + price + ", '"
                    + description + "')";

            // Send the statement to the DBMS.
            int rows = stmt.executeUpdate(sqlStatement);

            // Display the results.
            System.out.println(rows + " row(s) added to the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
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
