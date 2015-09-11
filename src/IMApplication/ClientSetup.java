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

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class ClientSetup extends JFrame
{
	public JTextField userName = new JTextField(10);
	private JLabel userNlabel = new JLabel("User Name: ");
	public JTextField serverIp = new JTextField(10);
	private JLabel ipLabel = new JLabel("IP Address: ");
        private static ClientWindow frame;
    
	 public ClientSetup()
	 {
		 JPanel panel = new JPanel();
		 panel.setLayout(new FlowLayout());
		 
		 JButton loginButton = new JButton("Login");
		 loginButton.addActionListener(new LoginButtonPressed());
		 
		 panel.add(userNlabel);
		 panel.add(userName);
		 panel.add(ipLabel);
		 panel.add(serverIp);
		 panel.add(loginButton);
		 
		 this.add(panel);
	 }
	
     		
	 
	 class LoginButtonPressed implements ActionListener
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	            String user = userName.getText();
	            String ip = serverIp.getText();
	            if(ip.isEmpty())
	            {
	            	frame = new ClientWindow(user,"localhost");
	             	frame.userName=user;
	             	frame.ipAddress= ip;
	             	frame.setTitle("Chat Window- Client: "+user);
	                 frame.setSize(530, 490);
	                 frame.setLocationRelativeTo(null);
	                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                                                                                                                                                                                                                                                                                                                                                              
	                 frame.setVisible(true); 
	                 chatProgramClient.closeSetup();
	                 
	                
		        }
	            
	            else
	            {
	            	frame = new ClientWindow(user,ip);
	             	frame.userName=user;
	             	frame.ipAddress= ip;
	             	frame.setTitle("Chat Window- Client: "+user);
	                 frame.setSize(530, 490);
	                 frame.setLocationRelativeTo(null);
	                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                                                                                                                                                                                                                                                                                                                                                              
	                 frame.setVisible(true); 
	                 chatProgramClient.closeSetup();
	                 
	                
		        }
	            }
	            
	    }
         
         public static void CloseClientFrame()
         {
             frame.dispose();
         }
	 
}
