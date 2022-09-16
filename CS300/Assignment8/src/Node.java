//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    DNA Encoder
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
 * Creates a node for the queue that contains data and the next node
 *
 * @param <T> the data type of the data
 */
public class Node<T> {
  private T data;
  private Node<T> next;

  /**
   * Constructs node with data
   *
   * @param data of node
   */
  public Node(T data) {
    this.data = data;
  }

  /**
   * Constructs node with data and links to next node
   *
   * @param data of node
   * @param next the next node
   */
  public Node(T data, Node<T> next) {
    this(data);
    this.next = next;
  }

  /**
   * Gets data of node
   *
   * @return data
   */
  public T getData() {
    return data;
  }

  /**
   * Gets next node
   *
   * @return node after current node
   */
  public Node<T> getNext() {
    return next;
  }

  /**
   * Sets next node
   *
   * @param next node after current node
   */
  public void setNext(Node<T> next) {
    this.next = next;
  }



}
