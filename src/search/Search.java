package search;

import utils.Web;

public class Search {
	
	private String city;
	private String productName;
	private String productType;
	
	private int maxListings;
	
	
	public static void main(String[] args) {
		
	}
	
	public Search(){
		
	}
	
	public String search() {

		productName = productName.replaceAll(" ", "+");

		String url = "http://" + city + ".craigslist.org/search/moa?query=" + productName + "&srchType=A&minAsk=&maxAsk=";

		return Web.getPage(url);
	}

}
