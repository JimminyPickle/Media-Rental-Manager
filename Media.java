package mediaRentalManager;

public class Media extends MediaRentalManager{
	private String title = "";
	private int copiesAvailable;

	public Media(String title, int copiesAvailable) {
		if (title == null || title == "") {
			return;
		}
		this.title = title;
		this.copiesAvailable = copiesAvailable;
	}
	public int getCopiesAvailable() {
		return copiesAvailable;
	}
	public void setCopiesAvailable(int num) {
		this.copiesAvailable = num;
	}
	public String getTitle() {
		return title;
	}

}
