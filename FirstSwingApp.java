import javax.swing.JOptionPane;;

public class FirstSwingApp {
    public static void main(String[] args) {
        int num1 = Integer.parseInt(JOptionPane.showInputDialog("Enter First Number:"));
        int num2 = Integer.parseInt(JOptionPane.showInputDialog("Enter Second Number:"));

        JOptionPane.showMessageDialog(null, "Sum: " + (num1 + num2));
    }
}
