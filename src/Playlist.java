import java.util.ArrayList;
import java.util.List;

public class Playlist extends MusicItem {
    private List<Song> songs;

    public Playlist(String name) {
        super(name);
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        if (song != null && !songs.contains(song)) {
            songs.add(song);
            System.out.println("Song added to playlist: " + song.getTitle());
        }
    }

    public void removeSong(Song song) {
        if (songs.remove(song)) {
            System.out.println("Song removed from playlist: " + song.getTitle());
        }
    }

    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public int getSongCount() {
        return songs.size();
    }

    @Override
    public String getDetails() {
        return "Playlist: " + name + " (" + songs.size() + " songs)";
    }

    @Override
    public void display() {
        System.out.println("\n=== " + name.toUpperCase() + " ===");
        if (songs.isEmpty()) {
            System.out.println("No songs in this playlist");
        } else {
            for (int i = 0; i < songs.size(); i++) {
                System.out.printf("%2d. %s\n", (i + 1), songs.get(i).getDetails());
            }
        }
    }
}