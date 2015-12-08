package GUI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Alarm.AutomaticShutdown;

class ShutdownFrame extends JFrame{	// shutdown frame extends jframe to make shutdonw setting window
	private JComboBox<String> selectTimeComboBox;
	private JLabel selectTimeLabel;
	private JLabel textLabel;
	private JButton okButton;
	
	ShutdownFrame(){	// make shutdown frame
		super("AutomaticShutdown Window");
		this.setSize(280,155);
		this.setLocation(400,200);
		this.setLayout(null);
		addSelectTimeComboBox();
		addTextLabel();
		addOkButton();
		okButton.addActionListener(e -> {	// when ok button is clicked, make automatic shutdown
        	this.setVisible(false);
        	
        	String selectMinute = (String)selectTimeComboBox.getSelectedItem();
            selectMinute = selectMinute.trim();
        	AutomaticShutdown.getInstance().setShutdown(selectMinute);
        	if(AutomaticShutdown.getInstance().getStatus())	// if already running, change to later thing
        		AutomaticShutdown.getInstance().start();
        });
		this.setVisible(true);
	}
	private void addTextLabel(){	// add text label
		textLabel = new JLabel("몇 분후 종료하시겠습니까?");
		textLabel.setBounds(20,15,200,30);
		this.add(textLabel);
	}
	private void addSelectTimeComboBox(){	// add select time
		selectTimeComboBox = new JComboBox<String>(new String[]{"1","5","10","15","30","45","60","90","120"});
		selectTimeComboBox.setBounds(40, 55, 60, 30);
		selectTimeLabel = new JLabel("분");
		selectTimeLabel.setBounds(110, 50, 60, 40);
		this.add(selectTimeLabel);
		this.add(selectTimeComboBox);
	}
	private void addOkButton(){	// add ok button
		okButton = new JButton("OK");
		okButton.setBounds(160,50,60,40);
		this.add(okButton);
	}
	
}
