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


import java.util.NoSuchElementException;

/**
 * Creates a queue with nodes
 * @param <T> data type of nodes
 */
public class LinkedQueue<T> implements QueueADT<T> {

  protected Node<T> back; //Node in the back of the queue
  protected Node<T> front; //Node in the front of the queue
  private int n; //number of elements in the queue


  /**
   * Adds node to back of queue
   * @param data of node being added
   */
  @Override public void enqueue(T data) {
    if (isEmpty()) {
      front = back = new Node<>(data);
      n++;
      return;
    }
    Node<T> newNode = new Node<>(data);
    back.setNext(newNode);
    back = newNode;
    n++;
  }

  /**
   * Removes nodes from front of queue
   * @return data of node being removed
   * @throws NoSuchElementException if queue is empty
   */
  @Override public T dequeue() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("The Queue is Empty");
    }

    Node<T> currNode = front;
    front = front.getNext();
    n--;

    if (isEmpty()) {
      back = null;
    }

    return currNode.getData();
  }

  /**
   * Gets data of front node
   * @return data of node in the front
   * @throws NoSuchElementException if queue is empty
   */
  @Override public T peek() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("The Queue is Empty");
    }
    return front.getData();
  }

  /**
   * Checks if queue is empty
   * @return true if empty, false otherwise
   */
  @Override public boolean isEmpty() {
    if (front == null) {
      return true;
    }
    return false;
  }

  /**
   * Gets size of queue
   * @return size of queue
   */
  @Override public int size() {
    return n;
  }

  /**
   * Prints out data of each node in queue
   * @return string version of queue
   */
  @Override public String toString() {
    String stringQueue = "";
    if (isEmpty()) {
      return stringQueue;
    }

    Node<T> currNode = front;

    stringQueue = currNode.getData() + " ";

    while (currNode.getNext() != null) {
      currNode = currNode.getNext();
      stringQueue += currNode.getData() + " ";
    }

    return stringQueue;
  }

}
