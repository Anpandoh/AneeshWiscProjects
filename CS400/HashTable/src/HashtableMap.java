// --== CS400 Project One File Header ==--
// Name: Aneesh Pandoh
// CSL Username: pandoh
// Email: pandoh@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: N/A


import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * Hashtable map
 * @param <KeyType> type for key
 * @param <ValueType> type for value
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {


  private int size = 0;
  protected LinkedList<KeyValPair<KeyType, ValueType>>[] hashArray;

  /**
   * Initializes Hashtable Map given a capacity
   * @param capacity of how many keys the hashTable can store originally without growth
   */
  public HashtableMap(int capacity) {
    hashArray = (LinkedList<KeyValPair<KeyType, ValueType>>[]) new LinkedList[capacity];
  }

  /**
   * Initializes Hashtable Map with default capacity = 15
   */
  public HashtableMap() {
    hashArray = (LinkedList<KeyValPair<KeyType, ValueType>>[]) new LinkedList[15];
  }

  /**
   * Inserts a new (key, value) pair into the map if the map does not
   * contain a value mapped to key yet.
   *
   * @param key   the key of the (key, value) pair to store
   * @param value the value that the key will map to
   * @return true if the (key, value) pair was inserted into the map,
   * false if a mapping for key already exists and the
   * new (key, value) pair could not be inserted
   */
  @Override public boolean put(KeyType key, ValueType value) {
    if (key == null || containsKey(key)) {
      return false;
    }
    int hashIndex = Math.abs(key.hashCode()) % hashArray.length;
    KeyValPair<KeyType, ValueType> entry = new KeyValPair<>(key, value);

    double loadFactor = (double) (size + 1)/hashArray.length;
    if (loadFactor >= 0.70) {
        rehashArray();
    }

    //Creates a linked list at the hash index to add entries or makes previous link list current
    LinkedList<KeyValPair<KeyType, ValueType>> hashIndexList;
    if (hashArray[hashIndex] == null) {
      hashIndexList = new LinkedList<>();
    } else {
      hashIndexList = hashArray[hashIndex];
    }
    //Adds entries and increments size
    hashIndexList.add(entry);
    hashArray[hashIndex] = hashIndexList;
    size++;


    return true;
  }

  /**
   * Rehash's the array by doubling the array capacity and making sure all key value pairs in linked
   * lists get properly converted
   */
  private void rehashArray() {
    LinkedList<KeyValPair<KeyType, ValueType>>[] tempArray;
    tempArray = (LinkedList<KeyValPair<KeyType, ValueType>>[]) new LinkedList[hashArray.length*2];


    for (LinkedList<KeyValPair<KeyType, ValueType>> listOfKeyValuePairs : hashArray) {
      if (listOfKeyValuePairs != null) {
        for (KeyValPair<KeyType, ValueType> pair : listOfKeyValuePairs) {
          int newHashIndex = Math.abs(pair.getKey().hashCode()) % tempArray.length;
          LinkedList<KeyValPair<KeyType, ValueType>> hashIndexList;
          if (tempArray[newHashIndex] == null) {
            hashIndexList = new LinkedList<>();
          } else {
            hashIndexList = tempArray[newHashIndex];
          }
          //Adds entries and increments size
          hashIndexList.add(pair);
          tempArray[newHashIndex] = hashIndexList;
        }
      }
    }
      hashArray = tempArray;
  }





  /**
   * Returns the value mapped to a key if the map contains such a mapping.
   *
   * @param key the key for which to look up the value
   * @return the value mapped to the key
   * @throws NoSuchElementException if the map does not contain a mapping
   *                                for the key
   */
  @Override public ValueType get(KeyType key) throws NoSuchElementException {
    if (key == null) {
      throw new NoSuchElementException("Key is null");
    }


    int hashIndex = Math.abs(key.hashCode()) % hashArray.length;
    if (hashArray[hashIndex] != null) {
      for (KeyValPair<KeyType, ValueType> pair : hashArray[hashIndex]) {
        if (pair.getKey().equals(key)) {
          return pair.getValue();
        }
      }
    }
    throw new NoSuchElementException("HashTableMap does not contain the given key");
  }

  /**
   * Removes a key and its value from the map.
   *
   * @param key the key for the (key, value) pair to remove
   * @return the value for the (key, value) pair that was removed,
   *         or null if the map did not contain a mapping for key
   */
  @Override public ValueType remove(KeyType key) {
    if (key == null) {
      return null;
    }
    int hashIndex = Math.abs(key.hashCode()) % hashArray.length;

    if (hashArray[hashIndex] != null) {
      for (KeyValPair<KeyType, ValueType> pair : hashArray[hashIndex]) {
        if (pair.getKey().equals(key)) {
          hashArray[hashIndex].remove(pair);
          size--;
          return pair.getValue();
        }
      }
    }
    return null;
  }

  /**
   * Checks if a key is stored in the map.
   *
   * @param key the key to check for
   * @return true if the key is stored (mapped to a value) by the map
   * and false otherwise
   */
  @Override public boolean containsKey(KeyType key) {
    if (key == null) {
      return false;
    }
    int hashIndex = Math.abs(key.hashCode()) % hashArray.length;
    if (hashArray[hashIndex] != null) {
      for (KeyValPair<KeyType, ValueType> pair : hashArray[hashIndex]) {
        if (pair.getKey().equals(key)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns the number of (key, value) pairs stored in the map.
   *
   * @return the number of (key, value) pairs stored in the map
   */
  @Override public int size() {
    return size;
  }

  /**
   * Removes all (key, value) pairs from the map.
   */
  @Override public void clear() {

      hashArray = (LinkedList<KeyValPair<KeyType, ValueType>>[]) new LinkedList[hashArray.length];
      size = 0;
  }




}
