package parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import search.Listing;
import utils.FileIO;

public class PageParser {

	private static final String xmlprepend = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!DOCTYPE some_name [ <!ENTITY nbsp \"&#160;\"> ]>";

	public static void main(String[] args) {

		String testPage = FileIO.readFile("testpage.txt");

		ArrayList<Listing> listings = PageParser.getListings(testPage);

	}

	public static ArrayList<Listing> getListings(String page) {
		ArrayList<Listing> listings = new ArrayList<Listing>();

		// parse listings from sb

		System.out.println("Getting listings for page");
		int start = 0;
		int end = 0;

		Listing listing;

		String startTag = "<p class=\"row\"";
		String endTag = "</p>";

		StringBuilder l;
		StringBuilder sb = new StringBuilder(page);

		while ((start = sb.indexOf(startTag, end)) != -1) {

			// sb.delete(0, start);
			end = sb.indexOf(endTag, start) + endTag.length();

			l = new StringBuilder(xmlprepend + sb.substring(start, end));

			listing = parseListing(l);

			if (listing != null) {
				listings.add(listing);
			}

		}
		
		System.out.println("Got " + listings.size() + " listings");

		return listings;
	}

	private static Listing parseListing(StringBuilder l) {

		html2xhtml(l);

		// System.out.println(l);
		Listing listing = new Listing();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			Document dom = db.parse(new InputSource(new ByteArrayInputStream(l.toString().getBytes("utf-8"))));

			Element docEle = dom.getDocumentElement();

			NodeList spans = docEle.getElementsByTagName("span");

			Element el;
			String classid;
			String value;

			if (spans != null && spans.getLength() > 0) {
				for (int i = 0; i < spans.getLength(); i++) {

					el = (Element) spans.item(i);

					classid = el.getAttribute("class");

					if (el.getFirstChild() != null) {
						value = el.getFirstChild().getNodeValue();
					} else {
						value = "";
					}

					setListingValue(listing, classid, value);
				}
			}

			NodeList as = docEle.getElementsByTagName("a");

			if (as != null && as.getLength() > 0) {
				el = (Element) as.item(0);

				listing.setDetailURL(el.getAttribute("href"));
				listing.setTitle(el.getFirstChild().getNodeValue());
			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		System.out.println(listing.toString());
		return listing;
	}

	private static void setListingValue(Listing listing, String classid, String value) {
		if (classid.equals("itemdate")) {
			listing.setDate(value);
		} else {
			if (classid.equals("itempp")) {
				listing.setPrice(value);
			}
		}
	}

	/**
	 * Adds end tag </br> for html's start tag <br>
	 * . XML tags have to have end tags.
	 * 
	 * @param sb
	 *            html string to be converted
	 */
	private static void html2xhtml(StringBuilder sb) {
		int start = 0;
		int end = 0;

		while ((start = sb.indexOf("<br", end)) >= 0) {
			end = sb.indexOf(">", start);
			if (end > 0) {
				sb.insert(end + 1, "</br>");
			}
		}
	}

}
