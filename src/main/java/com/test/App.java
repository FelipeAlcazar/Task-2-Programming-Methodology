package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


/**
 * Hello world!
 *
 */
public class App 
{
    static GenTree tree=new GenTree<>();
    public static void main(String[] args) throws Exception
    {
       Short matrix[][]={
           {'w','w','w','w','b','b','w','w'},
           {'w','w','w','w','b','b','w','w'},
           {'w','w','b','b','b','b','w','w'},
           {'w','w','b','b','b','b','w','w'},
           {'b','b','b','b','b','b','w','w'},
           {'b','b','b','b','w','w','w','w'},
           {'w','w','w','w','w','w','w','w'},
           {'w','w','w','w','w','w','w','w'}
       };

       NodeGenTree root=new NodeGenTree<>(Character.toString(matrix[0][0]));
       tree.addRoot(root);

       /** 
        * Example indexing
       //P1
       printMatrixIndexed(matrix, 0, 0, 4, 4);
       System.out.println();
       //P2
       printMatrixIndexed(matrix, 0, 4, 4, 8);
       System.out.println();
       //P3
       //printMatrixIndexed(matrix, 4, 0, 8, 4);
       System.out.println();
       //P4
       printMatrixIndexed(matrix, 4, 4, 8, 8);
       */

       //Example random matrix
       //Short color1='w';
       //Short color2='b';
       //quadtree(generateRandomMatrix(8,8,color1,color2), root);
       
       quadtree(matrix, root);
       System.out.print(tree.toString());
    }
       


    public static void quadtree(Short[][] matrix, NodeGenTree<String> root) {
        int colorsEqual = PartitionEqual(matrix);
        if(colorsEqual!=-1){
            root.addChild(new NodeGenTree<>(Character.toString(colorsEqual)));
        }else{
            System.out.println();
            System.out.println("------NEW PARTITION------");
            Short[][] matrix1 = PartitionMatrix(matrix, 0, 0, matrix.length/2, matrix.length/2);
            Short[][] matrix2 = PartitionMatrix(matrix, 0, matrix.length/2, matrix.length/2, matrix.length);
            Short[][] matrix3 = PartitionMatrix(matrix, matrix.length/2, 0, matrix.length, matrix.length/2);
            Short[][] matrix4 = PartitionMatrix(matrix, matrix.length/2, matrix.length/2, matrix.length, matrix.length);
            System.out.println("------PARTITION ENDED------");
            System.out.println();

            quadtree(matrix1, root);
            quadtree(matrix2, root);
            quadtree(matrix3, root);
            quadtree(matrix4, root);
        }

        
    }

    public static Short[][] PartitionMatrix(Short[][] matrix, int rowIni, int columnIni, int rowEnd, int columnEnd) {
        Short[][] res=new Short[rowEnd-rowIni][columnEnd-columnIni];
        int matrixRows=0;
        int matrixColumns=0;
        
        printMatrixIndexed(matrix, rowIni, columnIni, rowEnd, columnEnd);
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


    public static void printMatrixIndexed(Short matrix[][], int rowIni, int columnIni, int rowEnd, int columnEnd){
        for (int i=rowIni; i<rowEnd; i++){
            for(int j=columnIni; j<columnEnd; j++){
                System.out.print(Character.toString(matrix[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMatrix(Short matrix[][]){
        for (int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

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

    public static boolean checkPowerOf2(int number1, int number2){
        int [] powersOf2={1, 2, 4, 16, 32, 64, 128, 256, 512, 1024, 2048};
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
