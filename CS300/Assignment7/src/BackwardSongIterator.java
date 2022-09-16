//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    SongPlayerProject
// Course:   CS 300 Spring 2022
//
// Author:   Aneesh Pandoh
// Email:    Pandoh@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////



import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterates through nodes in backwards direction
 */
public class BackwardSongIterator implements Iterator<Song> {
  private LinkedNode<Song> next; //reference to the next linked node in a list of nodes.


  /**
   * Sets the last node as the first song that will be iterated through
   * @param last is the song that will be first
   */
  public BackwardSongIterator(LinkedNode<Song> last) {
    next = last;
  }

  /**
   * Checks if there is a next node
   * @return true if there is, false otherwise
   */
  @Override
  public boolean hasNext() {

    return next != null;
  }

  /**
   * Gets the current node in the list and sets the next node as the new current node
   * @return the current node
   * @throws NoSuchElementException if there are no more songs to return
   */
  @Override
  public Song next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("No more songs to return");
    }
    Song current = next.getData();
    next = next.getPrev();
    return current;
  }



}
