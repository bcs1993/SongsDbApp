import java.sql.*;
public class Main {
    public static final String DB_NAME = "songs.db";
    public static final String DB_PATH = ("jdbc:sqlite:C:\\Users\\brian\\Desktop\\Skillspire-java\\Day-30\\HomeworkSongApp\\songs.db");
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ARTIST = "artist";


    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(DB_PATH);
            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + TABLE_SONGS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_SONGS + "(" +
                    COLUMN_ARTIST + " TEXT, " +
                    COLUMN_TITLE + " TEXT)");


            insertSongsToTable(statement, "Dear Momma", "Tupac");
            insertSongsToTable(statement, "Bad Company", "Five Finger Death Punch");
            insertSongsToTable(statement, "Dance Monkey", "Tones and I");
            insertSongsToTable(statement, "Snowflake", "Tom Macdonald");
            deleteSongs(statement, "Dear Momma");
            updateSongs(statement, "Fake Woke", "Tom Macdonald");
            printSongs(statement, "Dance Monkey");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void insertSongsToTable(Statement statement, String title, String artist) {
        try {
            statement.execute("INSERT INTO " + TABLE_SONGS + " (" +
                    COLUMN_TITLE + ", " +
                    COLUMN_ARTIST + ") " +
                    "VALUES ('" + title + "', '" + artist + "')");
        } catch (SQLException throwables) {
            System.out.println("SQLException: " + throwables.getMessage());
            throwables.printStackTrace();
        }

    }

    public static void deleteSongs(Statement statement, String title) {
        try {
            statement.execute("DELETE FROM " + TABLE_SONGS + " WHERE " +
                    COLUMN_TITLE + "=" + "'" + title + "'");
        } catch (SQLException throwables) {
            System.out.println("SQLException: " + throwables.getMessage());
            throwables.printStackTrace();
        }

    }

    public static void updateSongs(Statement statement, String title, String artist) {
        try {
            statement.execute("UPDATE " +
                    TABLE_SONGS +
                    " SET " +
                    COLUMN_TITLE + "=" + "'" + title + "'" +
                    " WHERE " +
                    COLUMN_ARTIST + "=" + "'" + artist + "'");
        } catch (SQLException throwables) {
            System.out.println("SQLException: " + throwables.getMessage());
            throwables.printStackTrace();

        }
    }

    public static void printSongs(Statement statement, String song) {
        try {
            ResultSet results = statement.executeQuery("SELECT * from " + TABLE_SONGS);
            while (results.next()) {
                System.out.println(results.getString("title") + " " +
                        results.getString("artist"));
            }
            results.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
