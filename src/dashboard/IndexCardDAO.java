package dashboard;


import java.util.TreeSet;
import java.util.UUID;

public interface IndexCardDAO {
	public boolean setParent(IndexCard c, IndexCard p);
	public boolean setChild(IndexCard p, IndexCard c);
	public boolean isChild(IndexCard p, IndexCard c);
	public boolean isParent(IndexCard c, IndexCard p);
	public boolean add(String n);
	public boolean remove(String n);
	public TreeSet<IndexCard> getCardSet();
	public IndexCard get(String cidOrName);
	public IndexCard getCurrentCard();
	public void setCurrentCard(IndexCard c);
	public void updateCurrentCard(String newName, String... newParents);
	public boolean addItem(String name, String profile, String... parents);
	public DashboardItem getItem(String cIdString);
	public boolean removeItem(String cIdString);
	public boolean removeItem(DashboardItem i);
	public void setCurrentItem(DashboardItem i);
	public DashboardItem getCurrentItem();
	public void updateCurrentItem(String newName, String... newParents);
}
