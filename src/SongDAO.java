import java.sql.*;
import java.util.ArrayList;

public class SongDAO {
    private ArtistDAO artistDAO = new ArtistDAO();

    public int insertSong(Song song) {
        int artistId = artistDAO.insertArtist(song.getArtist());

        if (artistId == -1) {
            System.err.println("Failed to insert or retrieve artist!");
            return -1;
        }

        String sql = "INSERT INTO songs (title, artist_id, duration, genre, year) VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, song.getTitle());
            pstmt.setInt(2, artistId);
            pstmt.setInt(3, song.getDuration());
            pstmt.setString(4, song.getGenre());
            pstmt.setInt(5, song.getYear());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                System.out.println("Song inserted with ID: " + id);
                return id;
            }

        } catch (SQLException e) {
            System.err.println("Error inserting song: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public Song getSongById(int id) {
        String sql = "SELECT s.*, a.name as artist_name, a.country as artist_country " +
                "FROM songs s JOIN artists a ON s.artist_id = a.id WHERE s.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Artist artist = new Artist(
                        rs.getString("artist_name"),
                        rs.getString("artist_country")
                );

                return new Song(
                        rs.getString("title"),
                        artist,
                        rs.getInt("duration"),
                        rs.getString("genre"),
                        rs.getInt("year")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error getting song: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        String sql = "SELECT s.*, a.name as artist_name, a.country as artist_country " +
                "FROM songs s JOIN artists a ON s.artist_id = a.id ORDER BY s.title";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getString("artist_name"),
                        rs.getString("artist_country")
                );

                Song song = new Song(
                        rs.getString("title"),
                        artist,
                        rs.getInt("duration"),
                        rs.getString("genre"),
                        rs.getInt("year")
                );
                songs.add(song);
            }

        } catch (SQLException e) {
            System.err.println("Error getting all songs: " + e.getMessage());
            e.printStackTrace();
        }
        return songs;
    }

    public ArrayList<Song> searchByTitle(String keyword) {
        ArrayList<Song> songs = new ArrayList<>();
        String sql = "SELECT s.*, a.name as artist_name, a.country as artist_country " +
                "FROM songs s JOIN artists a ON s.artist_id = a.id " +
                "WHERE LOWER(s.title) LIKE LOWER(?) ORDER BY s.title";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getString("artist_name"),
                        rs.getString("artist_country")
                );

                Song song = new Song(
                        rs.getString("title"),
                        artist,
                        rs.getInt("duration"),
                        rs.getString("genre"),
                        rs.getInt("year")
                );
                songs.add(song);
            }

        } catch (SQLException e) {
            System.err.println("Error searching songs by title: " + e.getMessage());
            e.printStackTrace();
        }
        return songs;
    }

    public ArrayList<Song> searchByArtist(String artistName) {
        ArrayList<Song> songs = new ArrayList<>();
        String sql = "SELECT s.*, a.name as artist_name, a.country as artist_country " +
                "FROM songs s JOIN artists a ON s.artist_id = a.id " +
                "WHERE LOWER(a.name) LIKE LOWER(?) ORDER BY s.title";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + artistName + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getString("artist_name"),
                        rs.getString("artist_country")
                );

                Song song = new Song(
                        rs.getString("title"),
                        artist,
                        rs.getInt("duration"),
                        rs.getString("genre"),
                        rs.getInt("year")
                );
                songs.add(song);
            }

        } catch (SQLException e) {
            System.err.println("Error searching songs by artist: " + e.getMessage());
            e.printStackTrace();
        }
        return songs;
    }

    public ArrayList<Song> filterByGenre(String genre) {
        ArrayList<Song> songs = new ArrayList<>();
        String sql = "SELECT s.*, a.name as artist_name, a.country as artist_country " +
                "FROM songs s JOIN artists a ON s.artist_id = a.id " +
                "WHERE LOWER(s.genre) = LOWER(?) ORDER BY s.title";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, genre);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getString("artist_name"),
                        rs.getString("artist_country")
                );

                Song song = new Song(
                        rs.getString("title"),
                        artist,
                        rs.getInt("duration"),
                        rs.getString("genre"),
                        rs.getInt("year")
                );
                songs.add(song);
            }

        } catch (SQLException e) {
            System.err.println("Error filtering songs by genre: " + e.getMessage());
            e.printStackTrace();
        }
        return songs;
    }

    public boolean updateSong(int id, String title, int duration, String genre, int year) {
        String sql = "UPDATE songs SET title = ?, duration = ?, genre = ?, year = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setInt(2, duration);
            pstmt.setString(3, genre);
            pstmt.setInt(4, year);
            pstmt.setInt(5, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Song updated successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error updating song: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSong(int id) {
        String sql = "DELETE FROM songs WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Song deleted successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error deleting song: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}