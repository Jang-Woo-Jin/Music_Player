package GUI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;





import Alarm.AlarmSystem;

class AlarmFrame extends JFrame {
	private AlarmSystem alarmSystem;
	private JLabel textLabel;
	private JLabel hour_label;
	private JLabel minute_label;
	private JComboBox<String> hour;
	private JComboBox<String> minute;
	private JTextArea alarmMessage;
	
	private JButton okButton;
	private JComboBox<String> ampm;
	
	public AlarmFrame(){
		super("AlarmSetting Window");
		this.setSize(420,235);
        this.setLocation(400,200);
        this.setLayout(null);
        
        addHourContent();
        addMinuteContent();
        addAmpmContent();
        addOkButton();
        addAlarmMessage();
        addTextLabel();
        okButton.addActionListener(e -> {
        	this.setVisible(false);
        	
            
        	String selectAmpm = (String)ampm.getSelectedItem();
            selectAmpm = selectAmpm.trim();
            String selectHour = (String) hour.getSelectedItem();
            String selectMinute = (String) minute.getSelectedItem();

        	alarmSystem = new AlarmSystem(alarmMessage);
            
            alarmSystem.setAlarm(selectAmpm, selectHour, selectMinute);
            alarmSystem.start();
            
        });
        this.setVisible(true);
        
	}
	private void addTextLabel(){
		textLabel = new JLabel("알람 시각을 설정해주세요.");
		textLabel.setBounds(20,10,200,30);
		this.add(textLabel);
	}
	private void addAmpmContent(){
		ampm = new JComboBox<String>(new String[] {"오전","오후"});
		ampm.setBounds(40,40,60,30);
		this.add(ampm);
	}
	private void addHourContent(){
		hour_label = new JLabel("Hour : ");
		hour = new JComboBox<String>(new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12"});
		this.add(hour_label);
		hour_label.setBounds(110,30,50,50);
		this.add(hour);
		hour.setBounds(160,40,50,30);
	}
	private void addMinuteContent(){
		minute_label = new JLabel("Minute : ");
		minute = new JComboBox<String>(new String[]{"0","1","2","3","4","5","6","7","8","9","10",
													"11","12","13","14","15","16","17","18","19","20",
													"21","22","23","24","25","26","27","28","29","30",
													"31","32","33","34","35","36","37","38","39","40",
													"41","42","43","44","45","46","47","48","49","50",
													"51","52","53","54","55","56","57","58","59"});
		this.add(minute_label);
		minute_label.setBounds(220,30,50,50);
		this.add(minute);
		minute.setBounds(280,40,50,30);
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
	
}
