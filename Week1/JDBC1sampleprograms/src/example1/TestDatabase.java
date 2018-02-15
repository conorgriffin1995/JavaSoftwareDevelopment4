package example1;

/**
 * Created by Patricia on 27/01/2015.
 */
public class TestDatabase {
    public static void main(String[] args) {
        Database db = new Database();
        db.openDB();
        db.queryDB();
        db.closeDB();
    }
}
