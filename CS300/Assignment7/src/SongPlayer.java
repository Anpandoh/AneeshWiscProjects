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
 * Creates the songplayer which is iterable backwards and forwards
 */
public class SongPlayer implements Iterable<Song> {
  private int size; //size of the list
  private LinkedNode<Song> head; //head of this doubly linked list
  private LinkedNode<Song> tail; //tail of this doubly linked list
  private boolean playingBackward; //true if this song player is reading the list backward


  /**
   * Checks if list is empty
   *
   * @return true if empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Checks size of list
   *
   * @return size
   */
  public int size() {
    return size;
  }

  /**
   * Gets the first song in the list
   *
   * @return song that is first in list
   * @throws NoSuchElementException if list is empty
   */
  public Song getFirst() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    return head.getData();
  }

  /**
   * Gets the last song in the list
   *
   * @return song that is last in the list
   * @throws NoSuchElementException if list empty
   */
  public Song getLast() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    return tail.getData();
  }

  /**
   * Gets the song at a specific index
   *
   * @param index of song that is being returned
   * @return a song at the index provided
   * @throws IndexOutOfBoundsException if the index does not exist in song collection
   */
  public Song get(int index) throws IndexOutOfBoundsException {
    if (index > size() - 1 || index < 0) {
      throw new IndexOutOfBoundsException("index does not exist in song collection");
    }
    LinkedNode<Song> node = head;
    for (int i = 0; i < index; i++) {
      node = node.getNext();
    }
    return node.getData();
  }

  /**
   * Switches the direction in which the songs iterate through the list
   */
  public void switchPlayingDirection() {
    playingBackward = !playingBackward;
  }


  /**
   * Adds the  song to the beginning of the list
   *
   * @param oneSong the song being added
   * @throws NullPointerException if song is null
   */
  public void addFirst(Song oneSong) throws NullPointerException {
    if (oneSong == null) {
      throw new NullPointerException("Song is null");
    }
    if (head != null) {
      LinkedNode<Song> newHead = new LinkedNode<>(null, oneSong, head);

      head.setPrev(newHead);
      head = newHead;
      size++;
    } else {
      head = tail = new LinkedNode<>(null, oneSong, head);
      size++;
    }
  }


  /**
   * Add the song to the end of the list
   *
   * @param oneSong the song being added
   * @throws NullPointerException if song is null
   */
  public void addLast(Song oneSong) throws NullPointerException {
    if (oneSong == null) {
      throw new NullPointerException("Song is null");
    }
    if (tail != null) {
      LinkedNode<Song> newTail = new LinkedNode<>(tail, oneSong, null);
      tail.setNext(newTail);
      tail = newTail;
      size += 1;
    } else {
      tail = head = new LinkedNode<>(tail, oneSong, null);
      size++;
    }
  }

  /**
   * Add song to specific index in list
   *
   * @param index   where song where be added
   * @param oneSong that will be added
   * @throws NullPointerException      if song is null
   * @throws IndexOutOfBoundsException if index is out of song collection
   */
  public void add(int index, Song oneSong) throws NullPointerException, IndexOutOfBoundsException {
    if (oneSong == null) {
      throw new NullPointerException("Song is null");
    }
    if (index > size - 1 || index < 0) {
      throw new IndexOutOfBoundsException("index does not exist in song collection");
    }

    LinkedNode<Song> curr = head;
    if (index == 0 ) {
      addFirst(oneSong);
      return;
    }

    for (int i = 0; i < index - 1; i++) {
      curr = curr.getNext();
    }

    LinkedNode<Song> newNode = new LinkedNode<>(curr, oneSong, curr.getNext());
    curr.setNext(newNode);
    newNode.getNext().setPrev(newNode);
    size++;

  }

  /**
   * Removes the first song in the list
   *
   * @return the song being removed
   * @throws NoSuchElementException if the list is empty
   */
  public Song removeFirst() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    if (head.getNext() == null) {
      Song oldHead = head.getData();
      head = tail = null;
      size = 0;
      return oldHead;
    }
    Song oldHead = head.getData();
    head = head.getNext();

    head.setPrev(null);
    size--;
    return oldHead;
  }

  /**
   * Removes the list song in the list
   *
   * @return the song being removed
   * @throws NoSuchElementException if the list is empty
   */
  public Song removeLast() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    if (tail.getPrev() == null) {
      Song oldTail = tail.getData();
      head = tail = null;
      size = 0;
      return oldTail;
    }
    Song oldTail = tail.getData();
    tail = tail.getPrev();
    tail.setNext(null);
    size--;
    return oldTail;
  }

  /**
   * Removes song at specific index
   *
   * @param index of song being removed
   * @return song being removed
   * @throws IndexOutOfBoundsException if index is out of song list
   */
  public Song remove(int index) throws IndexOutOfBoundsException {


    if (index > size() - 1 || index < 0) {
      throw new IndexOutOfBoundsException("index does not exist in song collection");
    }
    LinkedNode<Song> curr = head;

    if (index == 0) {
      return removeFirst();
    } else {
      for (int i = 0; i < index; i++) {
        curr = curr.getNext();
      }
      Song oldCurr = curr.getData();
      curr.getPrev().setNext(curr.getNext());
      curr.getNext().setPrev(curr.getPrev());
      size--;
      return oldCurr;
    }
  }

  /**
   * Clears the list of songs
   */
  public void clear() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   * Checks if the list of songs contains a certain song
   *
   * @param testSong song being searched for
   * @return true if song exists in list, false otherwise
   */
  public boolean contains(Song testSong) {
    LinkedNode<Song> curr = head;
    while (curr != null) {
      if (curr.getData().equals(testSong)) {
        return true;
      }
      curr = curr.getNext();
    }
    return false;
  }

  /**
   * Plays all the songs in order set
   *
   * @return string of the songs
   */
  public String play() {
    String a = "";
    for (Song song : this) {
      a += song + "\n";
    }
    return a;
  }


  /**
   * Makes the list iterable
   *
   * @returns iterator depending on whether if the songlist is set to forwards or backward
   */
  @Override
  public Iterator<Song> iterator() {
    if (playingBackward) {
      return new BackwardSongIterator(tail);
    } else {
      return new ForwardSongIterator(head);
    }
  }
}
