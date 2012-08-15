package search;

import java.util.ArrayList;

import parser.City;
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

		Search sdc = new Search();

		sdc.setCity(City.WASHINGTONDC);
		sdc.setProductName("Iphone 4s");
		sdc.setMaxListings(100);

		ArrayList<Listing> dcResults = sdc.search();
		ArrayList<Listing> filteredDCResults = new ArrayList<Listing>();

		int filteredDCTot = 0;

		for (Listing l : dcResults) {
			System.out.println(l);

			if (l.getPrice() != null) {
				if (l.getPrice() > 100 && l.getPrice() < 900) {
					filteredDCResults.add(l);
					filteredDCTot += l.getPrice();
				}
			}
		}

		Search sbay = new Search();

		sbay.setCity(City.SFBAY);
		sbay.setProductName("Iphone 4s");
		sbay.setMaxListings(100);

		ArrayList<Listing> sfbayResults = sbay.search();
		ArrayList<Listing> filteredSFResults = new ArrayList<Listing>();

		int filteredSFTot = 0;

		for (Listing l : sfbayResults) {
			System.out.println(l);

			if (l.getPrice() != null) {
				if (l.getPrice() > 100 && l.getPrice() < 900) {
					filteredSFResults.add(l);
					filteredSFTot += l.getPrice();
				}
			}
		}
		
		
		System.out.println("Average in DC for Iphone 4s = " + filteredDCTot / filteredDCResults.size());
		System.out.println("Average in SF Bay for Iphone 4s = " + filteredSFTot / filteredSFResults.size());

	}

	public Search() {

	}

	public ArrayList<Listing> search() {

		productName = productName.replaceAll(" ", "%20");

		String url = "http://" + city + ".craigslist.org/search/moa?query=" + productName + "&srchType=A";

		if (minAsk != 0 && maxAsk != 0) {
			url += "&minAsk=" + minAsk + "&maxAsk=" + maxAsk;
		}

		// round up to nearest 100 beacuse cl search shows pages of 100 at a
		// atime
		int getPages = (int) (Math.ceil(((double) maxListings) / 100));

		System.out.println("Get # of pages = " + getPages);

		ArrayList<Listing> results = new ArrayList<Listing>();
		ArrayList<Listing> tempResults = new ArrayList<Listing>();
		String startAt = "";
		for (int i = 0; i < getPages; i++) {

			if (i > 0) {
				startAt = "&s=" + i * 100;
			}

			tempResults = PageParser.getListings(Web.getPage(url + startAt));

			if (tempResults.size() > 0) {
				results.addAll(tempResults);
			} else {
				break;
			}
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
