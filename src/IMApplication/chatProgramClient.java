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

import javax.swing.JFrame;

public class chatProgramClient extends JFrame
{
	static ClientSetup frame = new ClientSetup ();

    public static void main(String[] args) 
    {
    	 frame.setTitle("Chat Program: Client Setup");
         frame.setSize(400, 100);
         frame.setLocationRelativeTo(null); // Center the frame
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);

    }
    
    public static void closeSetup()
    {
        frame.dispose();
    }

}
