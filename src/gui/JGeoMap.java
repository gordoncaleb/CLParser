package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JLabel;

import utils.FileIO;

public class JGeoMap extends JLabel {

	private ArrayList<GeoObject> geoObjs = new ArrayList<GeoObject>();

	private BufferedImage mapImage;

	public JGeoMap() {
		super();

		mapImage = FileIO.getImage("img/usa.png");

	}

	public int getImageWidth() {
		return mapImage.getWidth();
	}

	public int getImageHeight() {
		return mapImage.getHeight();
	}

	public void addGeoObject(GeoObject geoObj) {
		geoObjs.add(geoObj);
		this.repaint();
	}

	public int[] getXYFromLatLon(double lat, double lon) {
		int[] xy = new int[2];

		double left = -130.5;
		double right = -65.2;
		double top = 50.6;
		double bottom = 21.3;

		double lonwidth = right - left;
		double latheight = top - bottom;

		int w = this.getWidth();// getImageWidth();
		int h = this.getHeight();// getImageHeight();

		int x = (int) Math.round(((lon - left) * (((double) w) / lonwidth)));
		//int y = (int) Math.round(((Math.cos(lat) - Math.cos(top)) * (Math.cos(bottom)-Math.cos(top))));
		int y = (int) Math.round(((top-lat) * (double)h/latheight));

		xy[0] = x;
		xy[1] = y;

		return xy;
	}

	public void paint(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.drawImage(mapImage, 0, 0, this.getWidth(), this.getHeight(), null);

		for (int i = 0; i < geoObjs.size(); i++) {
			geoObjs.get(i).draw(g2, this);
		}

		g.dispose();

	}

}
