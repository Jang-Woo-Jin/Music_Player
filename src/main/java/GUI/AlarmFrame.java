package GUI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Alarm.AlarmSystem;
import Music.CurrentMusic;

public class AlarmFrame extends JFrame {
	private AlarmSystem alarmSystem;
	
	private JLabel hour_label;
	private JLabel minute_label;
	private JTextField hour;
	private JTextField minute;
	
	private JButton okButton;
	private JComboBox<String> ampm;
	
	public AlarmFrame(String s){
		super(s);
		this.setSize(420,250);
        this.setLocation(400,200);
        this.setLayout(null);
        
        addHourContent();
        addMinuteContent();
        addAmpmContent();
        addOkButton();
        okButton.addActionListener(e -> {
        	alarmSystem = new AlarmSystem();
            String selectAmpm = (String)ampm.getSelectedItem();
            selectAmpm = selectAmpm.trim();
            String selectHour = hour.getText().trim();
            String selectMinute = minute.getText().trim();
            alarmSystem.setAlarm(selectAmpm, selectHour, selectMinute);
        });
        this.setVisible(true);
        
	}
	private void addAmpmContent(){
		ampm = new JComboBox<String>(new String[] {"  ","오전","오후"});
		ampm.setBounds(40,60,60,30);
		this.add(ampm);
	}
	private void addHourContent(){
		hour_label = new JLabel("Hour : ");
		hour = new JTextField("   ");
		this.add(hour_label);
		hour_label.setBounds(110,50,50,50);
		this.add(hour);
		hour.setBounds(160,55,40,40);
	}
	private void addMinuteContent(){
		minute_label = new JLabel("Minute : ");
		minute = new JTextField("   ");
		this.add(minute_label);
		minute_label.setBounds(220,50,50,50);
		this.add(minute);
		minute.setBounds(280,55,40,40);
	}
	private void addOkButton(){
		okButton = new JButton("OK");
		okButton.setBounds(300,110,70,50);
		this.add(okButton);
	}
}
