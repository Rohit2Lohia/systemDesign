package factories;

import lombok.NonNull;
import model.Cache;
import policies.LRUEvictionPolicy;
import storage.HashMapBasedStorage;

public class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCache(@NonNull final int capacity) {
        return new Cache<Key, Value>(new LRUEvictionPolicy<Key>(), new HashMapBasedStorage<Key, Value>(capacity));
    }
}
