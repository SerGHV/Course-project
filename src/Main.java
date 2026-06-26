import database.DBConnection;

public class Main {

    public static void main(String[] args) {

        DBConnection db = new DBConnection();

        if (db.getConnection() != null) {
            System.out.println("SUCCESS");
        } else {
            System.out.println("FAILED");
        }
    }
}