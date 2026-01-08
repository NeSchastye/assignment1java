import java.util.ArrayList;

public interface Searchable {
    ArrayList<Song> searchByTitle(String keyword);
    ArrayList<Song> searchByArtist(String artistName);
    ArrayList<Song> filterByGenre(String genre);
    void sortByTitle(boolean ascending);
    void sortByYear(boolean ascending);
}