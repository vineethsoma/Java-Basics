/* Name: Vineeth Soma
 * Section: 002
 * The program can be run if threadsWithoutJava.java is used as the main file.
 * This pogram is not using syncroniztion
 */

package threads ; 

import java.util.concurrent.* ;


public class threadsWithoutSync {

     
    private Integer sum = new Integer(0);
    public static  threadsWithoutSync  thread = new threadsWithoutSync() ;
    
    public void  add(){
        
		
        sum = sum + 1   ; 
    }
    
    public int showSum(){
        
        return sum ;
    }
    public static void main(String[] args) {
    
       ExecutorService executor = Executors.newFixedThreadPool(1000); 
       System.out.println( "Program running without synchornization"  ) ; 
       for(int i = 0 ; i<1000 ; i++){
       executor.execute(new AddANumber() ) ;
       
      
       
    }
       System.out.println( "The sum is " + thread.showSum() ) ;
       executor.shutdown();
       System.out.println( "Program done"  ) ;
    }
    
    
    
     public static class AddANumber implements Runnable{
          
         
         @Override
        public void run(){
            thread.add() ; 
            
        }
     }
}
