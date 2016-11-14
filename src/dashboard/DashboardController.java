package dashboard;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("manager")
public class DashboardController {
	@Autowired
	IndexCardManager dao;

	@ModelAttribute("manager")
	public IndexCardManager getManager() {
		return dao;
	}

	@RequestMapping("/showIndexCards.do")
	public String mainView() {
		return "IndexCards/index.jsp";
	}

	// view - edit - delete card cycle
	@RequestMapping("/viewIndexCard.do")
	public String viewIndexCard(@RequestParam(value = "cid", required = false) String cid) {
		dao.setCurrentCard(dao.get(cid));
		return "IndexCards/simpleCardView.jsp";
	}

	@RequestMapping("/editIndexCardView.do")
	public String editIndexCardView(@RequestParam(value = "cid", required = false) String cid) {
		if (cid != null){
			dao.setCurrentCard(dao.get(cid));
		}
		return "IndexCards/editCard.jsp";
	}

	@RequestMapping("/editIndexCard.do")
	public String editIndexCard(@RequestParam("name") String name,
			@RequestParam(value = "parents[]", required = false) String... parents) {
		dao.updateCurrentCard(name, parents);
		return viewIndexCard(null);
	}

	@RequestMapping("/deleteIndexCard.do")
	public String deleteIndexCard(@RequestParam(value = "cid") String cid) {
		dao.remove(cid);
		return mainView();
	} // end view - edit - delete card cycle

	// new card cycle
	@RequestMapping("/newIndexCardTemplate.do")
	public String newIndexCardTemplate() {
		return "IndexCards/newIndexCard.jsp";
	}

	@RequestMapping("/newIndexCard.do")
	public String newIndexCard(@RequestParam("name") String name,
			@RequestParam(value = "parents[]", required = false) String... parents) {
		dao.add(name, parents);
		return mainView();
	} // end new card cycle

	// dashboard items
	@RequestMapping("/viewDashboardItem.do")
	public String viewDashboardItem(@RequestParam(value = "cid", required = false) String cid) {
		dao.setCurrentItem(dao.getItem(cid));
		return "DashboardItems/simpleItemView.jsp";
	}

	@RequestMapping("/deleteItem.do")
	public String deleteItem() {
		dao.removeItem(dao.getCurrentItem());
		return mainView();
	}

	@RequestMapping("/editItemView.do")
	public String editItemView(@RequestParam(value = "cid", required=false) String cardCustomID) {
		dao.setCurrentCard(dao.get(cardCustomID));
		return "DashboardItems/editItem.jsp";
	}

	@RequestMapping("/editItem.do")
	public String editItem(@RequestParam("name") String name,
			@RequestParam(value = "parents[]", required = false) String... parents) {
		dao.updateCurrentItem(name, parents);
		return viewDashboardItem(null);
	}

	@RequestMapping("/newItemView.do")
	public String newItemView(@RequestParam(value = "cid", required=false) String cardCustomID) {
		dao.setCurrentCard(dao.get(cardCustomID));
		return dao.getCurrentCard() == null ? mainView() : "DashboardItems/newItem.jsp";
	}

	@RequestMapping("/newItem.do")
	public String newItem(@RequestParam("name") String name,
			@RequestParam(value = "parents[]", required = false) String... parents) {
		dao.addItem(name, "Daily", parents);
		return viewIndexCard(null);
	}

	// end dashboard items
}
