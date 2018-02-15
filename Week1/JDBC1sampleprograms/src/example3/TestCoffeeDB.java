package example3;

public class TestCoffeeDB {
	public static void main(String[] args) {
		CreateCoffeeDB cdb = new CreateCoffeeDB();
		cdb.openDB();
		cdb.dropTables();
		cdb.buildCoffeeTable();
		cdb.queryDB();
		cdb.queryStrings();
		cdb.queryLIKE();
		cdb.queryANDOR();
		cdb.querySORT();
		cdb.queryMATHFNS();
		cdb.insert("22-001", 8.65, "Honduran Dark");
		cdb.queryDB();
		cdb.closeDB();
	}
}
