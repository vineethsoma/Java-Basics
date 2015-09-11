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

import javax.swing.JTextField;
import javax.swing.JFrame;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class ServerWindow extends JFrame
{
    private JTextField currentMessage = new JTextField(35);
    private JTextArea messageLog = new JTextArea(7,33);
    private JTextArea connectedUsers = new JTextArea(7, 13);
    private int nMaxUsers = 0;
    private int nConnectedUsers = 0;
    private PrintWriter[] ClientWriters = new PrintWriter[20];
    private ServerSocket serverSocket;
    private int[] OpenClients = new int[5];
    private int currentClientNumber = 0;
    
    public ServerWindow(int nNumberOfUsers)
    {
        this.nMaxUsers = nNumberOfUsers;
        
        JPanel BottomBar = new JPanel();
        BottomBar.setLayout(new FlowLayout());
        
        JButton SendButton = new JButton("Send");
        SendButton.addActionListener(new SendServerMessage());
        
        BottomBar.add(currentMessage);
        BottomBar.add(SendButton);
        
        
        JPanel TextInfo = new JPanel();
        TextInfo.setLayout(new BorderLayout());
        
        this.messageLog.setEditable(false);
        this.connectedUsers.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(messageLog);
        this.currentMessage.addKeyListener(new EnterKeyPressedListener());
        
        TextInfo.add(scrollPane, BorderLayout.WEST);
        TextInfo.add(connectedUsers, BorderLayout.EAST);
        
        this.add(BottomBar, BorderLayout.SOUTH);
        this.add(TextInfo, BorderLayout.CENTER);
        
        messageLog.setBackground(new Color(218,234,238));
        connectedUsers.setBackground(new Color(218,234,238));
        
        
        try
        {
            serverSocket = new ServerSocket(8000);
            messageLog.append("Server started at " + new Date() + '\n');
            
            int clientNo = 1;
            
            while(clientNo <= nMaxUsers)
            {
                messageLog.append("Starting thread for client " + clientNo + " at " + new Date() + '\n');
                
                HandleAClient task = new HandleAClient(serverSocket);
                new Thread(task).start();
                
                clientNo++;
            }
        }
        catch(IOException ex)
        {
            //Fill this in with any exception we might need.
        }
    }
    
    public boolean filter(String msg)
    {
    	if(msg.toLowerCase().contains("ugly")||msg.toLowerCase().contains("bad")||msg.toLowerCase().contains("damn"))
    	{
    		return true;
    	}
    	else
    		return false;
    }
    
    private boolean DisconnectMessage(String msg, int nCurrentClientNumber, String Username)
    {
        if(msg.toLowerCase().contains("bye"))
        {
            int i = 0;
            int j = 0;
            boolean bFound = false;
            
            while(!bFound)
            {
                if(OpenClients[i] == nCurrentClientNumber)
                {
                    for(j = i; OpenClients[j] != -1; j++)
                    {
                        OpenClients[j] = OpenClients[j+1];
                        if(OpenClients[j] != -1)
                        {
                            ClientWriters[OpenClients[j]] = ClientWriters[OpenClients[j+1]];
                        }
                    }
                    
                    bFound = true;
                }
            }
            
            String[] UsernameList = connectedUsers.getText().split("\n");
            String[] NewUsernameList = new String[UsernameList.length - 1];

            bFound = false;
            
            for(i = 0; i < UsernameList.length; i++)
            {
                if(UsernameList[i].equals(Username) && !bFound)
                {
                    bFound = true;
                }
                else
                {
                    if(bFound)
                    {
                        NewUsernameList[i-1] = UsernameList[i];
                    }
                    else
                    {
                        NewUsernameList[i] = UsernameList[i];
                    }
                }
            }
            
            nConnectedUsers--;
            connectedUsers.setText(null);
            for(i = 0; i < NewUsernameList.length; i++)
            {
                connectedUsers.append(NewUsernameList[i] + "\n");
            }
            WriteUsernamesToClients();
            int displayNumber = nConnectedUsers + 1;
            messageLog.append("Re-Starting thread for client " + displayNumber + " at " + new Date() + '\n');    
            HandleAClient task = new HandleAClient(serverSocket);
            new Thread(task).start();
            return true;
        }
        else
        {
            return false;
        }
    }
    
    class SendServerMessage implements ActionListener
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
        String sServerMessage = "Server: " + currentMessage.getText() + "\n";
        messageLog.append(sServerMessage);
        WriteToClients(sServerMessage);
        currentMessage.setText(null);
    }
    
    class HandleAClient implements Runnable
    {
        private Socket socket;
        private ServerSocket serverSocket;
        private boolean bDisconnect = false;
        
        public HandleAClient(ServerSocket GivenServerSocket)
        {
            this.serverSocket = GivenServerSocket;
        }
        
        public void run()
        { 
            try 
            {
            	this.socket = serverSocket.accept();
                int ClientIdentificationNumber = currentClientNumber;
                OpenClients[nConnectedUsers] = ClientIdentificationNumber;
                OpenClients[nConnectedUsers + 1] = -1;
            	
            	BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ClientWriters[ClientIdentificationNumber] = new PrintWriter(socket.getOutputStream());
                String userName = fromClient.readLine();
                messageLog.append("Client "+userName+" connected.\n");
                connectedUsers.append(userName + "\n");
                
                currentClientNumber++;
            	nConnectedUsers++;
                
                WriteUsernamesToClients();
               
                while(!bDisconnect)
                {
                 String userMessage = userName+": "+fromClient.readLine().toString()+"\n";
                 messageLog.append(userMessage);
                 if(!filter(userMessage))
                 {
                    WriteToClients(userMessage);
                 }
                 else
                 {
                     ClientWriters[ClientIdentificationNumber].write("Sorry, your message contains forbidden words.\n"
                                                     + "It cannot be sent to the target client.\n");
                     ClientWriters[ClientIdentificationNumber].flush();
                 }
                 
                 bDisconnect = DisconnectMessage(userMessage, ClientIdentificationNumber, userName);
                }
            }
            catch(IOException e) 
            {
                System.err.println(e);
            }
        }
    }
    
    private void WriteToClients(String message)
    {
        int i = 0;
        
        while(OpenClients[i] != -1)
        {
            this.ClientWriters[OpenClients[i]].write(message);
            this.ClientWriters[OpenClients[i]].flush();
            
            i++;
        }
    }
    
    private void WriteUsernamesToClients()
    {
        int i;
        String sListToSend = new String();
        
        String[] names = connectedUsers.getText().split("\n");
        
        for(i = 0; i < names.length; i++)
        {
            sListToSend += names[i] + " ";
        }
        
        WriteToClients(sListToSend + "ChatProjectUserAdd9240\n");
    }
}
