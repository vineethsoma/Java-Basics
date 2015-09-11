/* Name: Vineeth Soma
 * Section: 002
 * The program can be run if Recursive.java is used as the main file.
 */

//package assignment4;

import java.util.Scanner;

public class Recursive{

  
    public static void main(String[] args) {
       
        int x=0 ;
        int n=0 ;
        Scanner input = new Scanner(System.in) ;
        System.out.println("Enter x ") ;
        
        x = input.nextInt();
         System.out.println("Enter n ") ;
        n = input.nextInt();
        
       System.out.println( pow(x,n));
        
    }
    
   public static long pow(int x, int n){
        
     
        
        if( n == 0)
            return 1;
        else 
          return   x*pow( x, n-1) ;
       
        
    }
}
