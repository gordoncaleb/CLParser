package parser;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import gui.GeoObject;
import gui.JGeoMap;

public class City implements GeoObject {

	public static final City ATLANTA = new City("atlanta", 33.7489, -84.3881);
	public static final City AUSTIN = new City("austin", 30.2669, -97.7428);
	public static final City BOSTON = new City("boston", 42.3583, -71.0603);
	public static final City CHICAGO = new City("chicago", 41.85, -87.65);
	public static final City DALLAS = new City("dallas", 32.7828, -96.8039);
	public static final City DENVER = new City("denver", 39.7392, -104.9842);
	public static final City DETROIT = new City("detroit", 42.3314, -83.0458);
	public static final City HOUSTON = new City("houston", 29.7631, -95.3631);
	public static final City LASVEGAS = new City("lasvegas", 36.08, -115.1522);
	public static final City LOSANGELES = new City("losangeles", 34.0522, -118.2428);
	public static final City MIAMI = new City("miami", 25.82, -80.28);
	public static final City MINNEAPOLIS = new City("minneapolis", 44.98, -93.2636);
	public static final City NYC = new City("newyork", 40.7142, -74.0064);
	public static final City ORANGECO = new City("orangecounty", 33.7315, -117.8617);
	public static final City PHILIADELPHIA = new City("philiadelphia", 39.9522, -75.1642);
	public static final City PHOENIX = new City("phoenix", 33.43, -112.02);
	public static final City PORTLAND = new City("portland", 45.5236, -122.6750);
	public static final City RALEIGH = new City("raleigh", 35.7719, -78.6389);
	public static final City SACRAMENTO = new City("sacramento", 38.5817, -121.4933);
	public static final City SANDIEGO = new City("sandeigo", 32.7153, -117.1564);
	public static final City SEATTLE = new City("seattle", 47.6097, -122.3331);
	public static final City SANFRANBAY = new City("sfbay", 37.7750, -122.4183);
	public static final City WASHINGTONDC = new City("washingtondc", 38.89, -77.03);
	
	//public static final City TEST = new City("test", 21.3, -65.2);

	private static City[] cities = { ATLANTA, AUSTIN, BOSTON, CHICAGO, DALLAS, DENVER, DETROIT, HOUSTON, LASVEGAS, LOSANGELES, MIAMI, MINNEAPOLIS,
			NYC, ORANGECO, PHILIADELPHIA, PHOENIX, PORTLAND, RALEIGH, SACRAMENTO, SANDIEGO, SEATTLE, SANFRANBAY, WASHINGTONDC };

	public static City[] getCities() {
		return cities;
	}

	private String name;
	private double lat;
	private double lon;

	public City(String name, double lat, double lon) {
		this.name = name;
		this.lat = lat;
		this.lon = lon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	@Override
	public void draw(Graphics2D g2, JGeoMap map) {
		int[] xy = map.getXYFromLatLon(lat, lon);

		g2.setColor(Color.RED);
		g2.fillOval(xy[0] - 1, xy[1] -1, 2, 2);

		FontMetrics fm = g2.getFontMetrics();
		String lbl = name;
		g2.drawString(lbl, xy[0] - fm.stringWidth(lbl) / 2, xy[1] + 20);
	}

}
