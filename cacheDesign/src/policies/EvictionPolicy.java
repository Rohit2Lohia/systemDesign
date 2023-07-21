package policies;

public interface EvictionPolicy<Key> {
    public void keyAccessed(Key key);
    public Key evictKey();
}
