package example4;

class TestHR {
    public static void main (String[] args) throws Exception
    {
        HRDatabase hr = new HRDatabase();
        hr.openDB();
        hr.queryHR();
        hr.closeDB();
    }
}
