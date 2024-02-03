import javax.swing.JOptionPane;;

public class FirstSwingApp {
    public static void main(String[] args) {
        int num1, num2;

        while (true) {
            try {
                num1 = Integer.parseInt(JOptionPane.showInputDialog("Enter First Number:"));
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please, enter an integer number!");
            }
        }

        while (true) {
            try {
                num2 = Integer.parseInt(JOptionPane.showInputDialog("Enter Second Number:"));
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please, enter an integer number!");
            }
        }

        JOptionPane.showMessageDialog(null, "Sum: " + (num1 + num2));
    }
}
