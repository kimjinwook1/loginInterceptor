package hello.login.domain.item;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

	private final ItemJpaRepository itemJpaRepository;

	@Transactional
	public Item save(Item item) {
		return itemJpaRepository.save(item);
	}

	public Item findById(Long id) {
		return itemJpaRepository.findById(id).get();
	}

	public List<Item> findAll() {
		return itemJpaRepository.findAll();
	}

	@Transactional
	public void updateItem(Long itemId, String itemName, int price, int quantity) {
		Item findItem = itemJpaRepository.findById(itemId).get();
		findItem.setItemName(itemName);
		findItem.setPrice(price);
		findItem.setQuantity(quantity);
	}
}
