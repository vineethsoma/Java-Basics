/* Name: Vineeth Soma
 * Section: 002
 * The program can be run if TestFrame.java is used as the main file.
 */
import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;

public class TestFrame{

        private static JFrame frame = new JFrame("Color") ;
        private static JPanel colorDisplay = new JPanel() ; 
        private static JPanel buttonsArea = new JPanel() ;
        private static JComboBox colorOptions = new JComboBox(new Object[]{"<unfilled>","Red", "Green", "Blue"});
        
    public static void main(String[] args){
        
        
        
        
        
        //Setting to the frame window
       
        frame.add(colorDisplay) ; 
        frame.add(buttonsArea) ;
        frame.setLayout(new FlowLayout());
        frame.setSize(350,250);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        buttonsArea.setBackground(Color.LIGHT_GRAY) ;
        buttonsArea.add(colorOptions) ; //Adds the combo box
        colorDisplay.setSize(200, 200);
        colorDisplay.setBackground(Color.LIGHT_GRAY) ;
        colorDisplay.setPreferredSize(new Dimension(200,200 ));
        
        
        
        colorOptions.addItemListener(new ItemListener(){ //Adds the listener to the comboxbox 
            @Override
            public void itemStateChanged(ItemEvent e){
                setSelectedColor(colorOptions.getSelectedIndex());
            }
        });
        
        
        
        
    }
    
    public static void setSelectedColor(int index){ //Gives combobox colors
            Color[] selectedColor =new Color[]{ Color.LIGHT_GRAY,Color.red,Color.green,Color.blue} ;
            
            colorDisplay.setBackground(selectedColor[index]) ;
            
            
        }
    
    
      
    }

