package GUI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Alarm.AutomaticShutdown;

class ShutdownFrame extends JFrame{
	private JComboBox<String> selectTimeComboBox;
	private JLabel selectTimeLabel;
	private JLabel textLabel;
	private JButton okButton;
	
	ShutdownFrame(){
		super("AutomaticShutdown Window");
		this.setSize(280,155);
		this.setLocation(400,200);
		this.setLayout(null);
		addSelectTimeComboBox();
		addTextLabel();
		addOkButton();
		okButton.addActionListener(e -> {
        	this.setVisible(false);
        	
        	String selectMinute = (String)selectTimeComboBox.getSelectedItem();
            selectMinute = selectMinute.trim();
           
        	AutomaticShutdown automaticShutdown = new AutomaticShutdown();
        	automaticShutdown.setShutdown(selectMinute);
        	automaticShutdown.start();
        });
		this.setVisible(true);
	}
	private void addTextLabel(){
		textLabel = new JLabel("몇 분후 종료하시겠습니까?");
		textLabel.setBounds(20,15,200,30);
		this.add(textLabel);
	}
	private void addSelectTimeComboBox(){
		selectTimeComboBox = new JComboBox<String>(new String[]{"1","5","10","15","30","45","60","90","120"});
		selectTimeComboBox.setBounds(40, 55, 60, 30);
		selectTimeLabel = new JLabel("분");
		selectTimeLabel.setBounds(110, 50, 60, 40);
		this.add(selectTimeLabel);
		this.add(selectTimeComboBox);
	}
	private void addOkButton(){
		okButton = new JButton("OK");
		okButton.setBounds(160,50,60,40);
		this.add(okButton);
	}
	
}
