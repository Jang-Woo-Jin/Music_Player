import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.*;

import javax.swing.*;

public class GUI_PlayerTab extends JPanel{
	
    File bip;
    Media hit;
    MediaPlayer mediaPlayer;
    JButton playButton;
    JButton pauseButton;
	final JFXPanel fxPanel = new JFXPanel();
	public GUI_PlayerTab() {
		this.initialize();
	}
	
    public Container initialize(){
   
       this.add(fxPanel);
        
        this.setPreferredSize(new Dimension(400, 300));
        this.setBackground(Color.BLACK);

        setMusic();
        setButtons();
        
        Platform.runLater(new Runnable() {
            public void run() {
                initFX(fxPanel);
            }
        }); 

        this.setVisible(true);
        return this;
    }

    private void initFX(JFXPanel fxPanel) {
        Scene scene = initScene();
        fxPanel.setScene(scene);
    }

    private Scene initScene(){
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root, javafx.scene.paint.Color.GREENYELLOW );
        
        return (scene);
    } 
    
    private void setButtons() {
    	
        playButton = new JButton("¢º");
       
    	/* buttons setting */
        playButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
        	{
    			if(playButton.getText().equals("¢º")) { 
    				mediaPlayer.play(); 
    	     		System.out.println("You clicked the play button");
    	     		playButton.setText(" || ");
    			}
    			else {
    				mediaPlayer.pause(); 
    				System.out.println("You clicked the pause button");
    				playButton.setText("¢º");
    			}
        	}
    	});
       
        
        this.add(playButton);
       
        
        /* buttons setting end */
       
    	
    }
    
    private void setMusic() {
    	bip = new File(System.getProperty("user.home") + "/Downloads/¡¼Lily¡½«Ñ«é«Î«¤«É¡¼«ª«ê«¸«Ê«ëÍØ¡½.mp3"); 
        System.out.println(bip.toURI().toString()); 
        hit = new Media(bip.toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
    }
    
    

}