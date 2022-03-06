package com.test;
/**
 * This class defines a general exception for any kind of problem found in a
 * tree.
 *
 * <P>This class is designed as a didactic tool for the Data Structures course
 * tought at the Escuela Superior de Informática de Ciudad Real.
 * Universidad de Castilla-La Mancha (Spain)
 *
 * @author Alfonso Niño, Camelia Muñoz and Crescencio Bravo
 * @version November, 2014
 */

public class ProblemInTreeException extends RuntimeException {
  public ProblemInTreeException(String err) {
    super(err);
  }
}
