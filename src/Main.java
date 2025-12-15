public class Main {
    public static void main(String[] args) {

        Artist artist1 = new Artist("Linkin Park", "USA");
        Artist artist2 = new Artist("Kairat Nurtas", "KZ");

        Song song1 = new Song("Numb", artist1, 210);
        Song song2 = new Song("Almaty Tuny", artist2, 205);

        Playlist playlist = new Playlist("Playlist1", 5);

        playlist.addSong(song1);
        playlist.addSong(song2);

        playlist.printPlaylist();
        if (song1.getDuration() > song2.getDuration()) {
            System.out.println(song1.getTitle() + " is longer than " + song2.getTitle());
        } else {
            System.out.println(song2.getTitle() + " is longer than " + song1.getTitle());
        }
    }
}
