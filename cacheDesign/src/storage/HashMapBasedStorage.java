package storage;

import exceptions.NotFoundException;
import exceptions.StorageFullException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<Key, Value> implements Storage<Key, Value>{

    private Map<Key, Value> storage;
    private final @Getter Integer capacity;

    public HashMapBasedStorage(Integer capacity) {
        this.storage = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public void add(Key key, Value value) {
        if(isStorageFull()) throw new StorageFullException("capacity full");
        storage.put(key, value);
    }

    @Override
    public void remove(Key key) {
        if(!storage.containsKey(key)) throw new NotFoundException(key + " doesn't exist in cache");
        storage.remove(key);
    }

    @Override
    public Value get(Key key) {
        if(!storage.containsKey(key)) throw new NotFoundException(key + " doesn't exist in cache");
        return storage.get(key);
    }

    private boolean isStorageFull() {
        return storage.size() >= capacity;
    }
}
