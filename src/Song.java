public class Song {
    private String title;
    private Artist artist;
    private int duration; // seconds
    private String genre;
    private int year;

    public Song(String title, Artist artist, int duration, String genre, int year) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Song{title='" + title + "', artist=" + artist.getName() +
                ", duration=" + duration + " sec, genre=" + genre + ", year=" + year + "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return year == song.year &&
                title.equals(song.title) &&
                artist.equals(song.artist);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + artist.hashCode();
        result = 31 * result + year;
        return result;
    }
}