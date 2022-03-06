package com.test;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <P>This generic class represents a node of a general tree. The node stores an
 * element of (generic) type T.
 *
 * <P>This class is designed as a didactic tool for the Data Structures course
 * tought at the Escuela Superior de Informática de Ciudad Real.
 * Universidad de Castilla-La Mancha (Spain)
 * @author Alfonso Niño, Camelia Muñoz and Crescencio Bravo
* @param <T> The type of elements in the node
 * @version November, 2014
 */

public class NodeGenTree<T> {
  /**
   * Reference to the parent node
   */
  private NodeGenTree<T> parent;
  /**
   * Reference to the element in the node
   */
  private T el;
  /**
   * List of children nodes
   */
  private List<NodeGenTree<T>> adj;

  /**
   * Constructor
   * @param e  The element to store in the node
   */
  public NodeGenTree(T e) {
    parent = null;
    adj = null;
    el = e;
  }

  /**
   * Constructor (simplest version)
   */
  public NodeGenTree() {
    parent = null;
    adj = null;
    el = null;
  }

  /**
   * Sets the parent of the present node
   * @param p The parent of the current node
   */
  public void setParent(NodeGenTree<T> p) {
    parent = p;
  }

  /**
   * Adds a child to the list of children of the node
   * @param n The child node
   */
  public void addChild(NodeGenTree<T> n) {
    if (adj == null) adj = new LinkedList<NodeGenTree<T>>();
    adj.add(n);
  }

  /**
   * Returns an iterator to the children of the node
   * @return Iterator to the children nodes
   */
  public Iterator<NodeGenTree<T>> getChildren() {
    if (adj == null)
      return null;
    else
      return adj.iterator();
  }

  /**
   * Returns the parent of the present node
   * @return The parent of the present node
   */
  public NodeGenTree<T> getParent() {
    return parent;
  }

  /**
   * Returns the element contained in the node
   * @return The element contained in the node
   */
  public T getElement() {
    return el;
  }

  /**
   * This method overrides the equal method inherited from the Object class.
   * IMPORTANT: Note that the parameter must be of class OBJECT
   * @param n The node to compare with
   * @return True if the two nodes contains the same element, false otherwise
   */
  public boolean equals(Object n) {
    //Notice the cast to convert n (class Object) to class NodeGenTree
    return el.equals(((NodeGenTree<T>) n).getElement());
  }
}
