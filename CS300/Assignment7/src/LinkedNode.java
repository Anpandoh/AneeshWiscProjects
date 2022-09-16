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



/**
 * Creates a node which links the previous and next nodes
 * @param <T> type of data in the node
 */
public class LinkedNode<T> {
  private T data; //data field of this linked node
  private LinkedNode<T> prev; //reference to the previous linked node in a list of nodes
  private LinkedNode<T> next; //reference to the next linked node in a list of nodes

  /**
   * Creates a linked node
   * @param prev node
   * @param data of the node
   * @param next node
   * @throws IllegalArgumentException if data is null
   */
  public LinkedNode(LinkedNode<T> prev, T data, LinkedNode<T> next)
    throws IllegalArgumentException {
    if (data == null) {
      throw new IllegalArgumentException("Data is null");
    }
    this.data = data;
    this.prev = prev;
    this.next = next;
  }

  /**
   * Sets the previous node
   * @param prev node
   */
  public void setPrev(LinkedNode<T> prev) {
    this.prev = prev;
  }

  /**
   * Sets the next node
   * @param next node
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }


  /**
   * Gets the previous node
   * @return prev node
   */
  public LinkedNode<T> getPrev() {
    return prev;
  }

  /**
   * Gets the next node
   * @return next node
   */
  public LinkedNode<T> getNext() {
    return next;
  }


  /**
   * Gets the data of the node
   * @return data of node
   */
  public T getData() {
    return data;
  }



}
