package GUI;

import javax.swing.*;

/**
 * Created by Elliad on 2015-12-08.
 */
public class ErrorDetector {
    public ErrorDetector() {
        JFrame errorFrame = new JFrame("Error");
        errorFrame.add(new JLabel("\tCannot Find chosen File"));

        errorFrame.setSize(200, 50);
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        errorFrame.setVisible(true);
    }
}
