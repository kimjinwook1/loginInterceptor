package hello.login;

import hello.login.domain.item.Item;
import hello.login.domain.item.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ASDfController {

	private final ItemService itemService;

	@GetMapping("/login/oauth2/code/google")
	public String vasdfasdf(Model model) {
		log.info("ASDfController");
		List<Item> items = itemService.findAll();
		model.addAttribute("items", items);
		return "items/items";
	}

}
