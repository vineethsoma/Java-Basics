/* Name: Vineeth Soma
 * Section: 002
 * The program can be run if matrix.java is used as the main file.
 */

package assignment5;

import java.util.Scanner;
import java.util.concurrent.* ;


public class  matrix {

    double[][] a = new double[3][3] ; 
    double[][] b = new double[3][3] ;
    double[][] c = new double[3][3] ;
    
     
    
      matrix(){
        
       Scanner input = new Scanner(System.in);
       System.out.println("Performing AxB matrix multiplication \n");
       System.out.println("Enter the contents of 3x3 matrix A:");
       for( int i = 0 ; i<3 ; i++ )
           for( int j = 0 ; j<3 ; j++)
               a[i][j] = input.nextDouble();
       
       System.out.println("Enter the contents of 3x3 matrix B:");
       for( int i = 0 ; i<3 ; i++ )
           for( int j = 0 ; j<3 ; j++)
               b[i][j] = input.nextDouble();
       
    }
    public static void  main(String[] args) {
        
        
         matrix m = new matrix() ;
        
        
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        
        for( int i = 0 ; i<3 ; i++ )
           for( int j = 0 ; j<3 ; j++)
               executor.execute( new multiply( m , i , j  )) ;
        
        
        
        System.out.println("The product of AxB is : ");
        
        for( int i = 0 ; i<3 ; i++ ){
            System.out.print( "\n ");
            for( int j = 0 ; j<3 ; j++){
                System.out.print( "\t ");
                
                System.out.print( m.c[i][j]  );
        
               }
       
        }
    
    } 

    public static class multiply implements Runnable{
    
        
        int i ; 
        int j ;
        matrix m ;
    
        multiply(matrix m , int i , int j){
        
            this.m = m;
            this.i = i ; 
            this.j = j ;
            
        
   
    }
    
    
        @Override
        public void run(){
            for(int k = 0 ; k<3 ; k++)
            m.c[i][j] += m.a[i][k] * m.b[k][j] ;
   
        }
    
    
    }



}

