package search;

public class Listing {

	private String date;
	private String title;
	private Integer price;
	private String subLocation;
	private String cat;
	private String by;

	private String detailURL;

	public Listing() {

		date = "";
		title = "";
		price = -1;
		subLocation = "";
		cat = "";
		by = "";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setPrice(String price) {

		if (price.length() == 0) {
			this.price = null;
			return;
		}

		try {
			this.price = Integer.parseInt(price.replace("$", "").trim());
		} catch (Exception e) {
			this.price = null;
			System.out.println("Error parsing price for :" + price);
		}
	}

	public String getSubLocation() {
		return subLocation;
	}

	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getDetailURL() {
		return detailURL;
	}

	public void setDetailURL(String detailURL) {
		this.detailURL = detailURL;
	}

	public String toString() {
		return "Title: " + title + "\nPrice: " + price + "\nDate: " + date;
	}

}
