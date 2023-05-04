package mediaRentalManager;

public class Album extends Media {
	private String artist;
	private String songs;

	public Album(String title, int copiesAvailable, String artist, String songs) {
		super(title, copiesAvailable);
		this.artist = artist;
		this.songs = songs;
	}

	public String getArtist() {
		return artist;
	}

	public String getSongs() {
		return songs;
	}

	public String toString() {
		return "Title: " + super.getTitle() + ", Copies Available: " + super.getCopiesAvailable() + ", Artist: "
				+ artist + ", Songs: " + songs;
	}
}
