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

       //printMatrixIndexed(matrix, 8, 8);
       //printMatrixIndexed(matrix, 0, 0, 8, 8);
       //System.out.println();
       //printMatrixIndexed(matrix, 0, 4, 4, 8);
       quadtree(matrix,0,0,8,8);
       
    }
       


    public static void quadtree(Short[][] matrix, int rowIni, int columnIni, int rowEnd, int columnEnd) {
        if (rowIni < rowEnd && columnIni < columnEnd) {
            boolean colorsEqual = Partition(matrix, rowIni, columnIni, rowEnd, columnEnd);
            if(!colorsEqual){
                quadtree(matrix, rowIni, columnIni, rowEnd/2, columnEnd/2);
            }
            
        }
    }

    public static boolean Partition(Short[][] matrix, int rowIni, int columnIni, int rowEnd, int columnEnd) {
        boolean colorsAreEqual=true;
        int firstColor=matrix[0][0];

        for (int i=rowIni; i<rowEnd; i++){
            for(int j=columnIni; j<columnEnd; j++){
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
    }

    public static void printMatrix(Short matrix[][]){
        for (int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
    }

}
}
