public class UserPlaylist extends Playlist {
    private String creator;

    public UserPlaylist(String name, String creator) {
        super(name);
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public void printPlaylist() {
        System.out.println("User Playlist: " + name + " (created by " + creator + ")");
        super.printPlaylist();
    }
}

