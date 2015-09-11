/* Name: Vineeth Soma
 * Section: 002
 * The program can be run if Salary.java is used as the main file.
 */
package assignment4;

import java.util.Scanner;


public class Salary {

    
    public static void main(String[] args) {
      
        double associateSalary = 0 ; 
        double assistantSalary = 0;
        double fullSalary  = 0 ;
        int countAsso = 0;
        int countAss = 0 ;
        int countFull = 0 ;
        
        

        System.out.print("Enter URL: ") ;
        String URLString = new Scanner(System.in).next();
        
        
        try{

            
            java.net.URL url = new java.net.URL(URLString);
            
            Scanner input = new Scanner(url.openStream());
            
            
            String rank ;
            while (input.hasNext()) {
                
                input.next();
                input.next();
                rank = input.next();
            
                
                switch (rank) {
                    case "assistant":
                        assistantSalary += input.nextDouble();
                        countAss++;
                        break;
                    case "associate":
                        associateSalary += input.nextDouble();
                        countAsso++;
                        break;
                    case "full":
                        fullSalary += input.nextDouble();
                        countFull++;
                        break;
                    default:
                        input.next();
                        break;
                }
                
            
                
                    

            
                }
            
        }
            catch(java.util.InputMismatchException ex){
                System.out.println("Input Mismatch");
                
            }
            catch (java.net.MalformedURLException ex) {
                System.out.println("Invalid URL");
            }
            catch (java.io.IOException ex) {
                System.out.println("I/O Errors: no such file");
                
        
            }
            catch (java.util.NoSuchElementException ex){
                System.out.println("No such element exists");
            }
        double totalSalary = assistantSalary + associateSalary + fullSalary ;
        int countTotal = countAss +countAsso + countFull;
        
        
            System.out.println("The total salary for the Assistant Professors is $" + assistantSalary);
            System.out.println("The total salary for the Associate Professors is $" + associateSalary);
            System.out.println("The total salary for the Full Professors is $" + fullSalary);
            System.out.println("The total salary for the faculty is $" + totalSalary);

            System.out.println("The average salary for the Assistant Professors is $" + assistantSalary/countAss);
            System.out.println("The average salary for the Associate Professors is $" + associateSalary/countAsso);
            System.out.println("The average salary for the Full Professors is $" + fullSalary/countFull);
            System.out.println("The average salary for the faculty is $" + totalSalary/countTotal);
        
}

}

