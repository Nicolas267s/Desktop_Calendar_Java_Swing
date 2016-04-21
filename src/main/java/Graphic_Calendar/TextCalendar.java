package Graphic_Calendar;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DAY_OF_WEEK;

/**
 * This class is a JComponent, which contains the calendar itself.
 */

public class TextCalendar extends JComponent {
    private SimpleDateFormat month = new SimpleDateFormat("MMMM");
    private SimpleDateFormat year = new SimpleDateFormat("yyyy");
    private SimpleDateFormat day = new SimpleDateFormat("d");

    private Date date = new Date();
    private Calendar cal;
    private Calendar today;

    private Font monthAndYearFont = new Font("Arial", Font.BOLD, 16);
    private Font datesAndWeekdaysFont = new Font("Arial", Font.BOLD, 12);
    private Font todayFont = new Font("Arial", Font.BOLD, 16);


    @Override
    public void paintComponent(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Setups a font and prints name of the month.
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(monthAndYearFont);
        g.drawString(getMonth().format(getDate()), 34, 36);

        // Setups a font color back to default white.
        g.setColor(Color.white);

        // Prints the year.
        g.drawString(getYear().format(getDate()), 220, 36);

        today = Calendar.getInstance();
        today.setTime(getDate());

        cal = Calendar.getInstance();
        cal.setTime(getDate());

        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -cal.get(DAY_OF_WEEK) + 2);

        printAllWeekdayNames(g);
        printAllDates(g);
    }

    /* This method prints all weekday names */
    public void printAllWeekdayNames(Graphics g){
        String[] unSortedShortWeekdays = new DateFormatSymbols().getShortWeekdays();
        String[] weekdays = new String[7];

        // getShortWeekdays() returns array with empty [0], and sunday, as the first day of the week.
        System.arraycopy(unSortedShortWeekdays, 2, weekdays, 0, 6);
        System.arraycopy(unSortedShortWeekdays, 1, weekdays, 6, 1);

        // Coordinates to draw texts into the component.
        int x = 50;
        int y = 70;

        for (String s : weekdays) {
            g.setFont(datesAndWeekdaysFont);

            if (s.equals(weekdays[5]) || s.equals(weekdays[6])) {
                // For a weekend days using a red font color.
                g.setColor(new Color(255, 10, 10));
                // In different locales getShortWeekdays() returns names with different lengths,
                // so we have to make sure that week day names will not be longer than 2 chars.
                g.drawString((s.substring(0, 2)), x, y);
                x += 30;
                g.setColor(Color.white);

            } else {
                g.drawString((s.substring(0, 2)), x, y);
                x += 30;
            }
        }
    }

    /*
     *  This method prints all dates of the month
     *
     *  "day * 30 + 50"
     *  counts x coordinates for every date,
     *  where "30" is the distance between dates
     *  and "50" is an indent from the left edge of the component.
     *
     *  "week * 25 + 105"
     *  counts y coordinates for every date,
     *  where "25" is this distance between lines
     *  and 105 is an indent from the top of the component.
     *
     */
    public void printAllDates(Graphics g) {

        for (int week = 0; week < 6; week++) {
            for (int day = 0; day < 7; day++) {
                String dateToPrint = getDay().format(cal.getTime());
                if (dateToPrint.length() < 2) {
                    // If date is a one-digit number,
                    // adding spaces to align it to the right.
                    dateToPrint = "  " + dateToPrint;
                }

                if (cal.getTime().equals(getDate())) {
                    // Setup a font and font color for "today".
                    g.setFont(todayFont);
                    g.setColor(new Color(0, 180, 0));

                    g.drawString(dateToPrint, day * 30 + 48, week * 25 + 106);

                    // Setup default white font color.
                    g.setColor(Color.white);

                } else if (cal.get(Calendar.MONTH) != Calendar.getInstance().get(Calendar.MONTH)) {
                    // Setup a font and font color for dates of the previous and the next months.
                    g.setFont(datesAndWeekdaysFont);
                    g.setColor(new Color(100, 100, 100));

                    g.drawString(dateToPrint, day * 30 + 50, week * 25 + 105);

                    // Setup default white font color.
                    g.setColor(Color.white);

                } else if (day > 4) {
                    // Setup a font and font color for dates of the weekends.
                    g.setFont(datesAndWeekdaysFont);
                    g.setColor(new Color(255, 10, 10));

                    g.drawString(dateToPrint, day * 30 + 50, week * 25 + 105);

                    // Setup default white font color.
                    g.setColor(Color.white);

                } else {
                    // Printing all other dates(workdays).
                    g.setFont(datesAndWeekdaysFont);
                    g.setColor(Color.white);
                    g.drawString(dateToPrint, day * 30 + 50, week * 25 + 105);
                }
                cal.add(Calendar.DATE, +1);
            }
        }
    }

    public SimpleDateFormat getMonth() {
        return month;
    }

    public SimpleDateFormat getYear() {
        return year;
    }

    public SimpleDateFormat getDay() {
        return day;
    }

    public Date getDate() {
        return date;
    }
}
