import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI_Tab implements ListSelectionListener{
	private JPanel selectListPanel = new JPanel(new GridLayout(6, 1, 0, 0));
	private SingleSelectionModel seletionModel;
	private int elements = 6;
	
	public JPanel createTabList() {
		
		//selectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//selectList.addListSelectionListener(this);
		this.selectListPanel.setSize(100, 450);
		
		this.addElement(new JButton("���� �����"));
		this.addElement(new JButton("��ü����"));
		this.addElement(new JButton("���ã��"));
		this.addElement(new JButton("�ֱ� �߰��� ��"));
		this.addElement(new JButton("�ֱ� ����� ��"));
		this.addElement(new JButton("���� ���� ����� ��"));
		
		this.selectListPanel.setVisible(true);
		return this.selectListPanel;
	}

	public void addElement(JButton element) {
		selectListPanel.add(element);
		//elements++; 
	}
	public void valueChanged(ListSelectionEvent arg0) {
	// TODO Auto-generated method stub
	
	}
}
