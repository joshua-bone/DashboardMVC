package dashboard;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

public class Daily extends DashboardItem{
	private LocalDate index; //outside classes must adjust the date index, then ask for history
	private TreeMap<LocalDate, Double> history = new TreeMap<>(); //change this 
	
	public Daily() {
		super();
		this.setProfile("Daily");
	}

	public Daily(String name){
		this();
		this.setName(name);
	}

	public LocalDate getIndex() {
		return index;
	}

	public void setIndex(LocalDate index) {
		this.index = index;
	}
	
	public TreeMap<LocalDate, Double> getHistory() {
		return history;
	}

	public void setHistory(TreeMap<LocalDate, Double> history) {
		this.history = history;
	}

	@Override
	public int getGrade() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGradeForPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void markComplete() {
		// TODO Auto-generated method stub
		
	}
	
	
}
