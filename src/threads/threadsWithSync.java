/* Name: Vineeth Soma
 * Section: 002
 * The program can be run if threadsWithJava.java is used as the main file.
 * This pogram is using syncroniztion
 */

package assignment6;

import java.util.concurrent.* ;


public class threadsWithSync {

     
    private Integer sum = new Integer(0);
    public static  threadsWithSync thread = new threadsWithSync() ;
    
    public   synchronized void  add(){
        
        sum = sum + 1   ; 
    }
    
    public int showSum(){
        
        return sum ;
    }
    public static void main(String[] args) {
    
       ExecutorService executor = Executors.newFixedThreadPool(1000); 
      System.out.println( "Program running with synchornization"  ) ; 
       for(int i = 0 ; i<1000 ; i++)
       executor.execute(new AddANumber() ) ;
       
      
       
    
       System.out.println( "The sum is " + thread.showSum() ) ;
       executor.shutdown();
       System.out.println( "Program done"  ) ;
    }
    
    
    
     public static class AddANumber implements Runnable{
          
         
         @Override
        public synchronized void run(){
            thread.add() ;  
        }
     }
}
