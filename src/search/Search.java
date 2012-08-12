package search;

import java.util.ArrayList;

import parser.PageParser;

import utils.Web;

public class Search {

	private String city;
	private String productName;
	private String productType;

	private int minAsk;
	private int maxAsk;

	private int maxListings;

	public static void main(String[] args) {

	}

	public Search() {

	}

	public ArrayList<Listing> search() {

		productName = productName.replaceAll(" ", "+");

		String url = "http://" + city + ".craigslist.org/search/moa?query=" + productName + "&srchType=A";

		if (minAsk != 0 && maxAsk != 0) {
			url += "&minAsk=" + minAsk + "&maxAsk=" + maxAsk;
		}

		// round up to nearest 100 beacuse cl search shows pages of 100 at a
		// atime
		int getPages = (int) (Math.ceil(((double) maxListings) / 100) * 100);

		ArrayList<Listing> results = new ArrayList<Listing>();
		String startAt = "";
		for (int i = 0; i < getPages; i++) {

			if (i > 0) {
				startAt = "&s=" + i * 100;
			}

			results.addAll(PageParser.getListings(Web.getPage(url + startAt)));
		}

		return results;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getMinAsk() {
		return minAsk;
	}

	public void setMinAsk(int minAsk) {
		this.minAsk = minAsk;
	}

	public int getMaxAsk() {
		return maxAsk;
	}

	public void setMaxAsk(int maxAsk) {
		this.maxAsk = maxAsk;
	}

	public int getMaxListings() {
		return maxListings;
	}

	public void setMaxListings(int maxListings) {
		this.maxListings = maxListings;
	}
	
}
