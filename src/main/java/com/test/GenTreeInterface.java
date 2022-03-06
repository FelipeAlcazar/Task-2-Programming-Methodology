package com.test;
import java.util.Iterator;

/**
 * <P><P>This class represents an interface for the General Tree ADT.
 * The nodes of the tree store elements of (generic) type T.
 *
 * <P>This interface is designed as a didactic tool for the Data Structures
 * course tought at the Escuela Superior de Informática de Ciudad Real.
 * Universidad de Castilla-La Mancha (Spain).
 * <P>
 * @author Alfonso Niño, Camelia Muñoz and Crescencio Bravo
 * @param <T> The type of elements in the tree
 * @version November, 2014
 */

public interface GenTreeInterface<T> {

  /**
   * This method adds a root node containing the element used as parameter
   * @param e The element to store in the root node
   * @throws ProblemInTreeException if the tree already has a root node
   */
  public void addRoot(T e) throws ProblemInTreeException;

  /**
   * This method adds a root node
   * @param n The node to be used as root
   * @throws ProblemInTreeException if the tree already has a root node
   */
  public void addRoot(NodeGenTree<T> n) throws ProblemInTreeException;

  /**
   * Adds a node containing element e2 as child of the node containing element e1. The
   * tree must contain a node with e1 before using this method. e2 must not
   * exist in any node of the tree.
   * @param e1  The element used a key to find the parent node
   * @param e2  The element used a key for the child node
   * @throws ProblemInTreeException if e2 is already in the tree or a node with
	 *         e1 does not exist
   */
  public void addChild(T e1, T e2) throws ProblemInTreeException;

  /**
   * Adds node n2 as child of node n1. The tree must contain n1 before using
   * this method. n2 must not exist in the tree.
   * @param n1  The parent node
   * @param n2  The child node
   * @throws ProblemInTreeException if node n2 is already in the tree or node n1
	 *         does not exist
   */
  public void addChild(NodeGenTree<T> n1, NodeGenTree<T> n2) throws
                        ProblemInTreeException;


  // Accessor functions ********************************************************

  /**
   * Returns the node containing the element used as parameter
   * @param elem The element used as key to find the node
   * @return The node containing the element or null if no node contains
   * the element
   */
  public NodeGenTree<T> getNode(T elem);

  /**
   * Returns an iterator to the nodes of the tree
   * @return An iterator to the nodes of the tree
   */
  public Iterator<NodeGenTree<T>> getNodes();

  /**
   * This method returns the root node of the tree
   * @return The root node of the tree
   */
  public NodeGenTree<T> getRoot();

  /**
   * Returns the parent of the node containing the element used as parameter
   * @param e The element used as key to look for the node
   * @return The parent of the node containing element e
   * @throws ProblemInTreeException if no node contains e
   */
  public NodeGenTree<T> getParent(T e) throws
                        ProblemInTreeException;

  /**
   * Returns the parent of the node used as parameter
   * @param n The node whose parent we are looking for
   * @return The parent of node n
   * @throws ProblemInTreeException if node n is not in the tree
   */
  public NodeGenTree<T> getParent(NodeGenTree<T> n) throws
                        ProblemInTreeException;

    /**
   * Returns an iterator to the children of the node containing the element
   * used as parameter
   * @param e  The element used as key to look for the node
   * @return  An iterator to the children of node containing element e
   * @throws ProblemInTreeException if no node contains e
   */
  public Iterator<NodeGenTree<T>> getChildren(T e) throws
                                  ProblemInTreeException;

  /**
   * Returns an iterator to the children of a given node
   * @param n  The node whose children we wish
   * @return  An iterator to the children of node n
   * @throws ProblemInTreeException if node n is not in the tree
   */
  public Iterator<NodeGenTree<T>> getChildren(NodeGenTree<T> n) throws
                                  ProblemInTreeException;


  // Query functions ***********************************************************

  /**
   * Checking if the node containing element e is internal
   * @param e  The element used as key to look for the node
   * @return  True if the node containing e is internal, false otherwise
   * @throws ProblemInTreeException if no node contains e
   */
  public boolean isInternal(T e) throws ProblemInTreeException;

  /**
   * Checking if the node used as parameter is internal
   * @param n  The node to check
   * @return  True if node n is internal, false otherwise
   * @throws ProblemInTreeException if node n is not in the tree
   */
  public boolean isInternal(NodeGenTree<T> n) throws ProblemInTreeException;

  /**
   * Checking if the node containing element e is external (leaf)
   * @param e  The element used as key to look for the node
   * @return  True if the node containing e is external, false otherwise
   * @throws ProblemInTreeException if no node contains e
   */
  public boolean isExternal(T e) throws ProblemInTreeException;

  /**
   * Checking if the node used as parameter is external (leaf)
   * @param n  The node to check
   * @return  True if node n is external, false otherwise
   * @throws ProblemInTreeException if node n is not in the tree
   */
  public boolean isExternal(NodeGenTree<T> n) throws ProblemInTreeException;

  /**
   * Checking if the node containing element e is the root
   * @param e  The element used as key to look for the root
   * @return  True if the node containing e is the root, false otherwise
   * @throws ProblemInTreeException if no node contains e
   */
  public boolean isRoot(T e) throws ProblemInTreeException;

  /**
   * Checking if the node used as parameter is the root
   * @param n  The node to check
   * @return  True if node n is the root, false otherwise
   * @throws ProblemInTreeException if node n is not in the tree
   */
  public boolean isRoot(NodeGenTree<T> n) throws ProblemInTreeException;

  /**
   * The method returns the size (number of nodes) of the tree
   * @return The size of the tree
   */
  public int size();

  /**
   * Determines if the tree is empty
   * @return True if the tree has no nodes, false otherwise
   */
  public boolean isEmpty();

}
