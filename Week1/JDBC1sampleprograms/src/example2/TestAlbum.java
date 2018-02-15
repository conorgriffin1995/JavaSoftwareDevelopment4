package example2;

public class TestAlbum {

    public static void main(String[] args) {
        AlbumDB db = new AlbumDB();
        db.openDB();
        db.CreateAlbum();
        db.queryAlbum();
        db.closeDB();
    }
}
