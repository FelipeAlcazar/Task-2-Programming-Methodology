package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws IOException
    {
        /** 
         GenTree tree=new GenTree<>();
        NodeGenTree root=new NodeGenTree<>("my root");
        NodeGenTree child1=new NodeGenTree<>("hijoRoot1");
        NodeGenTree child2=new NodeGenTree<>("hijoDelHijo1");
        NodeGenTree root2=new NodeGenTree<>("Anotherroot");
        tree.addRoot(root);
        tree.addChild(root, child1);
        tree.addChild(child1, child2);
        tree.addChild(root, root2);
        System.out.print(tree.toString());
       */

       Short matrix[][]={
           {1,1,1,1,0,0,1,1},
           {1,1,1,1,0,0,1,1},
           {1,1,0,0,0,0,1,1},
           {1,1,0,0,0,0,1,1},
           {0,0,0,0,0,0,1,1},
           {0,0,0,0,1,1,1,1},
           {1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1}
       };

       /** 
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
       quadtree(matrix);
    }
       


    public static void quadtree(Short[][] matrix) {
        boolean colorsEqual = PartitionEqual(matrix);
        if(colorsEqual){

        }else if(!colorsEqual){
            System.out.println();
            System.out.println("------NEW PARTITION------");
            Short[][] matrix1 = PartitionMatrix(matrix, 0, 0, matrix.length/2, matrix.length/2);
            Short[][] matrix2 = PartitionMatrix(matrix, 0, matrix.length/2, matrix.length/2, matrix.length);
            Short[][] matrix3 = PartitionMatrix(matrix, matrix.length/2, 0, matrix.length, matrix.length/2);
            Short[][] matrix4 = PartitionMatrix(matrix, matrix.length/2, matrix.length/2, matrix.length, matrix.length);
            System.out.println("------PARTITION ENDED------");
            System.out.println();

            quadtree(matrix1);
            quadtree(matrix2);
            quadtree(matrix3);
            quadtree(matrix4);
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

    public static boolean PartitionEqual(Short[][] matrix) {
        boolean colorsAreEqual=true;
        int firstColor=matrix[0][0];

        for (int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                if(matrix[i][j]!=firstColor){
                    colorsAreEqual=false;
                }
            }
        }
        return colorsAreEqual;
    }


    public static void printMatrixIndexed(Short matrix[][], int rowIni, int columnIni, int rowEnd, int columnEnd){
        for (int i=rowIni; i<rowEnd; i++){
            for(int j=columnIni; j<columnEnd; j++){
                System.out.print(matrix[i][j]);
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
}
