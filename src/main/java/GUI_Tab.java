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
		
		this.addElement(new JButton("현재 재생중"));
		this.addElement(new JButton("전체음악"));
		this.addElement(new JButton("즐겨찾기"));
		this.addElement(new JButton("최근 추가한 곡"));
		this.addElement(new JButton("최근 재생한 곡"));
		this.addElement(new JButton("가장 많이 재생한 곡"));
		
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
