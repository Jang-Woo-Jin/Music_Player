import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI_MusicList implements ListSelectionListener {
	private JPanel musicListPanel = new JPanel(new GridLayout(0, 1));
	private JList<String> musicList;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private SingleSelectionModel musicListSeletion;
	 
	 
	public JPanel createListPanel() {
		for(int i = 0 ; i < 100 ; i++)
		listModel.addElement(Integer.toString(i));

		
		musicList = new JList<String>(listModel);
		musicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musicList.addListSelectionListener(this);
		
		
		musicList.setVisible(true);
		
		musicListPanel.add(musicList);
		musicListPanel.setVisible(true);
		return musicListPanel;
	}


	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
