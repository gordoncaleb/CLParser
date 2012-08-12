package parser;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import search.Listing;
import utils.FileIO;

public class PageParser {

	public static void main(String[] args) {

		String testPage = FileIO.readFile("testpage.txt");

		ArrayList<Listing> listings = PageParser.getListings(testPage);

	}

	public static ArrayList<Listing> getListings(String page) {
		ArrayList<Listing> listings = new ArrayList<Listing>();

		// parse listings from sb

		int next;
		int end;
		String l;
		Listing listing;

		String startTag = "<p class=\"row\">";
		String endTag = "</p>";

		while ((next = page.indexOf(startTag)) != -1) {

			page = page.substring(next);
			end = page.indexOf(endTag) + endTag.length();

			l = page.substring(0, end);

			listing = parseListing(l);

			if (listing != null) {
				listings.add(listing);
			}
		}

		return listings;
	}

	private static Listing parseListing(String l) {
		Listing listing = new Listing();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			Document dom = db.parse(l);

			Element docEle = dom.getDocumentElement();

			NodeList nl = docEle.getElementsByTagName("p");

			if (nl != null && nl.getLength() > 0) {
				for (int i = 0; i < nl.getLength(); i++) {

					Element el = (Element) nl.item(i);

					String classid = el.getAttribute("class");
					String value = el.getFirstChild().getNodeValue();

				}
			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return listing;
	}

	private static void setListingValue(Listing listing, String classid, String value) {

	}

}
