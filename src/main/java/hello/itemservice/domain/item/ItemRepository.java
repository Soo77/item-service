package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // ComponentScan의 대상이되
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // static
    // 실무에서는 멀티스레드 환경에서 동시에 접근하게되면 해시맵쓰면안됨.
    // 동시에 여러스레드가 접근하는거면 해시맵쓰면안되고 concurrenthashmap 이라고 쓰면됨
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
