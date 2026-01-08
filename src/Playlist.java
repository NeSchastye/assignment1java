import java.util.ArrayList;
public class Playlist implements Searchable {
    protected String name;
    private ArrayList<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void printPlaylist() {
        System.out.println("Playlist: " + name);
        for (Song song : songs) {
            System.out.println(song);
        }
    }

    @Override
    public ArrayList<Song> searchByTitle(String keyword) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(song);
            }
        }
        return result;
    }

    @Override
    public ArrayList<Song> searchByArtist(String artistName) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getArtist().getName().toLowerCase().contains(artistName.toLowerCase())) {
                result.add(song);
            }
        }
        return result;
    }

    @Override
    public ArrayList<Song> filterByGenre(String genre) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getGenre().equalsIgnoreCase(genre)) {
                result.add(song);
            }
        }
        return result;
    }

    @Override
    public void sortByTitle(boolean ascending) {
        for (int i = 0; i < songs.size() - 1; i++) {
            for (int j = 0; j < songs.size() - 1 - i; j++) {
                boolean needSwap;
                if (ascending) {
                    needSwap = songs.get(j).getTitle().compareTo(songs.get(j + 1).getTitle()) > 0;
                } else {
                    needSwap = songs.get(j).getTitle().compareTo(songs.get(j + 1).getTitle()) < 0;
                }
                if (needSwap) {
                    Song temp = songs.get(j);
                    songs.set(j, songs.get(j + 1));
                    songs.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public void sortByYear(boolean ascending) {
        for (int i = 0; i < songs.size() - 1; i++) {
            for (int j = 0; j < songs.size() - 1 - i; j++) {
                boolean needSwap;
                if (ascending) {
                    needSwap = songs.get(j).getYear() > songs.get(j + 1).getYear();
                } else {
                    needSwap = songs.get(j).getYear() < songs.get(j + 1).getYear();
                }
                if (needSwap) {
                    Song temp = songs.get(j);
                    songs.set(j, songs.get(j + 1));
                    songs.set(j + 1, temp);
                }
            }
        }
    }
}