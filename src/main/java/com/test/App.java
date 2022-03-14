package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.Scanner;
/*********************************************************************
*
* Class Name: App
* Author/s name: Felipe Alcázar Gómez and Elena Megía
* Release/Creation date:
* Class description: In this class we have the entire program, what we do is take a randon array with black and white values,
* we have to make a quad tree method to compress the sized for representing the image in a tree.
* 
**********************************************************************
*/


public class App 
{
    static GenTree tree=new GenTree<>();
    final static Scanner sc = new Scanner(System.in);
    /*********************************************************************
    *
    * Method name: Main
    *
    * Description of the Method: In this method we introduce the type of array that we want.
    *
    * List of Checked Exceptions: 
    * Exception: This Exception occurs when the main method doesn't work well.
    *
    *********************************************************************/
    
    public static void main(String[] args) throws Exception
    {
       //Node style, used for the root of the matrixes (can be changed in future versions of the program)
       String nodeStyle="O";
       NodeGenTree root=new NodeGenTree<>(nodeStyle);
       tree.addRoot(root);

       //long time= checkTimeNano(matrix, root, -1, nodeStyle);
       System.out.println("Welcome to the program.");
       System.out.println("Choose the dimension of the matrix (Note: only powers of 2 allowed. Ex: 8): ");
       int number1=Numbers();
       
       //Colors used. Can be easily changed in future versions of the program
       Short color1='w'; // w for white color
       Short color2='b'; // b for black color
       long time= checkTimeNano(generateRandomMatrix(number1,number1,color1,color2), root,-1, nodeStyle);

       //Data print
       System.out.println("The resultant tree in one line is: \n"+tree.toString());
       System.out.println("The total time in nano seconds is: "+ time+" ns");
    }
    
    /*********************************************************************
    *
    * Method name: Numbers
    *
    * Description of the Method: This method checks if the entered number is a multiple of 2.
    *
    * Return value: 
    *   int, number, the total time it takes for the process to run.
    *
    *********************************************************************/
      public static int Numbers(){
            int number=0;
            boolean power2=false;
            number=sc.nextInt();
            power2=checkPowerOf2(number, number);
            while (power2==false) {
                System.out.println("Error: The matrix size must be power of 2 or the value indicated is too big (max. value allowed is 1024). Try Again.");
                System.out.print("Introduce a number multiple of 2: ");
                number = sc.nextInt();
                power2=checkPowerOf2(number, number);
            }
            return number;
        }
    
    /*********************************************************************
    *
    * Method name: checkTimeNano
    *
    * Description of the Method: This method calculates the time in nano second that the 
    * quadtree method takes to execute.
    *
    * Calling arguments: 
    *   Short[][], matrix, the matrix with the black and white values.
    *   NodeGenTree<String>, root, the node of the tree.
    *   int, depth, the depth of the node
    *   String, nodeStyle, the type of the node.
    *
    * Return value: 
    *   long, totaltime, the total time it takes for the process to run.
    *
    *********************************************************************/
    
    public static long checkTimeNano (Short[][] matrix, NodeGenTree<String> root, int depth, String nodeStyle){
        printMatrix(matrix);
        System.out.println("Structured tree created:");
        long nano =System.nanoTime();
        quadtree(matrix, root, depth, nodeStyle);
        long totaltime=System.nanoTime()-nano;

        return totaltime;
    }

    /*********************************************************************
    *
    * Method name: quadtree
    *
    * Description of the Method: in this method we check if the matrix has the same color and if not, 
    * it is divided into four parts until it is the same color. Goal: compress an image
    *
    * Calling arguments: 
    *   Short[][], matrix, the matrix with the black and white values.
    *   NodeGenTree<String>, root, the root node of the tree.
    *   int, depth, the depth of the node
    *   String, nodeStyle, the type of the node.
    *
    *********************************************************************/
    
    public static void quadtree(Short[][] matrix, NodeGenTree<String> root, int depth, String nodeStyle) {
        NodeGenTree node=new NodeGenTree<>(nodeStyle);
        int colorsEqual = PartitionEqual(matrix);
        if(colorsEqual!=-1){
            NodeGenTree newnode=new NodeGenTree<>(Character.toString(colorsEqual));
            root.addChild(newnode);
            newnode.printNode(depth);
        }else{
            root.printNode(depth);
            root.addChild(node);
            quadtree(PartitionMatrix(matrix, 0, 0, matrix.length/2, matrix.length/2), node, depth+1, nodeStyle);
            quadtree(PartitionMatrix(matrix, 0, matrix.length/2, matrix.length/2, matrix.length), node, depth+1, nodeStyle);
            quadtree(PartitionMatrix(matrix, matrix.length/2, 0, matrix.length, matrix.length/2), node, depth+1, nodeStyle);
            quadtree(PartitionMatrix(matrix, matrix.length/2, matrix.length/2, matrix.length, matrix.length), node, depth+1, nodeStyle);
        }
    }

    /*********************************************************************
    *
    * Method name: PartitionMatrix
    *
    * Description of the Method: In this method we divided the matrix in four part.
    *
    * Calling arguments: A list of the calling arguments, their types, and
    * brief explanations of what they do.
    *
    * Return value: Short[][], res, it's a matrix.
    *
    * Required Files:
    *   Short[][], matrix, i'ts a two dimensional array.
    *   int, rowIni, initial row.
    *   int, columnIni, initial colum.
    *   int, rowEnd, last row.
    *   int, columnEnd, last column  . 
    *
    *********************************************************************/
    
    public static Short[][] PartitionMatrix(Short[][] matrix, int rowIni, int columnIni, int rowEnd, int columnEnd) {
        Short[][] res=new Short[rowEnd-rowIni][columnEnd-columnIni];
        int matrixRows=0;
        int matrixColumns=0;
        
        //printMatrixIndexed(matrix, rowIni, columnIni, rowEnd, columnEnd);
        for (int i=rowIni; i<rowEnd; i++){
            for(int j=columnIni; j<columnEnd; j++){
                res[matrixRows][matrixColumns]=matrix[i][j];
                matrixColumns++;
            }
            matrixColumns=0;
            matrixRows++;
        }

        
        return res;
    }

    /*********************************************************************
    *
    * Method name: PartitionEqual
    *
    * Description of the Method: This method checks that they are the same color.
    *
    * Calling arguments:
    *   Short[][], matrix, i'ts a two dimensional array.
    *
    * Return value: 
    *   int, colorsAreEqual, says if the colors are equal.
    *
    *********************************************************************/
    
    public static int PartitionEqual(Short[][] matrix) {
        int colorsAreEqual=matrix[0][0];

        for (int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                if(matrix[i][j]!=colorsAreEqual) 
                    colorsAreEqual=-1;
                
            }
        }
        return colorsAreEqual;
    }


    /*********************************************************************
    *
    * Method name: printMatrixIndexed
    *
    * Description of the Method: This method print the index of the matrix.
    *
    * Calling arguments: 
    *   Short[][], matrix, i'ts a two dimensional array.
    *   int, rowIni, initial row.
    *   int, columnIni, initial colum.
    *   int, rowEnd, last row.
    *   int, columnEnd, last column  . 
    *
    *********************************************************************/
    
    public static void printMatrixIndexed(Short matrix[][], int rowIni, int columnIni, int rowEnd, int columnEnd){
        for (int i=rowIni; i<rowEnd; i++){
            for(int j=columnIni; j<columnEnd; j++){
                System.out.print(Character.toString(matrix[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    /*********************************************************************
    *
    * Method name: printMatrix
    *
    * Description of the Method: This method prints the matrix.
    *
    * Calling arguments: 
    *   Short[][], matrix, i'ts a two dimensional array. 
    *
    *********************************************************************/
    
    public static void printMatrix(Short matrix[][]){
        System.out.println("Random array generated:");
        for (int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print(Character.toString(matrix[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    /*********************************************************************
    *
    * Method name: generateRandomMatrix
    *
    * Description of the Method: This method create a random two-dimensional array with the values b(black), w(white).
    *
    * Calling arguments: 
    *   int, rows, the number of rows that the matrix will have.
    *   int, columns, the number of columns that the matrix will have.
    *   Short, color1, white color (w).
    *    Short, color2, black color (b).
    *
    * Return value: Short[][], matrix, the two dimensional array created in a random mode.
    *
    *
    * List of Checked Exceptions:
    *   Exception: This Exception occurs when the main method doesn't work well.
    *
    *********************************************************************/
    
    public static Short[][] generateRandomMatrix(int rows, int columns, Short color1, Short color2) throws Exception{
        Short matrix[][]=new Short[rows][columns];

        if(checkPowerOf2(rows,columns)){
            Random r = new Random();
            for (int i=0; i<matrix.length; i++){
                for(int j=0; j<matrix[i].length; j++) 
                    matrix[i][j]=r.nextBoolean() ? color1 : color2;
            }
        }else throw new Exception("The matrix size must be power of 2 or the values indicated are too big");   
        return matrix;
    }

    /*********************************************************************
    *
    * Method name: checkPowerOf2
    *
    * Description of the Method: This method checks if the values are powers of two.
    *
    * Calling arguments: 
    *   int, number1, the number of rows.
    *   int, number2, the number of columns.    
    *
    * Return value: 
    *   boolean, res, if it's false there are not powers of two.
    *
    *********************************************************************/
    
    public static boolean checkPowerOf2(int number1, int number2){
        int [] powersOf2={1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
        boolean number1PowerOf2=false;
        boolean number2PowerOf2=false;

        boolean res=false;
        if(number1==number2) 
            for (int i : powersOf2) {
                if(number1==i) number1PowerOf2=true;
                if(number2==i) number2PowerOf2=true;
            }
        if(number1PowerOf2 && number2PowerOf2) res=true;      

        return res;
    }
}
