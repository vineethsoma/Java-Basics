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

import javax.swing.JLabel;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class ClientWindow extends JFrame
{
    public String userName;
    public String ipAddress;
    private JTextField currentMessage = new JTextField(35);
    private JTextArea messageLog = new JTextArea(7,33);
    private JTextArea connectedUsers = new JTextArea(7, 13);
    
    public PrintWriter toServer;
    public  BufferedReader fromServer;
    public ClientWindow(String userName, String ipAddress)
    {
    	 
    	
        JPanel BottomBar = new JPanel();
        BottomBar.setLayout(new FlowLayout());
        
        JButton SendButton = new JButton("Send");
        SendButton.addActionListener(new ClientWindow.SendClientMessage());
        
        BottomBar.add(currentMessage);
        BottomBar.add(SendButton);
        
        JPanel TextInfo = new JPanel();
        TextInfo.setLayout(new BorderLayout());
        
        this.messageLog.setEditable(false);
        this.connectedUsers.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(messageLog);  //KEW: Added
        this.currentMessage.addKeyListener(new EnterKeyPressedListener());  //KEW: Added listener to message field
        
        TextInfo.add(scrollPane, BorderLayout.WEST);  //KEW changed from messageLog to scrollPane
        
        TextInfo.add(connectedUsers, BorderLayout.EAST);
        
        this.add(BottomBar, BorderLayout.SOUTH);
        this.add(TextInfo, BorderLayout.CENTER);
        
        messageLog.setBackground(new Color(218,234,238));
        connectedUsers.setBackground(new Color(218,234,238));
        
        try 
        {
            Socket socket = new Socket(ipAddress, 8000);
            toServer = new PrintWriter(socket.getOutputStream(),true);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toServer.write(userName + "\n");
            toServer.flush();
            
            GetMessagesFromServer task = new GetMessagesFromServer();
            new Thread(task).start();
        }

        catch (IOException ex)
        {
        	System.out.println(ex.toString() + '\n'); 
        	JOptionPane.showMessageDialog(getParent(), "Sorry, the IP Address is incorrect or the server is down");
        	System.exit(0);
        }
       
    }
    
       private class SendClientMessage implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                AppendText();
            }
        }
        
        class EnterKeyPressedListener implements KeyListener    //Added the EnterKeyPressedListener
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                if (e.getKeyChar() == KeyEvent.VK_ENTER)
                {
                    AppendText();
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                //Stubbed out functions needed because the function implements KeyListener
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                //Stubbed out functions needed because the function implements KeyListener
            }
        }
    
        private void AppendText()
        {
            //messageLog.append(userName+": " + currentMessage.getText() + "\n");
            toServer.write(currentMessage.getText() + "\n");
            toServer.flush();
            if(currentMessage.getText().toLowerCase().contains("bye"))
            {
                ClientSetup.CloseClientFrame();
                System.exit(0);
            }
            currentMessage.setText(null);
        }
        
        private class GetMessagesFromServer implements Runnable
        {
            public void run()
            {
                try
                {
                    while(true)
                    {
                        String sServerMessage = fromServer.readLine();
                        if(sServerMessage.contains("ChatProjectUserAdd9240"))
                        {
                            connectedUsers.setText(null);
                            String[] Users = new String[6];
                            
                            Users = sServerMessage.split(" ");
                            
                            int i = 0;
                            while(!Users[i].equals("ChatProjectUserAdd9240"))
                            {
                                connectedUsers.append(Users[i] + "\n");
                                i++;
                            }
                        }
                        else
                        {
                            messageLog.append(sServerMessage + "\n");
                        }
                    }
                }
                catch(IOException ex)
                {
                    System.err.println(ex);
                }
            }
        }
}