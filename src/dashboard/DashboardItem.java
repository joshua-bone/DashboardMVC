package dashboard;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.UUID;

import org.springframework.web.util.UriUtils;

public abstract class DashboardItem { 
	private CustomID customID;
	private ArrayList<IndexCard> parents;
	private String name;
	private String profile;
	private String notes;
	
	public abstract TreeMap<LocalDate, Double> getHistory();
	public abstract int getGrade();
	public abstract int getGradeForPeriod();
	public abstract void markComplete();
	
	public DashboardItem(){
		parents = new ArrayList<>();
		customID = new CustomID();
	}
	
	public ArrayList<IndexCard> getParents(){
		return parents;
	}
	
	public void setParents(ArrayList<IndexCard> parents) {
		this.parents = parents;
	}
	
	public String getCustomID(){
		return customID.toString();
	}
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customID == null) ? 0 : customID.hashCode());
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
		DashboardItem other = (DashboardItem) obj;
		if (customID == null) {
			if (other.customID != null)
				return false;
		} else if (!customID.equals(other.customID))
			return false;
		return true;
	}	
}
