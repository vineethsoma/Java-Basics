/*Class Section: 2336.002
 * Programmers: Kyle Webster
 * 			   Chen Ming Li
 * 			   Sumanth Nelluru
 * 			   Vineeth Soma	
 * How to Run:
 * 			   1.Launch the ChatProgram.java (Server Program).
 * 			   2.Enter number of clients(Maximum 5)
 * 	           3.Launch the ChatProgramClient.java(Client Program)
 * 			   4.Enter the user name and IP address of the server(Leave it blank if the server is local)
 * 			   5.Enjoy! 
 * */

package IMApplication;

import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ServerSetup extends JFrame
{
    private JTextField numUsersField = new JTextField(2);
    
    public ServerSetup()
    {
        JPanel bottomBar = new JPanel();
        bottomBar.setLayout(new FlowLayout());
        
        JButton Okay = new JButton("Okay");
        Okay.addActionListener(new OkayButtonPressed());
        
        bottomBar.add(new JLabel("Please enter the number of users (max of 5)"));
        bottomBar.add(numUsersField);
        bottomBar.add(Okay);
        
        this.add(bottomBar);
        
    }
    
    class OkayButtonPressed implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	try{
        		int maxNumUsers = Integer.parseInt(numUsersField.getText());
        		if(maxNumUsers>5)
                {
                 JOptionPane.showMessageDialog(getParent(), "Sorry, the maximum user number is 5");	
                }
                else
                {
                	ServerWindow frame = new ServerWindow(maxNumUsers);
                	frame.setTitle("Chat Program: Server Setup");
                	frame.setSize(530, 490);
                	frame.setLocationRelativeTo(null); // Center the frame
                	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                	frame.setVisible(true);
                	
                	ChatProgram.closeSetup();
                }
        	   }
        	catch(NumberFormatException ex)
        	{
        		JOptionPane.showMessageDialog(getParent(), "Please enter a number.");	
        	}
            
        }
    }
}
