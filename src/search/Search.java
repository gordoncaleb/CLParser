package search;

import gui.JGeoMap;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

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

		JFrame frame = new JFrame("Map");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JGeoMap map = new JGeoMap();
		frame.add(map, BorderLayout.CENTER);
		frame.setSize(map.getImageWidth(), map.getImageHeight());

		frame.setVisible(true);

		Search sdc = new Search();

		String product = "iphone 4s";
		int maxListings = 400;
		int filterMin = 300;
		int filterMax = 900;

		City[] cities = City.getCities();

		// for (int i = 0; i < cities.length; i++) {
		// map.addGeoObject(cities[i]);
		// }
		Results[] filteredResults = new Results[cities.length];

		for (int i = 0; i < cities.length; i++) {

			sdc.setCity(cities[i].getName());
			sdc.setProductName(product);
			sdc.setMaxListings(maxListings);

			Results results = new Results(sdc.search());
			filteredResults[i] = results.filterByPrice(filterMin, filterMax);
			filteredResults[i].setCity(cities[i]);
			map.addGeoObject(filteredResults[i]);

			System.out.println("Average of " + filteredResults[i].size() + " listings in " + cities[i].getName() + " for " + product + " is : "
					+ filteredResults[i].getAverage());
		}

		map.repaint();

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
