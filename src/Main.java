import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Playlist> playlists = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Music Application ===");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = getIntInput("Choose an action: ");

            switch (choice) {
                case 1:
                    createPlaylist();
                    break;
                case 2:
                    addSongToPlaylist();
                    break;
                case 3:
                    viewAllPlaylists();
                    break;
                case 4:
                    searchInPlaylist();
                    break;
                case 5:
                    sortPlaylist();
                    break;
                case 6:
                    filterByGenre();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Create playlist");
        System.out.println("2. Add song to playlist");
        System.out.println("3. View all playlists");
        System.out.println("4. Search in playlist");
        System.out.println("5. Sort playlist");
        System.out.println("6. Filter by genre");
        System.out.println("0. Exit");
    }

    private static void initializeTestData() {
        Artist artist1 = new Artist("The Beatles", "UK");
        Artist artist2 = new Artist("Queen", "UK");
        Artist artist3 = new Artist("Nirvana", "USA");

        Album album1 = new Album("Abbey Road", artist1, 1969);
        album1.addSong(new Song("Come Together", artist1, 259, "Rock", 1969));
        album1.addSong(new Song("Something", artist1, 182, "Rock", 1969));
        album1.addSong(new Song("Here Comes The Sun", artist1, 185, "Rock", 1969));
        playlists.add(album1);

        UserPlaylist userPlaylist = new UserPlaylist("My Rock Mix", "User");
        userPlaylist.addSong(new Song("Bohemian Rhapsody", artist2, 354, "Rock", 1975));
        userPlaylist.addSong(new Song("Smells Like Teen Spirit", artist3, 301, "Grunge", 1991));
        playlists.add(userPlaylist);

    }

    private static void createPlaylist() {
        System.out.println("\n--- Create Playlist ---");
        System.out.println("1. Album");
        System.out.println("2. User Playlist");
        int type = getIntInput("Choose type: ");

        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();

        if (type == 1) {
            System.out.print("Artist name: ");
            String artistName = scanner.nextLine();
            System.out.print("Artist country: ");
            String country = scanner.nextLine();
            int year = getIntInput("Release year: ");

            Artist artist = new Artist(artistName, country);
            Album album = new Album(name, artist, year);
            playlists.add(album);
            System.out.println("Album created!");
        } else if (type == 2) {
            System.out.print("Creator name: ");
            String creator = scanner.nextLine();

            UserPlaylist playlist = new UserPlaylist(name, creator);
            playlists.add(playlist);
            System.out.println("Playlist created!");
        } else {
            System.out.println("Invalid type.");
        }
    }

    private static void addSongToPlaylist() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists available.");
            return;
        }

        System.out.println("\n--- Add Song ---");
        listPlaylists();
        int index = getIntInput("Choose playlist (number): ") - 1;

        if (index < 0 || index >= playlists.size()) {
            System.out.println("Invalid playlist number.");
            return;
        }

        scanner.nextLine();
        System.out.print("Song title: ");
        String title = scanner.nextLine();
        System.out.print("Artist name: ");
        String artistName = scanner.nextLine();
        System.out.print("Artist country: ");
        String country = scanner.nextLine();
        int duration = getIntInput("Duration (sec): ");
        scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        int year = getIntInput("Year: ");

        Artist artist = new Artist(artistName, country);
        Song song = new Song(title, artist, duration, genre, year);
        playlists.get(index).addSong(song);
        System.out.println("Song added!");
    }

    private static void viewAllPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists available.");
            return;
        }

        System.out.println("\n--- All Playlists ---");
        for (Playlist playlist : playlists) {
            playlist.printPlaylist();
            System.out.println();
        }
    }

    private static void searchInPlaylist() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists available.");
            return;
        }

        System.out.println("\n--- Search ---");
        listPlaylists();
        int index = getIntInput("Choose playlist (number): ") - 1;

        if (index < 0 || index >= playlists.size()) {
            System.out.println("Invalid playlist number.");
            return;
        }

        System.out.println("1. Search by title");
        System.out.println("2. Search by artist");
        int searchType = getIntInput("Choose search type: ");

        scanner.nextLine();
        ArrayList<Song> results = new ArrayList<>();

        if (searchType == 1) {
            System.out.print("Enter keyword: ");
            String keyword = scanner.nextLine();
            results = playlists.get(index).searchByTitle(keyword);
        } else if (searchType == 2) {
            System.out.print("Enter artist name: ");
            String artistName = scanner.nextLine();
            results = playlists.get(index).searchByArtist(artistName);
        } else {
            System.out.println("Invalid search type.");
            return;
        }

        displaySearchResults(results);
    }

    private static void sortPlaylist() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists available.");
            return;
        }

        System.out.println("\n--- Sort ---");
        listPlaylists();
        int index = getIntInput("Choose playlist (number): ") - 1;

        if (index < 0 || index >= playlists.size()) {
            System.out.println("Invalid playlist number.");
            return;
        }

        System.out.println("1. By title");
        System.out.println("2. By year");
        int sortType = getIntInput("Choose sort type: ");

        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        int order = getIntInput("Choose order: ");
        boolean ascending = (order == 1);

        if (sortType == 1) {
            playlists.get(index).sortByTitle(ascending);
            System.out.println("Playlist sorted by title.");
        } else if (sortType == 2) {
            playlists.get(index).sortByYear(ascending);
            System.out.println("Playlist sorted by year.");
        } else {
            System.out.println("Invalid sort type.");
        }
    }

    private static void filterByGenre() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists available.");
            return;
        }

        System.out.println("\n--- Filter by Genre ---");
        listPlaylists();
        int index = getIntInput("Choose playlist (number): ") - 1;

        if (index < 0 || index >= playlists.size()) {
            System.out.println("Invalid playlist number.");
            return;
        }

        scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        ArrayList<Song> results = playlists.get(index).filterByGenre(genre);
        displaySearchResults(results);
    }

    private static void listPlaylists() {
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).name);
        }
    }

    private static void displaySearchResults(ArrayList<Song> results) {
        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println("\nSongs found: " + results.size());
            for (Song song : results) {
                System.out.println(song);
            }
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}