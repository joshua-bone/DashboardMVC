package dashboard;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.web.util.UriUtils;


public class IndexCard implements Comparable<IndexCard>{
	private CustomID customID;
	private String name;
	// can have multiple parents, but should never
	// have a child as a parent or vice versa
	private ArrayList<IndexCard> children;
	private ArrayList<IndexCard> parents;
	private ArrayList<DashboardItem> items;

	// CONSTRUCTOR
	public IndexCard(String n) { 
		name = n;
		customID = new CustomID();
		children = new ArrayList<>();
		parents = new ArrayList<>();
		items = new ArrayList<>();
	}
	


	public IndexCard(){
		this("Default");
	}
	
	public String getUri(){
		try{
			return UriUtils.encode(name, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException uce){
			System.err.println("Error encoding name: " + name);
			return name;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<IndexCard> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<IndexCard> children) {
		this.children = children;
	}

	public ArrayList<IndexCard> getParents() {
		return parents;
	}

	public void setParents(ArrayList<IndexCard> parents) {
		this.parents = parents;
	}
	
	public ArrayList<DashboardItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<DashboardItem> items) {
		this.items = items;
	}
	
	public String getCustomID() {
		return customID.toString();
	}
	
	public int getNumItems(){
		if (this.children.size() == 0){
			return this.items.size();
		} else {
			int n = this.items.size();
			for (IndexCard child : this.children) {
				n += child.getNumItems();
			}
			return n;
		}
	}
	
	public int compareTo(IndexCard o){
		return this.getName().toLowerCase().compareTo(o.getName().toLowerCase());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.toLowerCase().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndexCard other = (IndexCard) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equalsIgnoreCase(other.name))
			return false;
		return true;
	}
}
