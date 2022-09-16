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
 * Iterates through nodes in forwards direction
 */
public class ForwardSongIterator implements Iterator<Song> {
  private LinkedNode<Song> next; //reference to the next linked node in a list of nodes.

  /**
   * Sets the first node as the first song that will be iterated through
   * @param first is the song that will be first
   */
  public ForwardSongIterator(LinkedNode<Song> first) {
    next = first;
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
    next = next.getNext();
    return current;
  }
}
