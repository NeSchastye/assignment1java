public class Album extends Playlist {
    private Artist artist;
    private int releaseYear;

    public Album(String name, Artist artist, int releaseYear) {
        super(name);
        this.artist = artist;
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public void printPlaylist() {
        System.out.println("Album: " + super.name + " by " + artist.getName() + " (" + releaseYear + ")");
        super.printPlaylist();
    }
}