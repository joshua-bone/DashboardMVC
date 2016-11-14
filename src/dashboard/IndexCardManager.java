package dashboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndexCardManager implements IndexCardDAO {
	private TreeSet<IndexCard> cardSet = new TreeSet<>();
	private HashSet<DashboardItem> itemSet = new HashSet<>();
	private IndexCard currentCard;
	private DashboardItem currentItem;

	public static void main(String[] args) {
		IndexCardManager t = new IndexCardManager();
	}

	// CONSTRUCTOR
	public IndexCardManager() {
		loadIndexCards();
	}

	private void loadIndexCards() {
		add("Health");
		add("Fitness", "Health");
		add("Nutrition", "Health");

		add("Finances");
		add("Bills", "Finances");
		add("Freelance Work", "Finances");
		add("Web Design", "Freelance Work");
		
		add("Interests");
		add("Bicycling", "Interests", "Fitness");
		add("Photography", "Freelance Work", "Interests");
		add("Travel");
		
		add("Education");
		add("Self-Study", "Education");
		add("Skill Distillery", "Education");

		addItem("Crossfit - Tuesdays and Thursdays", "Daily", "Fitness");
		addItem("Bike to work 1x/week", "Daily",  "Bicycling");
		addItem("Photo trip - Great Sand Dunes", "Daily",  "Travel", "Photography");
		addItem("Take pics at Pat & Emma's wedding - May 22nd", "Daily",  "Photography");
		addItem("Pay utilities by 10th of month", "Daily",  "Bills");
		addItem("Ruby on Rails tutorials - 1 lesson / day", "Daily",  "Web Design", "Self-Study");
		addItem("Weekend project - due Monday at noon", "Daily",  "Skill Distillery");
		addItem("Quit smoking", "Daily",  "Health");

	}

	@Override
	public boolean setParent(IndexCard c, IndexCard p) {
		return setChild(p, c);
	}

	@Override
	public boolean setChild(IndexCard p, IndexCard c) {
		boolean set = false;
		if (!isChild(p, c) && !isParent(c, p)) { // do not link if a link exists
			link(p, c);
			set = true;
		}
		return set;
	}

	public boolean isChild(IndexCard p, IndexCard c) {
		boolean isChild = false;
		if (p == c) {
			isChild = true;
		} else {
			for (IndexCard t : p.getChildren()) {
				isChild ^= isChild(t, c);
			}
		}
		return isChild;
	}

	public boolean isParent(IndexCard c, IndexCard p) {
		return isChild(p, c);
	}

	public boolean add(String n) {
		if (n == null || n.equals("")) {
			return false;
		}
		IndexCard c = new IndexCard(n);
		return cardSet.add(c);
	}

	public boolean add(String n, String... parents) {
		if (n == null || n.equals("")) {
			return false;
		}
		boolean added = add(n);
		if (added) {
			if (parents != null) {
				IndexCard c = get(n);
				for (String pString : parents) {
					IndexCard p = get(pString.trim());
					if (p != null)
						setParent(c, p);
				}
			}
		}
		return added;
	}

	public IndexCard get(String cidOrName) {
		IndexCard returnCard = null;
		if (cidOrName != null && cidOrName != "") {
			for (IndexCard c : cardSet) {
				if (c.getCustomID().equals(cidOrName) || c.getName().equalsIgnoreCase(cidOrName)) {
					returnCard = c;
					break;
				}
			}
		}
		return returnCard;
	}

	// leaves children as orphans
	public boolean remove(String n) {
		boolean r = false;
		IndexCard toRemove = get(n);
		if (toRemove != null) {
			while (!toRemove.getChildren().isEmpty()) {
				unlink(toRemove, toRemove.getChildren().get(0));
			}
			while (!toRemove.getParents().isEmpty()) {
				unlink(toRemove.getParents().get(0), toRemove);
			}
			cardSet.remove(toRemove);
			r = true;
		}
		return r;
	}

	private void link(IndexCard p, IndexCard c) {
		p.getChildren().add(c);
		c.getParents().add(p);
	}

	private void unlink(IndexCard p, IndexCard c) {
		p.getChildren().remove(c);
		c.getParents().remove(p);
	}

	@Override
	public TreeSet<IndexCard> getCardSet() {
		return cardSet;
	}

	@Override
	public IndexCard getCurrentCard() {
		return currentCard;
	}

	@Override
	public void setCurrentCard(IndexCard card) {
		if (card != null) {
			currentCard = card;
		}
	}

	@Override
	public void updateCurrentCard(String newName, String... newParents) {
		if (newName == null || newName.equals("")) {
			return;
		}

		if (add(newName)) { // quick check to see that newName is unique
			remove(newName); // remove it immediately
			currentCard.setName(newName);
			while (currentCard.getParents().size() > 0) {
				unlink(currentCard.getParents().get(0), currentCard);
			}
			if (newParents != null) {
				for (String pString : newParents) {
					IndexCard p = get(pString.trim());
					if (p != null) {
						setParent(currentCard, p);
					}
				}
			}
		}
	}

	////////////////////////////////////////////////////
	////////////// DASHBOARDITEMS////////////////////////
	////////////////////////////////////////////////////

	@Override
	public void setCurrentItem(DashboardItem i) {
		if (i != null) {
			currentItem = i;
		}
	}

	@Override
	public DashboardItem getCurrentItem() {
		return currentItem;
	}

	@Override
	public boolean addItem(String name, String profile, String... parents) {
		boolean added = false;
		if (name == null || name.equals("") || parents == null) {
			return false;
		}

		DashboardItem di = null;
		switch (profile) {
		case "Daily":
			di = new Daily(name);
			break;
		}

		if (di != null) {
			added = itemSet.add(di); // should always be true since we compare
										// by customID
			for (String p : parents) {
				IndexCard pCard = get(p);
				if (pCard != null) {
					linkItem(pCard, di);
				}
			}
		}
		return added;
	}

	@Override
	public DashboardItem getItem(String cIdString) {
		DashboardItem returnItem = null;
		for (DashboardItem di : itemSet) {
			if (di.getCustomID().equals(cIdString)) {
				returnItem = di;
				break;
			}
		}
		return returnItem;
	}

	@Override
	public boolean removeItem(String cIDString) {
		return removeItem(getItem(cIDString));
	}

	@Override
	public boolean removeItem(DashboardItem i) {
		while (i.getParents().size() > 0) {
			IndexCard p = i.getParents().get(0);
			unlinkItem(p, i);
		}
		return itemSet.remove(i);
	}

	public void linkItem(IndexCard pCard, DashboardItem item) {
		pCard.getItems().add(item);
		item.getParents().add(pCard);
	}

	public void unlinkItem(IndexCard pCard, DashboardItem item) {
		pCard.getItems().remove(item);
		item.getParents().remove(pCard);
	}

	@Override
	public void updateCurrentItem(String newName, String... newParents) {
		if (newName == null || newName.equals("")) {
			return;
		}
		currentItem.setName(newName);
		while (currentItem.getParents().size() > 0) {
			unlinkItem(currentItem.getParents().get(0), currentItem);
		}
		if (newParents != null) {
			for (String pString : newParents) {
				IndexCard p = get(pString.trim());
				if (p != null) {
					linkItem(p, currentItem);
				}
			}
		}
	}

}
