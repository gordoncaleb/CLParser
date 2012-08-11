package objects;

import java.util.ArrayList;

public class Page {
	
	private ArrayList<Link> links;
	
	public Page(){
		links = new ArrayList<Link>();
	}
	
	public void addLink(Link link){
		links.add(link);
	}

}
