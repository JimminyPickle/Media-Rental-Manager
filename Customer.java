package mediaRentalManager;

import java.util.ArrayList;

public class Customer extends MediaRentalManager {
	private String name;
	private String address;
	private String plan;
	ArrayList<String> queueReceived = new ArrayList<String>();
	ArrayList<String> queueInterested = new ArrayList<String>();

	public Customer(String name, String address, String plan) {
		if (name == null || name == "" || address == null || address == "") {
			return;
		}
		if (plan != "UNLIMITED" && plan != "LIMITED") {
			return;
		}
		this.name = name;
		this.address = address;
		this.plan = plan;
	}

	public String toString() {
		return "Name: " + name + ", Address: " + address + ", Plan: " + plan + "\n" + "Rented: " + queueReceived + "\n"
				+ "Queue: " + queueInterested + "\n";
	}

	public String getName() {
		return name;
	}
	public String getPlan() {
		return plan;
	}
	@Override
	public boolean addToQueue(String customerName, String mediaTitle) {
		if (customerName == null || customerName.equals("") || mediaTitle == null || mediaTitle == "") {
			return false;
		}
		if (queueInterested.contains(mediaTitle) == true) {
			return false;
		}
		queueInterested.add(mediaTitle);
		return true;
	}

	@Override
	public boolean removeFromQueue(String customerName, String mediaTitle) {
		if (customerName == null || customerName == "" || mediaTitle == null || mediaTitle == "") {
			return false;
		}
		if (queueInterested.contains(mediaTitle)) {
			int position = queueInterested.indexOf(mediaTitle);
			queueInterested.remove(position);
			return true;
		} else {
			return false;
		}
	}		
}
