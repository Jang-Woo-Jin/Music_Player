package GUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



import Alarm.AlarmSystem;
import Music.CurrentMusic;

public class AlarmFrame extends JFrame {
	private AlarmSystem alarmSystem;
	
	private JLabel hour_label;
	private JLabel minute_label;
	private JTextField hour;
	private JTextField minute;
	private JTextArea alarmMessage;
	
	private JButton okButton;
	private JComboBox<String> ampm;
	
	private JFrame alarmMessageFrame;
	
	public AlarmFrame(String s){
		super(s);
		this.setSize(420,235);
        this.setLocation(400,200);
        this.setLayout(null);
        
        addHourContent();
        addMinuteContent();
        addAmpmContent();
        addOkButton();
        addAlarmMessage();
        okButton.addActionListener(e -> {
        	this.setVisible(false);
        	alarmSystem = new AlarmSystem();
            
        	String selectAmpm = (String)ampm.getSelectedItem();
            selectAmpm = selectAmpm.trim();
            String selectHour = hour.getText().trim();
            String selectMinute = minute.getText().trim();
            
            alarmMessageFrame = new JFrame("Alarm Message");
            alarmMessageFrameInit();
            
            alarmSystem.setAlarm(selectAmpm, selectHour, selectMinute);
            alarmSystem.run();
            alarmMessageFrame.setVisible(true);
        });
        this.setVisible(true);
        
	}
	private void addAmpmContent(){
		ampm = new JComboBox<String>(new String[] {"  ","오전","오후"});
		ampm.setBounds(40,40,60,30);
		this.add(ampm);
	}
	private void addHourContent(){
		hour_label = new JLabel("Hour : ");
		hour = new JTextField("   ");
		this.add(hour_label);
		hour_label.setBounds(110,30,50,50);
		this.add(hour);
		hour.setBounds(160,35,40,40);
	}
	private void addMinuteContent(){
		minute_label = new JLabel("Minute : ");
		minute = new JTextField("   ");
		this.add(minute_label);
		minute_label.setBounds(220,30,50,50);
		this.add(minute);
		minute.setBounds(280,35,40,40);
	}
	private void addOkButton(){
		okButton = new JButton("OK");
		okButton.setBounds(330,140,60,40);
		this.add(okButton);
	}
	private void addAlarmMessage(){
		alarmMessage = new JTextArea();
		alarmMessage.setBounds(40,80,280,100);
		this.add(alarmMessage);
	}
	private void alarmMessageFrameInit(){
		 alarmMessageFrame.add(alarmMessage);
         alarmMessageFrame.setSize(400, 200);
         alarmMessageFrame.setLocationRelativeTo(null);
         alarmMessage.setEditable(false);
         alarmMessage.setBackground(Color.white);
	}
}
