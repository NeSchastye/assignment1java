import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MusicLibrary {

    private List<Artist> artists;
    private List<Song> songs;
    private List<Playlist> playlists;

    public MusicLibrary() {
        this.artists = new ArrayList<>();
        this.songs = new ArrayList<>();
        this.playlists = new ArrayList<>();
        System.out.println("Music Library created");
    }

    public void addArtist(Artist artist) {
        if (artist != null && !artists.contains(artist)) {
            artists.add(artist);
            System.out.println("Artist added: " + artist.getName());
        } else if (artists.contains(artist)) {
            System.out.println("Artist already exists: " + artist.getName());
        } else {
            System.out.println("Artist = null");
        }
    }

    public List<Artist> getAllArtists() {
        return new ArrayList<>(artists);
    }

    public Artist findArtistByName(String name) {
        for (Artist artist : artists) {
            if (artist.getName().equalsIgnoreCase(name)) {
                return artist;
            }
        }
        return null;
    }

    public List<Artist> filterArtistsByCountry(String country) {
        List<Artist> results = new ArrayList<>();
        for (Artist artist : artists) {
            if (artist.getCountry().equalsIgnoreCase(country)) {
                results.add(artist);
            }
        }
        return results;
    }

    public void sortArtistsByName() {
        artists.sort(Comparator.comparing(Artist::getName, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Sorted artists by name");
    }

    public boolean removeArtist(Artist artist) {
        if (artists.remove(artist)) {
            System.out.println("Artist deleted: " + artist.getName());
            return true;
        }
        System.out.println("Artist not found");
        return false;
    }

    public void displayAllArtists() {
        System.out.println("\n=== ALL ARTISTS ===");
        if (artists.isEmpty()) {
            System.out.println("No artists found in library");
        } else {
            for (int i = 0; i < artists.size(); i++) {
                System.out.printf("%2d. %s\n", (i + 1), artists.get(i).getDetails());
            }
        }
    }

    public void addSong(Song song) {
        if (song != null && !songs.contains(song)) {
            songs.add(song);
            System.out.println("Song added: " + song.getTitle());
        } else if (songs.contains(song)) {
            System.out.println("Song already exists: " + song.getTitle());
        } else {
            System.out.println("Song = null");
        }
    }

    public List<Song> getAllSongs() {
        return new ArrayList<>(songs);
    }

    public Song findSongByTitle(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

    public List<Song> searchSongsByKeyword(String keyword) {
        List<Song> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Song song : songs) {
            if (song.getTitle().toLowerCase().contains(lowerKeyword) ||
                    song.getArtist().getName().toLowerCase().contains(lowerKeyword)) {
                results.add(song);
            }
        }
        return results;
    }

    public List<Song> filterSongsByArtist(Artist artist) {
        List<Song> results = new ArrayList<>();
        for (Song song : songs) {
            if (song.getArtist().equals(artist)) {
                results.add(song);
            }
        }
        return results;
    }

    public List<Song> filterSongsByMinDuration(int minSeconds) {
        List<Song> results = new ArrayList<>();
        for (Song song : songs) {
            if (song.getDuration() >= minSeconds) {
                results.add(song);
            }
        }
        return results;
    }

    public void sortSongsByTitle() {
        songs.sort(Comparator.comparing(Song::getTitle, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Songs sorted by name");
    }

    public void sortSongsByDuration() {
        songs.sort(Comparator.comparingInt(Song::getDuration));
        System.out.println("Songs sorted by length");
    }

    public boolean removeSong(Song song) {
        if (songs.remove(song)) {
            System.out.println("Song deleted: " + song.getTitle());
            return true;
        }
        System.out.println("Song not found");
        return false;
    }

    public void displayAllSongs() {
        System.out.println("\n=== ALL SONGS ===");
        if (songs.isEmpty()) {
            System.out.println("No songs found in library");
        } else {
            for (int i = 0; i < songs.size(); i++) {
                System.out.printf("%2d. %s\n", (i + 1), songs.get(i).getDetails());
            }
        }
    }

    public void addPlaylist(Playlist playlist) {
        if (playlist != null && !playlists.contains(playlist)) {
            playlists.add(playlist);
            System.out.println("Playlist added: " + playlist.getName());
        } else if (playlists.contains(playlist)) {
            System.out.println("Playlist already exists: " + playlist.getName());
        } else {
            System.out.println("Playlist = null");
        }
    }

    public List<Playlist> getAllPlaylists() {
        return new ArrayList<>(playlists);
    }

    public Playlist findPlaylistByName(String name) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equalsIgnoreCase(name)) {
                return playlist;
            }
        }
        return null;
    }

    public boolean removePlaylist(Playlist playlist) {
        if (playlists.remove(playlist)) {
            System.out.println("Playlist deleted: " + playlist.getName());
            return true;
        }
        System.out.println("Playlist not found");
        return false;
    }

    public void displayAllPlaylists() {
        System.out.println("\n=== ALL PLAYLISTS ===");
        if (playlists.isEmpty()) {
            System.out.println("No playlists found in library");
        } else {
            for (int i = 0; i < playlists.size(); i++) {
                System.out.printf("%2d. %s\n", (i + 1), playlists.get(i).getDetails());
            }
        }
    }

    public void displayStatistics() {
        System.out.println("\n=== LIBRARY STATS ===");
        System.out.println("Artist count: " + artists.size());
        System.out.println("Songs count: " + songs.size());
        System.out.println("Playlist count: " + playlists.size());

        int totalPlaylistSongs = 0;
        for (Playlist p : playlists) {
            totalPlaylistSongs += p.getSongCount();
        }
        System.out.println("Total songs in playlists: " + totalPlaylistSongs);
    }

    public void displayMusicItem(MusicItem item) {
        if (item != null) {
            item.display();
        }
    }
}
