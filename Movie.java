package mediaRentalManager;

public class Movie extends Media {
	private String rating;

	public Movie(String title, int copiesAvailable, String rating) {
		super(title, copiesAvailable);
		if (rating == "PG" || rating == "R" || rating == "NR") {
			this.rating = rating;
		}
	}
	
	public String getRating() {
		return rating;
	}

	public String toString() {
		return "Title: " + super.getTitle() + ", Copies Available: " + super.getCopiesAvailable() + ", Rating: "
				+ rating + "\n";
	}
}
