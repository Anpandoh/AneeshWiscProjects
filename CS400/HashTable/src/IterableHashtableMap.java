// IterableHashtableMap created by anpandoh at 11:18 PM, 10/2/22


import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class IterableHashtableMap<KeyType, ValueType> extends HashtableMap<KeyType, ValueType>
  implements IterableMapADT<KeyType, ValueType> {

  public IterableHashtableMap(int capacity) {
    super(capacity);
  }



  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override public Iterator<ValueType> iterator() {
    Iterator<ValueType> customIterator = new Iterator<>() {
      private int currentIndex = 0; //Current index in array

      private int currentNode = 0; //Current node in linked list

      private int remainingElements = size();

      /**
       * Returns {@code true} if the iteration has more elements.
       * (In other words, returns {@code true} if {@link #next} would
       * return an element rather than throwing an exception.)
       *
       * @return {@code true} if the iteration has more elements
       */
      @Override public boolean hasNext() {
        return remainingElements != 0;
      }

      /**
       * Returns the next element in the iteration.
       *
       * @return the next element in the iteration
       * @throws NoSuchElementException if the iteration has no more elements
       */
      @Override public ValueType next() {


        if (!hasNext()) {
          throw new NoSuchElementException("No more elements in this iteration");
        }
        while (hasNext()) {
          if (hashArray[currentIndex] != null) {

            LinkedList<KeyValPair<KeyType, ValueType>> currentLinkedList = hashArray[currentIndex];
            if (currentNode < currentLinkedList.size()) {
              int tempNode = currentNode;
              currentNode++;
              remainingElements--;
              return currentLinkedList.get(tempNode).getValue();
            }
          }

            currentIndex++;
            currentNode = 0;




        }


        //Iterate over the array, if there linkedlist is initialized linked list, skip,
        // set currentNode to value in linked list (check if LinkedList.hasNext()),
        // if not continue to iterate continue through array



        return null;
      }
    };



    return customIterator;



  }

}
