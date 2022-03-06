package com.test;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <P>This class implements the General Tree ADT interface (GenTreeInterface).
 * The nodes of the tree store elements of (generic) type T.
 *
 * <P>This class is designed as a didactic tool for the Data Structures course
 * tought at the Escuela Superior de Informática de Ciudad Real.
 * Universidad de Castilla-La Mancha (Spain)
 * @author Alfonso Niño, Camelia Muñoz and Crescencio Bravo
 * @param <T> The type of elements in the tree
 * @version November, 2014
 */

public class GenTree<T> implements GenTreeInterface<T> {
  /**
   * Root of the tree
   */
  private NodeGenTree<T> root;
  /**
   * Size (number of nodes) of the tree
   */
  private int size;

  /**
   * Constructor (an empty tree is created)
   */
  public GenTree() {
    root = null;
    size = 0;
  }

  /**
   * Constructor (the root is created)
   * @param el Element to store in the root
   */
  public GenTree(T el) {
    NodeGenTree<T> n = new NodeGenTree<T>(el);
    root = n;
    size = 1;
  }

  /**
   * Constructor (the root is created)
   * @param n The node used as root
   */
  public GenTree(NodeGenTree<T> n) {
    root = n;
    size = 1;
  }

  // Adds a root node containing the element used as parameter
  public void addRoot(T e) throws ProblemInTreeException {
    if (root != null) throw new ProblemInTreeException(
                    "The tree already has a root node");
    NodeGenTree<T> n = new NodeGenTree(e);
    root = n;
    size = 1;
  }

  // Adds as root the node used as parameter
  public void addRoot(NodeGenTree<T> n )throws ProblemInTreeException {
    if (root != null) throw new ProblemInTreeException(
                    "The tree already has a root node");
    root = n;
    size = 1;
  }

  // Adds a node containing e2 to the node containing e1
  public void addChild(T e1, T e2) throws ProblemInTreeException {
    if (root == null)
       throw new ProblemInTreeException("The tree has no root.");
    NodeGenTree<T> n1 = find(root, e1);  // Node containing e1
    NodeGenTree<T> n2 = find(root, e2);  // Node containing e2

    if (n1 != null && n2 == null) {  // n1 exists but not n2
      n2 = new NodeGenTree(e2);
      n2.setParent(n1);
      n1.addChild(n2);

      size++;
    } else if (n1 == null) {        // n1 does not exist
      throw new ProblemInTreeException(
                "Element " + e1 + " is not in this tree."
                + " Is not possible to add unconnected nodes.");
    } else {
      throw new ProblemInTreeException(
                "Element " + e2 + " is already in this tree."
                + " Is not possible to add another parent.");
    }
  }

  // Adds n2 as child of n1
  public void addChild(NodeGenTree<T> n1, NodeGenTree<T> n2) throws
              ProblemInTreeException {
    addChild(n1.getElement(), n2.getElement());
  }


// Accessor functions ********************************************************


  // Returns the node containing the element elem
  public NodeGenTree<T> getNode(T elem) {
    return find(root, elem);
  }


  // Returns an iterator to the tree nodes
  public Iterator<NodeGenTree<T>> getNodes() {
    List<NodeGenTree<T>> l = new LinkedList();
    l.add(root);
    iteratorTree(root, l);
    return l.iterator();
   }

  // Returns the root node of the tree
  public NodeGenTree<T> getRoot() {
    return root;
  }

  // Returns the parent of the node containing the element e
  public NodeGenTree<T> getParent(T e) throws ProblemInTreeException {
    NodeGenTree<T> n = find(root, e);
    if (n != null) {
      return n.getParent();
    } else {
      throw new ProblemInTreeException("Element " + e + " is not in this tree");
    }
  }

  // Returns the parent of node v
  public NodeGenTree<T> getParent(NodeGenTree<T> n) throws
                        ProblemInTreeException {
    return getParent(n.getElement());
  }

  // Returns an iterator to the children of the node containing the element e
  public Iterator<NodeGenTree<T>> getChildren(T e) throws
                               ProblemInTreeException {
    NodeGenTree<T> n = find(root, e);
    if (n != null) {
      return n.getChildren();
    } else {
      throw new ProblemInTreeException("Element " + e + " is not in this tree");
    }
  }

  // Returns an iterator to the children of n
  public Iterator<NodeGenTree<T>> getChildren(NodeGenTree<T> n) throws
                               ProblemInTreeException {
    return getChildren(n.getElement());
  }



// Query functions ***********************************************************


  // Checking if the node containing element e is internal
  public boolean isInternal(T e) throws ProblemInTreeException {
    NodeGenTree<T> v = find (root, e);
    if (v != null) {
      return (v.getChildren() != null);
    } else {
      throw new ProblemInTreeException("Element " + e + " is not in this tree");
    }
  }

  // Checking if the node used as parameter is internal
  public boolean isInternal(NodeGenTree<T> n) throws ProblemInTreeException {
    return isInternal(n.getElement());
  }

  // Checking if the node containing element e is external (leaf)
  public boolean isExternal(T e) throws ProblemInTreeException {
    NodeGenTree<T> v = find(root, e);
    if (v != null) {
      return (v.getChildren() == null);
    } else {
      throw new ProblemInTreeException("Element " + e + " is not in this tree");
    }
  }

  // Checking if the node used as parameter is external (leaf)
  public boolean isExternal(NodeGenTree<T> n) throws ProblemInTreeException {
    return isExternal(n.getElement());
  }

  // Checking if the node containing element e is the root
  public boolean isRoot(T e) throws ProblemInTreeException {
    NodeGenTree<T> n = find(root, e);
    if (n != null) {
      return n.getParent() == null;
    } else {
      throw new ProblemInTreeException("Element " + e + " is not in this tree");
    }
  }

  // Checking if the node used as parameter is the root
  public boolean isRoot(NodeGenTree<T> n) throws ProblemInTreeException {
    return isRoot(n.getElement());
  }

  // Returns the size of the tree
  public int size() {
    return size;
  }

  // Checking if the tree is empty
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * This method uses a DFS traversal of the tree to look for the node
   * containing an element.
   * @param n  The node where the DFS procedure starts
   * @param e  The element used as key to find the node
   * @return   The node containing the element or null if no node contains the
   * element
   */
  private NodeGenTree<T>  find(NodeGenTree<T> n, T e) {
    Iterator<NodeGenTree<T>> it = n.getChildren();
    NodeGenTree<T> m = null;

    if (n.getElement().equals(e)) {
      m = n;
    } else if (it != null) {
        boolean goOn = true;
        while (it.hasNext() && goOn) {
          m = it.next();
          if (m.getElement().equals(e)) {
            goOn = false;
          } else {
            m = find(m, e);
            if (m != null && m.getElement().equals(e))
              goOn = false;
          }
        }
    }
    return m;
  }

  /**
   * This method uses a DFS traversal to generate a list of nodes.
   * @param n  The node where the DFS procedure starts
   * @param l  The list with the nodes
   */
  private void iteratorTree(NodeGenTree<T> n, List<NodeGenTree<T>> l) {

    Iterator<NodeGenTree<T>> it = n.getChildren();
    if (it != null) {
      NodeGenTree<T> m;
      while (it.hasNext()) {
        m = it.next();
        l.add(m);
        iteratorTree(m, l);
      }
    }
  }

  /**
   * This method returns a string with a Newick-like represention of the tree
   * @return The tree representation
   */
  public String toString() {
    String c = null;
    if (size == 0)
      c= "The tree is empty";
    else
      c= "[" + printTree(root) + "]";
    return c;
  }

  /**
   * This method performs a DFS traversal of the tree to generate a Newick-like
   * represention of the tree
   * @param n The node where the DFS procedure starts
   * @return  The tree representation
   */

  private String printTree(NodeGenTree<T> n) {
    String s = n.getElement().toString();
    Iterator<NodeGenTree<T>> it = n.getChildren();
    if (it != null) {
      s += "(";
      NodeGenTree<T> m;
      while (it.hasNext()) {
        m = it.next();
        s += printTree(m);
        if (it.hasNext()) s += ",";
      }
      s += ")";
    }
    return s;
  }
}

