import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class GUI_PlayButton extends JButton{
	
	
	public void OnCreate() {
		this.setText("Play");
		this.setSize(30, 30);
		this.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
        	{
    			//Execute when button is pressed
    			
    			System.out.println("You clicked the button");
        	}
    	});
	}
	
	public GUI_PlayButton() {
		OnCreate();
	}
}
