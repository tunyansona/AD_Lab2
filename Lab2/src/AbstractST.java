// Code provided by prof. H. Dierks
import java.util.Iterator;

@SuppressWarnings("ALL")
public abstract class AbstractST<Key, Val> implements Iterable<Key> {
    // abstract methods
    abstract public boolean isEmpty();
    abstract public void put(Key key, Val val);
    abstract public Val get(Key key);
    abstract public void delete(Key key);
    abstract public Iterator<Key> iterator();

    public Boolean contains(Key key) {
        return (get(key) != null);
    }

    public void initWith(Key[] karr, Val[] varr) { };
}