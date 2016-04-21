package Graphic_Calendar;

import javax.swing.*;
import java.awt.*;

/**
 * This program prints calendar of the month with highlighted today to a window.
 */

public class MyGUICalendar {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new CalendarMainFrame("Calendar");

                Toolkit kit = Toolkit.getDefaultToolkit();
                Dimension screenSize = kit.getScreenSize();

                frame.setPreferredSize(new Dimension(300, 300));
                frame.setLocation(screenSize.width / 3, screenSize.height / 3);
                frame.setResizable(false);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
