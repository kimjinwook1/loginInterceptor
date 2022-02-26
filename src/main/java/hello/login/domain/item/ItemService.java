package hello.login.domain.item;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {

	private final ItemJpaRepository itemJpaRepository;

	//save, findbyId findall update
	public Item save(Item item) {
		return itemJpaRepository.save(item);
	}

	public Item items(Long id) {
		return itemJpaRepository.findById(id).get();
	}

	public List<Item> items() {
		return itemJpaRepository.findAll();
	}

}
