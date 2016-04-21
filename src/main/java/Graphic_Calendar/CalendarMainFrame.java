package Graphic_Calendar;

import javax.swing.*;
import java.awt.*;

/**
 * This class is a JFrame, which contains a JComponent with a calendar.
 */

public class CalendarMainFrame extends JFrame {

    private TextCalendar calendar = new TextCalendar();

    public CalendarMainFrame(String title) {
        super(title);

        setPreferredSize(new Dimension(300, 300));
        getContentPane().add(calendar);
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        setLocation(screenSize.width / 3, screenSize.height / 3);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
