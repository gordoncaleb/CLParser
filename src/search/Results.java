package search;

import gui.GeoObject;
import gui.JGeoMap;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import parser.City;

public class Results implements GeoObject {

	private ArrayList<Listing> listings;

	private City city;

	public Results(ArrayList<Listing> listings) {
		this.listings = listings;
	}

	public Results() {
		listings = new ArrayList<Listing>();
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void add(Listing l) {
		listings.add(l);
	}

	public int size() {
		return listings.size();
	}

	public Results filterByPrice(int min, int max) {
		ArrayList<Listing> filteredResults = new ArrayList<Listing>();

		for (Listing l : listings) {

			if (l.getPrice() != null) {
				if (l.getPrice() >= min && l.getPrice() <= max) {
					filteredResults.add(l);
				}
			}
		}

		return new Results(filteredResults);
	}

	public double getAverage() {

		double total = 0;

		for (Listing l : listings) {
			if (l.getPrice() != null) {
				total += l.getPrice();
			}
		}

		return total / ((double) listings.size());

	}

	@Override
	public void draw(Graphics2D g2, JGeoMap map) {

		if (city != null) {
			int[] xy = map.getXYFromLatLon(city.getLat(), city.getLon());

			g2.setColor(new Color(1.0f, 0.0f, 0.0f, 0.5f));
			//g2.setColor(Color.red);
			g2.fillOval(xy[0] - 15, xy[1]+20, 30, 30);
			g2.setColor(Color.BLACK);
			g2.drawOval(xy[0] - 15, xy[1]+20, 30, 30);

			FontMetrics fm = g2.getFontMetrics();
			String lbl = "$" + (int) getAverage();
			g2.drawString(lbl, xy[0] - fm.stringWidth(lbl) / 2, xy[1] + 40);

		}

	}

}
