package com.test;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <P>This class implements the binary Tree ADT interface (BinTreeInterface).
 * The nodes of the tree store elements of (generic) type T.
 *
 * <P>This class is designed as a didactic tool for the Data Structures course
 * tought at the Escuela Superior de Informática de Ciudad Real.
 * Universidad de Castilla-La Mancha (Spain).
 *
 * @author Alfonso Niño, Camelia Muñoz and Crescencio Bravo
 * @version November, 2014
 */

public class BinTree<T> implements BinTreeInterface<T> {
  /**
   * Root node
   */
  private NodeBinTree<T> root=null;
  /**
   * Size (number of nodes) of the tree
   */
  private int size=0;


  /**
   * Constructor (an empty tree is created)
   */
  public BinTree(){
    root = null;
    size = 0;
  }

  /**
   * Constructor (the root is created)
   * @param el Element to store in the root
   */
  public BinTree(T el){
    NodeBinTree <T> n=new NodeBinTree(el);
    root = n;
    size = 1;
  }

  /**
   * Constructor (the root is created)
   * @param n The node used as root
   */
  public BinTree(NodeBinTree<T> n){
    root = n;
    size = 1;
  }

  // Adds a root node containing the element used as parameter
  public void addRoot (T e) throws ProblemInTreeException {
  if (root != null) throw new ProblemInTreeException(
                    "The tree already has a root node");
    NodeBinTree<T> n = new NodeBinTree(e);
    root = n;
    size = 1;
  }

  // Adds as root the node used as parameter
  public void addRoot (NodeBinTree<T> n)throws ProblemInTreeException {
  if (root != null) throw new ProblemInTreeException(
                "The tree already has a root node");
    root = n;
    size = 1;
  }

  // Adds a left node containing e2 to the node containing e1
  public void addLeft (T e1, T e2) throws ProblemInTreeException {
    NodeBinTree<T> n1= find (root, e1);  // Node containing e1
    NodeBinTree<T> n2= find (root, e2);  // Node containing e2

    if (n1 != null && n2 == null) {  // n1 exists but not n2
      n2 = new NodeBinTree(e2);
      n2.setParent(n1);
      n1.addLeft(n2);

      size++;
    } else if (n1 == null) {        // n1 does not exist
      throw new ProblemInTreeException(
                "Element "+e1+" is not in this tree."+
                " Is not possible to add unconnected nodes.");
    } else {
      throw new ProblemInTreeException(
                "Element "+e2+" is already in this tree."+
                " Is not possible to add another parent.");
    }
  }

  // Adds n2 as left child of n1
  public void addLeft (NodeBinTree<T> n1, NodeBinTree<T> n2) throws
              ProblemInTreeException {
    addLeft(n1.getElement(), n2.getElement());
  }

  // Adds a right node containing e2 to the node containing e1
  public void addRight (T e1, T e2) throws ProblemInTreeException {
    NodeBinTree<T> n1= find (root, e1);  // Node containing e1
    NodeBinTree<T> n2= find (root, e2);  // Node containing e2

    if (n1 != null && n2 == null) {  // n1 exists but not n2
      n2 = new NodeBinTree(e2);
      n2.setParent(n1);
      n1.addRight(n2);

      size++;
    } else if (n1 == null) {        // n1 does not exist
      throw new ProblemInTreeException(
                "Element "+e1+" is not in this tree."+
                " Is not possible to add unconnected nodes.");
    } else {
      throw new ProblemInTreeException(
                "Element "+e2+" is already in this tree."+
                " Is not possible to add another parent.");
    }
  }

  // Adds n2 as right child of n1
  public void addRight (NodeBinTree<T> n1, NodeBinTree<T> n2) throws
              ProblemInTreeException {
    addRight(n1.getElement(), n2.getElement());
  }



// Accessor functions ********************************************************


  // Returns the node containing the element elem
  public NodeBinTree<T> getNode(T elem){
    return find(root, elem);
  }

  // Returns an iterator to the tree nodes
  public Iterator<NodeBinTree<T>> getNodes(){
    List<NodeBinTree<T>> l = new LinkedList();
    l.add(root);
    iteratorTree(root, l);
    return l.iterator();
   }

  // Returns the root node of the tree
  public NodeBinTree<T> getRoot(){
    return root;
  }

  //Returns the left node of the node containing element e
  public NodeBinTree<T> getLeft(T e) {
    NodeBinTree<T> n = find(root, e);
    return n.getLeft();
  }


  // Returns the left node of node n
  public NodeBinTree<T> getLeft(NodeBinTree<T> n) {
    n = find(root, n.getElement());
    return n.getLeft();
  }

  // Returns the right node of the node containing element e
  public NodeBinTree<T> getRight(T e) {
    NodeBinTree<T> n = find(root, e);
    return n.getRight();
  }

  // Returns the right node of node n
  public NodeBinTree<T> getRight(NodeBinTree<T> n) {
    n = find(root, n.getElement());
    return n.getRight();
  }

  // Returns the parent of the node containing the element e
  public NodeBinTree<T> getParent(T e) throws ProblemInTreeException{
    NodeBinTree<T> n = find(root, e);
    if (n != null){
      return n.getParent();
    }
    else {
      throw new ProblemInTreeException("Element " + e + " is not in this tree");
    }
  }

  // Returns the parent of node v
  public NodeBinTree<T> getParent(NodeBinTree<T> n) throws
                        ProblemInTreeException{
    return getParent(n.getElement());
  }


// Query functions ***********************************************************


  // Checking if the node containing element e is internal
  public boolean isInternal(T e) throws ProblemInTreeException {
    NodeBinTree<T> v=find (root, e);
    if (v != null){
      return (v.getLeft() != null || v.getRight() != null);
    }
    else {
      throw new ProblemInTreeException("Element "+e+" is not in this tree");
    }
  }

  // Checking if the node used as parameter is internal
  public boolean isInternal(NodeBinTree<T> n) throws ProblemInTreeException {
    return isInternal(n.getElement());
  }

  // Checking if the node containing element e is external (leaf)
  public boolean isExternal(T e) throws ProblemInTreeException {
    NodeBinTree<T> v = find (root, e);
    if (v != null){
      return (v.getLeft() == null && v.getRight() == null);
    }
    else {
      throw new ProblemInTreeException("Element "+e+" is not in this tree");
    }
  }

  // Checking if the node used as parameter is external (leaf)
  public boolean isExternal(NodeBinTree<T> n) throws ProblemInTreeException {
    return isExternal(n.getElement());
  }

  // Checking if the node containing element e is the root
  public boolean isRoot(T e) throws ProblemInTreeException {
    NodeBinTree<T> n = find (root, e);
    if (n != null){
      return n.getParent() == null;
    }
    else {
      throw new ProblemInTreeException("Element "+e+" is not in this tree");
    }
  }

  // Checking if the node used as parameter is the root
  public boolean isRoot(NodeBinTree<T> n) throws ProblemInTreeException {
    return isRoot(n.getElement());
  }

  // Returns the size of the tree
  public int size(){
    return size;
  }

  // Checking if the tree is empty
  public boolean isEmpty(){
    return size == 0;
  }

  /**
   * This method returns a string with a Newick-like represention of the tree
   * @return The tree representation
   */
  public String toString(){
    String c = null;
    if (size == 0)
      c= "The tree is empty";
    else
      c= "[" + printTree(root) + "]";
    return c;
  }

  /**
   * This method uses a DFS traversal of the tree to look for the node
   * containing an element.
   * @param n  The node where the DFS procedure starts
   * @param e  The element used as key to find the node
   * @return   The node containing the element or null if no node contains the
   * element
   */
  private NodeBinTree <T>  find (NodeBinTree <T> n, T e) {
    NodeBinTree <T> m = null;
    NodeBinTree <T> left  = n.getLeft();
    NodeBinTree <T> right = n.getRight();

    if (n.getElement().equals(e)){
      m = n;
    } else if (left != null){
        m = find (left, e);
    }
    if (m == null && right != null) {
      m = find (right, e);
    }
    return m;
  }

  /**
   * This method uses a DFS traversal to generate a list of nodes.
   * @param n  The node where the DFS procedure starts
   * @param l  The list with the nodes
   */
  private void iteratorTree (NodeBinTree <T> n, List<NodeBinTree <T>> l) {
    NodeBinTree <T> left  = n.getLeft();
    NodeBinTree <T> right = n.getRight();

    if (left != null){
      l.add(left);
      iteratorTree(left, l);
    }
    if (right != null){
      l.add(right);
      iteratorTree(right, l);
    }
  }

  /**
   * This method performs a DFS traversal of the tree to generate a Newick-like
   * represention of the tree
   * @param n The node where the DFS procedure starts
   * @return  The tree representation
   */
  private String printTree (NodeBinTree n) {
    String s=n.getElement().toString();
    boolean l=n.getLeft() !=null;
    boolean r=n.getRight()!=null;
    if (l || r ){
      s += "(";

      if (l)
        s += "" + printTree(n.getLeft());
      else
        s += "null";
      if (r)
        s += ", " + printTree(n.getRight());
      else
        s += ", null";

      s += ")";
    }
    return s;
  }
}

