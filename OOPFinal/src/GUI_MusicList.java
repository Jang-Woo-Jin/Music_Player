import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI_MusicList implements ListSelectionListener {
	private JPanel musicListPanel = new JPanel(new GridLayout(MusicFile.getMusicFileNum(), 1)); 
	private JList<MusicFile> musicList;
	private DefaultListModel<MusicFile> listModel = new DefaultListModel<MusicFile>();

	public JPanel createListPanel() {
		for(int i = 0 ; i < 100 ; i++) {
			listModel.addElement(new MusicFile("A"));
			listModel.get(i).setName(Integer.toString(i));
		}
		musicList = new JList<MusicFile>(listModel);
		musicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musicList.addListSelectionListener(this);
		
		musicList.setVisible(true);
	
		musicListPanel.add(musicList);
		musicListPanel.setVisible(true);
		return musicListPanel;
	}


	public void valueChanged(ListSelectionEvent e) {

		@SuppressWarnings("unchecked")
		JList<MusicFile> list = (JList<MusicFile>)e.getSource();
	    System.out.println(list.getSelectedValue().getName());
        
	}
}
