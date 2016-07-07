/*
 * ReversableHashtable.java
 *
 * Created on February 25, 2007, 5:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.util;

import java.util.Hashtable;

/**
 *
 * @author Menachem & Shira
 */
public class ReversableHashtable<K,V> extends Hashtable<K,V> {
  Hashtable<V, K> reverseHash;
  
  public ReversableHashtable(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor);
    reverseHash = new Hashtable<V, K>(initialCapacity, loadFactor);
  }
  
  public ReversableHashtable(int initialCapacity) {this(initialCapacity, 0.75f);}
  public ReversableHashtable() {this(11, 0.75f);}
  
  public synchronized K getKey(Object value) {return reverseHash.get(value);}

  public synchronized V put(K key, V value) {
    reverseHash.put(value, key);
    return super.put(key, value);
  }  
  public synchronized V remove(Object key) {
    V value = super.remove(key);
    reverseHash.remove(value);
    return value;
  }
  public synchronized void clear() {
    super.clear();
    reverseHash.clear();
  }
 
  public synchronized Object clone() {
    ReversableHashtable<K, V> hash = (ReversableHashtable<K, V>) super.clone();
    hash.reverseHash = (Hashtable<V, K>) reverseHash.clone();
    return hash;
  }
}
