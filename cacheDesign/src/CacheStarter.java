import factories.CacheFactory;
import model.Cache;

public class CacheStarter {
    public static void main(String[] args) {
        Cache<Integer, Integer> cache = new CacheFactory<Integer, Integer>().defaultCache(4);
        testGettingItemsFromCache(cache);
    }

    private static void testGettingItemsFromCache(Cache<Integer, Integer> cache) {
        cache.put(1,10);
        cache.put(2, 20);
        System.out.println("(K,V):("+1+","+cache.get(1)+")");
        cache.put(3, 30);
        cache.put(4, 40);
        cache.put(5, 50);
        System.out.println("(K,V):("+2+","+cache.get(2)+")");

    }
}
