import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarApp extends JFrame {
    private JLabel monthLabel;
    private JPanel calendarPanel;
    private Calendar calendar;

    public CalendarApp() {
        setTitle("Calendar App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        monthLabel = new JLabel("", JLabel.CENTER);
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7));

        // Initialize calendar to current month
        calendar = new GregorianCalendar();
        updateCalendar();

        // Add components to the frame
        add(monthLabel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);

        // Add navigation buttons
        JButton prevButton = new JButton("<< Prev");
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calendar.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });
        JButton nextButton = new JButton("Next >>");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calendar.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateCalendar() {
        // Clear previous month's days
        calendarPanel.removeAll();

        // Set month label
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
        monthLabel.setText(sdf.format(calendar.getTime()));

        // Add day names labels at the top of the calendar panel
        String[] dayNames = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        for (String dayName : dayNames) {
            JLabel nameLabel = new JLabel(dayName, JLabel.CENTER);
            nameLabel.setForeground(Color.BLACK); // Set color to distinguish from day labels
            calendarPanel.add(nameLabel);
        }

        // start day nname of every month of first week
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Add empty labels for days before the first day of the month
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        // Add day labels for the entire month
        int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDayOfMonth; i++) {
            JLabel dayLabel = new JLabel(Integer.toString(i), JLabel.CENTER);
            // Add padding
            dayLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            dayLabel.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    // Highlight with blue border on hover
                    dayLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                }

                public void mouseExited(MouseEvent e) {
                    // Remove border on mouse exit
                    dayLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                    highlightCurrentDay(firstDayOfWeek);
                }

                public void mouseClicked(MouseEvent e) {
                    int day = Integer.parseInt(dayLabel.getText());
                    JOptionPane.showMessageDialog(null, getFullDateInfo(day));
                }
            });
            calendarPanel.add(dayLabel);
        }

        this.highlightCurrentDay(firstDayOfWeek);

        // Repaint the frame
        validate();
        repaint();
    }

    private void highlightCurrentDay(int firstDayOfWeek) {
        // Highlight current day with box design
        Calendar today = Calendar.getInstance();
        if (calendar.get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
                calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
            int currentDay = today.get(Calendar.DAY_OF_MONTH);
            JLabel currentDayLabel = (JLabel) calendarPanel.getComponent(currentDay + firstDayOfWeek + 7 - 2);
            currentDayLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Add border
        }
    }

    private String getFullDateInfo(int day) {
        calendar.set(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM, yyyy");
        return sdf.format(calendar.getTime());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalendarApp::new);
    }
}
