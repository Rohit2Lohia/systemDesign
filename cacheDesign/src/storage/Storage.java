package storage;

public interface Storage<Key, Value>  {
    public void add(Key key, Value value);
    void remove(Key key);
    public Value get(Key key);
}
