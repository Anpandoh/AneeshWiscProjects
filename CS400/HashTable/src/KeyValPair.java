// --== CS400 Project One File Header ==--
// Name: Aneesh Pandoh
// CSL Username: pandoh
// Email: pandoh@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: N/A


/**
 * Creates a tuple pair of key and value
 * @param <KeyType> type for key
 * @param <ValueType> type for value
 */
public class KeyValPair<KeyType, ValueType> {
  KeyType key = null;
  ValueType value = null;

  /**
   * Construct a key value pair and assign variable values
   * @param key in the pair
   * @param value in the pair
   */
  public KeyValPair(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Gets key
   * @return key
   */
  public KeyType getKey() {
    return key;
  }

  /**
   * Gets value
   * @return value
   */
  public ValueType getValue() {
    return value;
  }

  /**
   * Sets keys
   * @param key to be set
   */
  public void setKey(KeyType key) {
    this.key = key;
  }

  /**
   * Sets value
   * @param value to be set
   */
  public void setValue(ValueType value) {
    this.value = value;
  }



}
