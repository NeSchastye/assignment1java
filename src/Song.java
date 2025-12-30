import java.util.Objects;
public class Song extends MusicItem {
    private Artist artist;
    private int duration; // seconds

    public Song(String title, Artist artist, int duration) {
        super(title);
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
            return name;
    }

    public void setTitle(String title) {
        this.name = title;
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
    public String getFormattedDuration() {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
    @Override
    public String toString() {
        return "Song{title='" + name + "', artist=" + artist.getName() +
                ", duration=" + getFormattedDuration() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return duration == song.duration &&
                Objects.equals(name, song.name) &&
                Objects.equals(artist, song.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, artist, duration);
    }
    @Override
    public String getDetails() {
        return "Song: " + name + " by " + artist.getName() +
                " [" + getFormattedDuration() + "]";
    }
    @Override
    public void display() {
        System.out.println(getDetails());
    }
}
