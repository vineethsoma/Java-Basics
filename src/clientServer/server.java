/* Name: Vineeth Soma
 * Section: 002
 * The program can be run if server.java is used as the main file.
 * Pleae start the server file first
 */
package assignment6;

 import java.io.*;
 import java.net.*;
 import java.util.*;

public class server {

    
    public static void main(String[] args) {
        new server() ;
        
    }
    
    public server(){
        
        
       
        
        try ( 
            ServerSocket serverSocket = new ServerSocket(4444);
            Socket clientSocket = serverSocket.accept();
            PrintWriter outputToClient =
            new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader inputFromClient = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
        ) {
           System.out.println("Server started.. ") ;
           
            
           String fromServer ="";
           String fromUser ;
           String name ;      
           boolean n = false ;    
                
                
                
                
            while ((fromUser = inputFromClient.readLine()) != null) {
                
                
                
               switch (fromUser) {
                   
                   case "Hello, are you there?":
                       System.out.println("Client: " + fromUser);
                       fromServer = "Yes, who is it?";
                       break;
                   case "This is":
                       name = inputFromClient.readLine() ; 
                       System.out.println("Client: " + fromUser + " " + name );
                       
                       fromServer = "Nice to meet you " + name + ". Would you like to continue? (Y/N)" ;
                       
                       n = true ; 
                       break;
                       
                       case "N":
                           System.out.println("Client: " + fromUser  );
                            fromServer = "N" ;
                            break;
                           
                        case "Y":
                            System.out.println("Client: " + fromUser  );
                            fromServer = "Y" ;
                            break;
               }

            
            if (fromServer != null  ) {   
                
                if( fromServer != "Y" && fromServer!="N")
                System.out.println("Server: " + fromServer);
                
                outputToClient.println(fromServer) ;
                
            
                
                
            }
            
           
                

                
        }               
                 
                
                
                
                
                
            
           }
                   
        catch(IOException ex){
            
            
            System.err.println(ex) ;
            
            
            
        }
    }

    
    
}