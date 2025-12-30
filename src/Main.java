import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MusicLibrary library = new MusicLibrary();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== MUSIC LIBRARY ===\n");

        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();
        System.out.print("Enter country: ");
        String country = scanner.nextLine();
        Artist artist = new Artist(artistName, country);
        library.addArtist(artist);

        System.out.print("\nEnter song title: ");
        String songTitle = scanner.nextLine();
        System.out.print("Enter duration (seconds): ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        Song song = new Song(songTitle, artist, duration);
        library.addSong(song);

        System.out.print("\nEnter playlist name: ");
        String playlistName = scanner.nextLine();
        Playlist playlist = new Playlist(playlistName);
        playlist.addSong(song);
        library.addPlaylist(playlist);

        System.out.println("\n=== POLYMORPHISM DEMO ===");
        library.displayMusicItem(artist);
        library.displayMusicItem(song);
        library.displayMusicItem(playlist);

        library.displayStatistics();

        scanner.close();
    }
}