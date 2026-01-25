import java.util.ArrayList;

class DatabaseTest {

    public static void main(String[] args) {
        System.out.println("=== Database CRUD Operations Test ===\n");

        if (!DatabaseConnection.testConnection()) {
            System.err.println("Failed to connect to database. Please check your configuration.");
            return;
        }

        ArtistDAO artistDAO = new ArtistDAO();
        SongDAO songDAO = new SongDAO();

        System.out.println("\n========== CREATE OPERATIONS ==========");

        Artist newArtist1 = new Artist("Pink Floyd", "UK");
        Artist newArtist2 = new Artist("Led Zeppelin", "UK");

        int artistId1 = artistDAO.insertArtist(newArtist1);
        int artistId2 = artistDAO.insertArtist(newArtist2);

        Song newSong1 = new Song("Wish You Were Here", newArtist1, 334, "Progressive Rock", 1975);
        Song newSong2 = new Song("Stairway to Heaven", newArtist2, 482, "Rock", 1971);

        int songId1 = songDAO.insertSong(newSong1);
        int songId2 = songDAO.insertSong(newSong2);

        System.out.println("\n========== READ OPERATIONS ==========");

        System.out.println("\n--- All Artists ---");
        ArrayList<Artist> allArtists = artistDAO.getAllArtists();
        for (Artist artist : allArtists) {
            System.out.println(artist);
        }

        System.out.println("\n--- All Songs ---");
        ArrayList<Song> allSongs = songDAO.getAllSongs();
        for (Song song : allSongs) {
            System.out.println(song);
        }

        System.out.println("\n--- Search by Title (keyword: 'Heaven') ---");
        ArrayList<Song> searchResults = songDAO.searchByTitle("Heaven");
        for (Song song : searchResults) {
            System.out.println(song);
        }

        System.out.println("\n--- Search by Artist (keyword: 'Noize') ---");
        searchResults = songDAO.searchByArtist("Noize");
        for (Song song : searchResults) {
            System.out.println(song);
        }

        System.out.println("\n--- Filter by Genre ('Rock') ---");
        ArrayList<Song> filteredSongs = songDAO.filterByGenre("Rock");
        for (Song song : filteredSongs) {
            System.out.println(song);
        }

        System.out.println("\n--- Get Song by ID (" + songId1 + ") ---");
        Song retrievedSong = songDAO.getSongById(songId1);
        if (retrievedSong != null) {
            System.out.println(retrievedSong);
        }

        System.out.println("\n========== UPDATE OPERATIONS ==========");

        System.out.println("\n--- Update Song ---");
        boolean updateResult = songDAO.updateSong(songId1, "Wish You Were Here (Remastered)", 340, "Progressive Rock", 1975);
        if (updateResult) {
            Song updatedSong = songDAO.getSongById(songId1);
            System.out.println("Updated song: " + updatedSong);
        }

        System.out.println("\n--- Update Artist ---");
        boolean artistUpdateResult = artistDAO.updateArtist(artistId2, "Led Zeppelin", "United Kingdom");
        if (artistUpdateResult) {
            Artist updatedArtist = artistDAO.getArtistById(artistId2);
            System.out.println("Updated artist: " + updatedArtist);
        }

        System.out.println("\n========== DELETE OPERATIONS ==========");

        System.out.println("\n--- Delete Song (ID: " + songId2 + ") ---");
        boolean deleteResult = songDAO.deleteSong(songId2);
        if (deleteResult) {
            System.out.println("Song deleted successfully!");

            System.out.println("\n--- All Songs After Deletion ---");
            allSongs = songDAO.getAllSongs();
            for (Song song : allSongs) {
                System.out.println(song);
            }
        }

        System.out.println("\n--- Delete Artist (ID: " + artistId1 + ") ---");
        boolean artistDeleteResult = artistDAO.deleteArtist(artistId1);
        if (artistDeleteResult) {
            System.out.println("Artist deleted successfully (cascade delete for all songs)!");

            System.out.println("\n--- All Songs After Artist Deletion ---");
            allSongs = songDAO.getAllSongs();
            for (Song song : allSongs) {
                System.out.println(song);
            }
        }

        System.out.println("\n========== FINAL DATABASE STATE ==========");
        System.out.println("\n--- All Artists ---");
        allArtists = artistDAO.getAllArtists();
        for (Artist artist : allArtists) {
            System.out.println(artist);
        }

        System.out.println("\n--- All Songs ---");
        allSongs = songDAO.getAllSongs();
        for (Song song : allSongs) {
            System.out.println(song);
        }

        DatabaseConnection.closeConnection();

        System.out.println("\n=== Test Completed ===");
    }
}