/* Name: Vineeth Soma
 * Section: 002
 * The program can be run if client.java is used as the main file.
 * Pleae start the server file first
 */
package assignment6;

import java.io.*;
import java.net.*;
import java.util.* ;


public class client {
    
    public static void main(String[] args) {
       new client() ;
       
    }
    
    
    
    
    public client(){
        
        String[] names = {"John Smith","Vineeth Soma", "John McCain", "Hillary Clinton", "Joe Biden", "Paul Rand" , "Chris Christie" , "Barack Obama" , "Rick Perry" , "Wendy Davis" , "Donald Trump" } ; 
        Scanner input = new  Scanner(System.in) ;
        
        
        
        
        try(Socket socket = new Socket("localhost" , 4444 ) ;
            
            PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);  
            
            BufferedReader inputFromServer = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));){
            
            boolean restart = true ; 
            
            while( restart){        
               Random randomGenerator =  new Random();
                
                int randomInt = randomGenerator.nextInt(10) ;
                String name = names[randomInt] ; 
                boolean status = true ;
                boolean n = false  ; 
                String fromServer ;
                String fromUser ="Hello, are you there?" ;
                String case2="" ; 
                outputToServer.println(fromUser) ; 
                System.out.println("Client: " + fromUser) ;
            
                while (status ) {
                
                    fromServer = inputFromServer.readLine() ; 
                    
                    switch (fromServer) {
                        
                        case "Yes, who is it?":
                            System.out.println("Server: " + fromServer);
                            fromUser = "This is"   ;
                            n = true ; 
                            
                            name = names[randomInt] ;
                            case2 = "Nice to meet you " + name + ". Would you like to continue? (Y/N)" ; 
                            status = true ;
                            break;
                            
                       
                            
                        case "N":
                            status = false ;
                            restart = false ;
                            fromUser = "";
                            break;
                            
                        case "Y":
                           
                            restart = true  ;
                            status = false ;
                            fromUser = "";
                            
                            break;
                            
                            
                         
                    }
                    
                    if( fromServer.equals(case2)){
                        
                        System.out.println("Server: " + fromServer);
                            System.out.print("Client: ");
                            fromUser = input.next() ;
                            status = true ;
                    }
                    
                    if (fromUser != null) { 
                        
                        if(!fromUser.equals("") && !n && status ){
                            
                            if( !fromUser.equals("Y") && !fromUser.equals("N"))
                            System.out.println("Client: " + fromUser ) ;
                            
                            outputToServer.println(fromUser) ;
                        }
                        else if( n ){
                            
                        System.out.println("Client: " + fromUser+ " "+ name);
                        outputToServer.println(fromUser) ;
                        outputToServer.println(name) ;
                        n = false ; 
                     }
                    }
                    
                }
             
        } 
            
    }
            
      
        
        
        
        catch (IOException ex){
            
            System.err.println(ex);
            
           
        }
        
        
    
        
        
    }
}
