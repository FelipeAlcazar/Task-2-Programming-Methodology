package com.test;
/**
 * <P>This generic class represents a node of a binary general tree. The node
 * stores an element of (generic) type T.
 *
 * <P>This class is designed as a didactic tool for the Data Structures course
 * tought at the Escuela Superior de Informática de Ciudad Real.
 * Universidad de Castilla-La Mancha (Spain)
 * @author Alfonso Niño, Camelia Muñoz and Crescencio Bravo
 * @version November, 2014
 */

public class NodeBinTree<T> {
  /**
   * Reference to the parent node
   */
  private NodeBinTree<T> parent;

  /**
   * Reference to the element in the node
   */
  private T el;

  /**
   * Reference to the left child of the node
   */
  private NodeBinTree<T> left;

  /**
   * Reference to the right child of the node
   */
  private NodeBinTree<T> right;

  /**
   * Constructor
   * @param e  The element to store in the node
   */
  public NodeBinTree(T e){
    parent=null;
    left=null;
    right=null;
    el=e;
  }


  /**
   * Sets the parent of the present node
   * @param p The parent of the current node
   */
  public void setParent(NodeBinTree<T> p){
    parent=p;
  }

  /**
   * Adds the left child of the node
   * @param n The left child of the current node
   */
  public void addLeft(NodeBinTree<T> n){
    left=n;
  }

  /**
   * Adds the left child of the node
   * @param n The right child of the current node
   */
  public void addRight(NodeBinTree<T> n){
    right=n;
  }

  /**
   * This method returns the left child of the present node
   * @return The left child of the present node
   */
  public NodeBinTree<T> getLeft(){
    return left;
  }

  /**
   * This method returns the right child of the present node
   * @return The right child of the present node
   */
  public NodeBinTree<T> getRight(){
    return right;
  }

  /**
   * Returns the parent of the present node
   * @return The parent of the present node
   */
  public NodeBinTree<T> getParent(){
    return parent;
  }

  /**
   * Returns the element contained in the node
   * @return The element contained in the node
   */
  public T getElement(){
    return el;
  }

  /**
   * This method overrides the equal method inherited from the Object class.
   * IMPORTANT: Note that the parameter must be of class OBJECT
   * @param n The node to compare with
   * @return True if the two nodes contains the same element, false otherwise
   */
    public boolean equals (Object n){
    //Notice the cast to convert n (class Object) to class NodeBinTree<T>
    return el.equals(((NodeBinTree<T>)n).getElement());
  }
}
