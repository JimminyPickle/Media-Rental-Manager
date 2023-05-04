package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRentalManager implements MediaRentalManagerInt {
	protected ArrayList<Media> mediaList = new ArrayList<Media>();
	protected ArrayList<Customer> customerList = new ArrayList<Customer>();
	protected int limit = 2;

	public void addCustomer(String name, String address, String plan) {
		Customer newCustomer = new Customer(name, address, plan);
		if (customerList.size() == 0) {
			customerList.add(newCustomer);
		}
		if (!customerList.contains(newCustomer)) {
			for (int i = 0; i < customerList.size(); i++) {
				if (!customerList.contains(newCustomer)) {
					if (customerList.get(i).getName().toLowerCase().compareTo(name.toLowerCase()) > 0) {
						customerList.add(i, newCustomer);
					}
				}
			}
		}
		if (!customerList.contains(newCustomer)) {
			customerList.add(newCustomer);
		}
	}

	public void addMovie(String title, int copiesAvailable, String rating) {
		Movie newMovie = new Movie(title, copiesAvailable, rating);
		if (mediaList.size() == 0) {
			mediaList.add(newMovie);
		} else if (!mediaList.contains(newMovie)) {
			for (int i = 0; i < mediaList.size(); i++) {
				if (!mediaList.contains(newMovie)) {
					if (mediaList.get(i).getTitle().toLowerCase().compareTo(title.toLowerCase()) > 0) {
						mediaList.add(i, newMovie);
					}
				}
			}
		}
		if (!mediaList.contains(newMovie)) {
			mediaList.add(newMovie);
		}
	}

	public void addAlbum(String title, int copiesAvailable, String artist, String songs) {
		Album newAlbum = new Album(title, copiesAvailable, artist, songs);
		if (mediaList.size() == 0) {
			mediaList.add(newAlbum);
		}
		if (!mediaList.contains(newAlbum)) {
			for (int i = 0; i < mediaList.size(); i++) {
				if (!mediaList.contains(newAlbum)) {
					if (mediaList.get(i).getTitle().toLowerCase().compareTo(title.toLowerCase()) > 0) {
						mediaList.add(i, newAlbum);
					}
				}
			}
		}
		if (!mediaList.contains(newAlbum)) {
			mediaList.add(newAlbum);
		}
	}

	public void setLimitedPlanLimit(int value) {
		this.limit = value;
	}

	public String getAllCustomersInfo() {
		String a = "***** Customers' Information *****\n";
		for (int i = 0; i < customerList.size(); i++) {
			a += customerList.get(i).toString();
		}
		return a;
	}

	public String getAllMediaInfo() {
		String a = "***** Media Information *****\n";
		for (int i = 0; i < mediaList.size(); i++) {
			a += mediaList.get(i).toString();
		}
		return a;
	}

	/*
	 * Description what method does: sorts through the customer arrayList in the
	 * database, adding the desired media to the customer queue
	 * 
	 * Returns True if: the array finds the customerName in the arrayList, adding
	 * mediaTitle to the queue
	 * 
	 * Returns False if: customerName isn't found, parameters are null, mediaTitle
	 * isn't found
	 * 
	 */
	public boolean addToQueue(String customerName, String mediaTitle) {
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getName().equals(customerName)) {
				
				return customerList.get(i).addToQueue(customerName, mediaTitle);
			}
		}
		return false;
	}

	public boolean removeFromQueue(String customerName, String mediaTitle) {
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getName().equals(customerName)) {
				return customerList.get(i).removeFromQueue(customerName, mediaTitle);
			}
		}
		return false;
	}

	public String processRequests() {
		String a = "";
		if (customerList.size() == 0) {
			return "";
		}
		for (int customers = 0; customers < customerList.size(); customers++) {
			// System.out.print(customers + "\n\n\n");
			a += processRequestsHelper(customerList.get(customers));
		}
		return a;
	}

	public String processRequestsHelper(Customer c) {
		String a = "";
		int num = c.queueInterested.size();
		int num1 = c.queueReceived.size();
		int num3 = 0;
		if (c.getPlan() == "UNLIMITED") {
			if (c.queueInterested.size() == 0) {
				return "";
			}
			for (int j = 0; j < num; j++) {
				for (int i = 0; i < mediaList.size(); i++) {
					// System.out.println(i + mediaList.get(i).getTitle() + " + " +
					// c.queueInterested.get(0) +
					// mediaList.get(i).getTitle().equals(c.queueInterested.get(0)) + "\n\n\n");
					if (mediaList.get(i).getTitle().equals(c.queueInterested.get(num3))) {
						// System.out.println(mediaList.get(i).getTitle() + "\n\n\n");
						if (mediaList.get(i).getCopiesAvailable() <= 0) {
							num3++;
						}
						if (mediaList.get(i).getCopiesAvailable() > 0) {
							c.queueReceived.add(c.queueInterested.get(num3));
							c.queueInterested.remove(num3);
							mediaList.get(i).setCopiesAvailable(mediaList.get(i).getCopiesAvailable() - 1);
							a += "Sending " + mediaList.get(i).getTitle() + " to " + c.getName() + "\n";
							break;
						}
					}
				}
			}
			return a;
		} else {
			if (c.queueInterested.size() == 0) {
				return "";
			}
			for (int j = 0; j < num; j++) {
				if (num1 >= limit) {
					return a;
				}
				for (int i = 0; i < mediaList.size(); i++) {
					if (num1 >= limit) {
						return a;
					}
					// System.out.println(i + mediaList.get(i).getTitle() + " + " +
					// c.queueInterested.get(0) +
					// mediaList.get(i).getTitle().equals(c.queueInterested.get(0)) + "\n\n\n");
					if (mediaList.get(i).getTitle().equals(c.queueInterested.get(0))) {
						// System.out.println(mediaList.get(i).getTitle() + "\n\n\n");
						if (mediaList.get(i).getCopiesAvailable() <= 0) {
							num3++;
						}
						if (mediaList.get(i).getCopiesAvailable() > 0) {
							c.queueReceived.add(c.queueInterested.get(num3));
							c.queueInterested.remove(num3);
							num1++;
							mediaList.get(i).setCopiesAvailable(mediaList.get(i).getCopiesAvailable() - 1);
							a += "Sending " + mediaList.get(i).getTitle() + " to " + c.getName() + "\n";
							break;
						}
					}
				}
			}
			return a;
		}
	}

	public boolean returnMedia(String customerName, String mediaTitle) {
		boolean a = false;
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getName().equals(customerName)) {
				a = returnMediaAuxiliary(customerList.get(i), mediaTitle);
			}
		}
		return a;
	}

	public boolean returnMediaAuxiliary(Customer c, String mediaTitle) {
		if (c.getName() == null || c.getName() == "" || mediaTitle == null || mediaTitle == "") {
			return false;
		}
		if (c.queueReceived.contains(mediaTitle)) {
			int position = c.queueReceived.indexOf(mediaTitle);
			c.queueReceived.remove(position);
			for (int i = 0; i < mediaList.size(); i++) {
				if (mediaList.get(i).getTitle().equals(mediaTitle)) {
					mediaList.get(i).setCopiesAvailable(mediaList.get(i).getCopiesAvailable() + 1);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
		ArrayList<String> mediaTitles = new ArrayList<String>();
		if (title == null && rating == null && artist == null && songs == null) {
			for (int i = 0; i < mediaList.size(); i++) {
				mediaTitles.add(mediaList.get(i).getTitle());
			}
		}
		if (mediaList.size() == 0) {
			return mediaTitles;
		}
		if (title != null) {
			for (int i = 0; i < mediaList.size(); i++) {
				if (mediaList.get(i).getTitle().equals(title)) {
					if (mediaTitles.size() == 0) {
						mediaTitles.add(title);
					} else if (!mediaTitles.contains(title)) {
						for (int j = 0; j < mediaTitles.size(); j++) {
							if (!mediaTitles.contains(title)) {
								if (mediaTitles.get(j).toLowerCase().compareTo(title.toLowerCase()) > 0) {
									mediaTitles.add(j, title);
								}
							}
						}
					}
					if (!mediaTitles.contains(title)) {
						mediaTitles.add(title);
					}
				}
			}
		} else if (rating != null) {
			for (int i = 0; i < mediaList.size(); i++) {
				if (mediaList.get(i).getClass() == Movie.class) {
					Movie x = (Movie) mediaList.get(i);
					if (x.getRating().equals(rating)) {
						if (mediaTitles.size() == 0) {
							mediaTitles.add(x.getTitle());
						} else if (!mediaTitles.contains(x.getTitle())) {
							for (int j = 0; j < mediaTitles.size(); j++) {
								if (mediaTitles.get(j).toLowerCase().compareTo(x.getTitle().toLowerCase()) > 0) {
									mediaTitles.add(j, x.getTitle());
								}
							}
						}
						if (!mediaTitles.contains(x.getTitle())) {
							mediaTitles.add(x.getTitle());
						}
					}
				}
			}
		} else if (artist != null) {
			for (int i = 0; i < mediaList.size(); i++) {
				if (mediaList.get(i).getClass() == Album.class) {
					Album x = (Album) mediaList.get(i);
					if (x.getArtist().equals(artist)) {
						if (mediaTitles.size() == 0) {
							mediaTitles.add(x.getTitle());
						} else if (!mediaTitles.contains(x.getTitle())) {
							for (int j = 0; j < mediaTitles.size(); j++) {
								if (mediaTitles.get(j).toLowerCase().compareTo(x.getTitle().toLowerCase()) > 0) {
									mediaTitles.add(j, x.getTitle());
								}
							}
						}
						if (!mediaTitles.contains(x.getTitle())) {
							mediaTitles.add(x.getTitle());
						}
					}
				}
			}
		} else if (songs != null) {
			for (int i = 0; i < mediaList.size(); i++) {
				if (mediaList.get(i).getClass() == Album.class) {
					Album x = (Album) mediaList.get(i);
					if (x.getSongs().equals(songs) || x.getSongs().contains(songs)) {
						if (mediaTitles.size() == 0) {
							mediaTitles.add(x.getTitle());
						} else if (!mediaTitles.contains(x.getTitle())) {
							for (int j = 0; j < mediaTitles.size(); j++) {
								if (mediaTitles.get(j).toLowerCase().compareTo(x.getTitle().toLowerCase()) > 0) {
									mediaTitles.add(j, x.getTitle());
								}
							}
						}
						if (!mediaTitles.contains(x.getTitle())) {
							mediaTitles.add(x.getTitle());
						}
					}
				}
			}
		} else {
		}
		return mediaTitles;
	}
}
