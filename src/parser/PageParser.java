package parser;

import java.util.ArrayList;

import search.Listing;

public class PageParser {

	public static void main(String[] args) {

	}

	public ArrayList<Listing> getListings(String page) {
		ArrayList<Listing> listings = new ArrayList<Listing>();

		// parse listings from sb

		int next;
		int end;
		String l;
		Listing listing;

		while ((next = page.indexOf("<p class=\"row\">")) != -1) {

			page = page.substring(next);
			end = page.indexOf("</p>");

			l = page.substring(next, end);

			listing = parseListing(l);

			if (listing != null) {
				listings.add(listing);
			}
		}

		return listings;
	}

	private Listing parseListing(String l) {
		Listing listing = new Listing();
		
		

		return listing;
	}

}
