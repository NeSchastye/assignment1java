public class Playlist {
    private String name;
    private Song[] songs;
    private int count;

    public Playlist(String name, int size) {
        this.name = name;
        songs = new Song[size];
        count = 0;
    }

    public void addSong(Song song) {
        if (count < songs.length) {
            songs[count++] = song;
        }
    }

    public void printPlaylist() {
        System.out.println("Playlist: " + name);
        for (int i = 0; i < count; i++) {
            System.out.println(songs[i]);
        }
    }
}