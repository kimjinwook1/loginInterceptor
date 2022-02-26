package hello.login.web.item;

import hello.login.domain.item.Item;
import hello.login.domain.item.ItemService;
import hello.login.web.item.form.ItemSaveForm;
import hello.login.web.item.form.ItemUpdateForm;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemControllerV2 {

	private final ItemService itemService;

	@GetMapping
	public String itemsV2(Model model) {
		List<Item> items = itemService.findAll();
		model.addAttribute("items", items);
		return "items/items";
	}

	@GetMapping("/{itemId}")
	public String itemV2(@PathVariable long itemId, Model model) {
		Item item = itemService.findById(itemId);
		model.addAttribute("item", item);
		return "items/item";
	}

	@GetMapping("/add")
	public String addFormV2(Model model) {
		model.addAttribute("item", new Item());
		return "items/addForm";
	}

	@PostMapping("/add")
	public String addItemV2(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		//특정 필드 예외가 아닌 전체 예외
		if (form.getPrice() != null && form.getQuantity() != null) {
			int resultPrice = form.getPrice() * form.getQuantity();
			if (resultPrice < 10000) {
				bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
			}
		}

		if (bindingResult.hasErrors()) {
			log.info("errors={}", bindingResult);
			return "items/addForm";
		}

		//성공 로직
		Item item = new Item(form.getItemName(), form.getPrice(), form.getQuantity());
		Item savedItem = itemService.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/items/{itemId}";
	}

	@GetMapping("/{itemId}/edit")
	public String editFormV2(@PathVariable Long itemId, Model model) {
		Item item = itemService.findById(itemId);
		model.addAttribute("item", item);
		return "items/editForm";
	}

	@PostMapping("/{itemId}/edit")
	public String editV2(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {

		//특정 필드 예외가 아닌 전체 예외
		if (form.getPrice() != null && form.getQuantity() != null) {
			int resultPrice = form.getPrice() * form.getQuantity();
			if (resultPrice < 10000) {
				bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
			}
		}

		if (bindingResult.hasErrors()) {
			log.info("errors={}", bindingResult);
			return "items/editForm";
		}

		itemService.updateItem(form.getId(), form.getItemName(), form.getPrice(), form.getQuantity());

		return "redirect:/items/{itemId}";
	}
}
