package examplePreparedStatements;

import java.sql.*; // Needed for JDBC classes
import oracle.jdbc.pool.OracleDataSource;

/**
 * This program creates the CoffeeDB database.
 */
public class CoffeeOperations {

    private Connection conn;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rset;

    public void openDB() {
        try {
            // Load the Oracle JDBC driver
            OracleDataSource ods = new OracleDataSource();

//            ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//            ods.setUser("????");
//            ods.setPassword("????");
            
            ods.setURL("jdbc:oracle:thin:hr/hr@localhost:1521/XE");
            ods.setUser("hr");
            ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (Exception e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
    }

    /**
     * The dropTables method drops any existing in case the database already exists.
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
            String sql1 = "CREATE TABLE Coffee "
                    + "(Description VARCHAR(25),ProdNum VARCHAR(10) NOT NULL PRIMARY KEY, Price DECIMAL(5,2))";
            // Get a Statement object.
            pstmt = conn.prepareStatement(sql1);
            // Create the table.
            pstmt.executeUpdate();

            String sql2 = "INSERT INTO Coffee VALUES (?,?,?)";
            pstmt = conn.prepareStatement(sql2);
            // Insert row #1.
            pstmt.setString(1, "Bolivian Dark");
            pstmt.setString(2, "14-001");
            pstmt.setDouble(3, 8.95);
            pstmt.executeUpdate();

            // Insert row #2.
            pstmt.setString(1, "Bolivian Medium");
            pstmt.setString(2, "14-002");
            pstmt.setDouble(3, 7.95);
            pstmt.executeUpdate();

            // Insert row #3.
            pstmt.setString(1, "Brazilian Medium");
            pstmt.setString(2, "15-002");
            pstmt.setDouble(3, 7.95);
            pstmt.executeUpdate();

            // Insert row #4.
            pstmt.setString(1, "Brazilian Decaf");
            pstmt.setString(2, "15-003");
            pstmt.setDouble(3, 8.55);
            pstmt.executeUpdate();

            // Insert row #5.
            pstmt.setString(1, "Central American Dark");
            pstmt.setString(2, "16-001");
            pstmt.setDouble(3, 9.95);
            pstmt.executeUpdate();

            // Insert row #6.
            pstmt.setString(1, "Central American Medium");
            pstmt.setString(2, "16-002");
            pstmt.setDouble(3, 9.95);
            pstmt.executeUpdate();

            // Insert row #7.
            pstmt.setString(1, "Sumatra Dark");
            pstmt.setString(2, "17-001");
            pstmt.setDouble(3, 7.95);
            pstmt.executeUpdate();

            // Insert row #8.
            pstmt.setString(1, "Sumatra Decaf");
            pstmt.setString(2, "17-002");
            pstmt.setDouble(3, 8.95);
            pstmt.executeUpdate();

            // Insert row #9.
            pstmt.setString(1, "Sumatra Medium");
            pstmt.setString(2, "17-003");
            pstmt.setDouble(3, 7.95);
            pstmt.executeUpdate();

            // Insert row #10.
            pstmt.setString(1, "Sumatra Organic Dark");
            pstmt.setString(2, "17-004");
            pstmt.setDouble(3, 11.95);
            pstmt.executeUpdate();

            // Insert row #11.
            pstmt.setString(1, "Kona Medium");
            pstmt.setString(2, "18-001");
            pstmt.setDouble(3, 18.45);
            pstmt.executeUpdate();

            // Insert row #12.
            pstmt.setString(1, "Kona Dark");
            pstmt.setString(2, "18-002");
            pstmt.setDouble(3, 18.45);
            pstmt.executeUpdate();

            // Insert row #13.
            pstmt.setString(1, "French Roast Dark");
            pstmt.setString(2, "19-001");
            pstmt.setDouble(3, 19.65);
            pstmt.executeUpdate();

            // Insert row #14.
            pstmt.setString(1, "Galapagos Medium");
            pstmt.setString(2, "20-001");
            pstmt.setDouble(3, 6.85);
            pstmt.executeUpdate();

            // Insert row #15.
            pstmt.setString(1, "Guatemalan Dark");
            pstmt.setString(2, "21-001");
            pstmt.setDouble(3, 9.95);
            pstmt.executeUpdate();

            // Insert row #16.
            pstmt.setString(1, "Guatemalan Decaf");
            pstmt.setString(2, "21-002");
            pstmt.setDouble(3, 10.45);
            pstmt.executeUpdate();

            // Insert row #17.
            pstmt.setString(1, "Guatemalan Medium");
            pstmt.setString(2, "21-003");
            pstmt.setDouble(3, 9.95);
            pstmt.executeUpdate();

            System.out.println("Coffee table created.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void queryDB() {
        String sqlStatement = "SELECT * FROM Coffee";
        try {
            // Send the statement to the DBMS.
            stmt = conn.createStatement();
            rset = stmt.executeQuery(sqlStatement);

	    // Display the contents of the result set.
            // The result set will have three columns.
            while (rset.next()) {
                System.out.printf("%25s %10s %5.2f\n",
                        rset.getString("Description"),
                        rset.getString("ProdNum"), rset.getDouble("Price"));
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

     public void add(String description,String prodNum, double price) {
        try {
            // Create a string with an INSERT statement.
            String sqlStatement = "INSERT INTO Coffee VALUES (?,?,?)";
            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, description);
            pstmt.setString(2, prodNum);
            pstmt.setDouble(3, price);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row added to the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
     
      public void updateRecord(String prodID, double price) {
        try {
            // Create a string with an UPDATE statement.
            String sqlStatement = "UPDATE Coffee SET Price = ?"+
                                  "WHERE ProdNum = ?" ;

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setDouble(1, price);
            pstmt.setString(2, prodID);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row updated in the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
      
      public void deleteRecord(String prodID) {
        try {
            // Create a string with an DELETE statement.
            String sqlStatement = "DELETE FROM Coffee WHERE ProdNum = ?";

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, prodID);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row deleted to the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void closeDB() {
        try {
            stmt.close();
            pstmt.close();
            rset.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.print("Could not close connection ");
        }
    }
}
