import java.sql.*;
import java.util.ArrayList;

public class ArtistDAO {

    public int insertArtist(Artist artist) {
        String sql = "INSERT INTO artists (name, country) VALUES (?, ?) ON CONFLICT (name, country) DO NOTHING RETURNING id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, artist.getName());
            pstmt.setString(2, artist.getCountry());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                System.out.println("Artist inserted with ID: " + id);
                return id;
            } else {
                return getArtistId(artist.getName(), artist.getCountry());
            }

        } catch (SQLException e) {
            System.err.println("Error inserting artist: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public Artist getArtistById(int id) {
        String sql = "SELECT * FROM artists WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Artist(
                        rs.getString("name"),
                        rs.getString("country")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error getting artist: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public int getArtistId(String name, String country) {
        String sql = "SELECT id FROM artists WHERE name = ? AND country = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, country);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            System.err.println("Error getting artist ID: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<Artist> getAllArtists() {
        ArrayList<Artist> artists = new ArrayList<>();
        String sql = "SELECT * FROM artists ORDER BY name";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getString("name"),
                        rs.getString("country")
                );
                artists.add(artist);
            }

        } catch (SQLException e) {
            System.err.println("Error getting all artists: " + e.getMessage());
            e.printStackTrace();
        }
        return artists;
    }

    public boolean updateArtist(int id, String newName, String newCountry) {
        String sql = "UPDATE artists SET name = ?, country = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, newCountry);
            pstmt.setInt(3, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Artist updated successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error updating artist: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteArtist(int id) {
        String sql = "DELETE FROM artists WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Artist deleted successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error deleting artist: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}