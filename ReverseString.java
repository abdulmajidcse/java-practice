public class ReverseString {
    public static void main(String[] args) {
        String name = "gemini";
        String reverseName = "";

        for (int i = 0; i < name.length(); i++) {
            reverseName = name.charAt(i) + reverseName;
        }

        System.out.println("Origianl name: " + name);
        System.out.println("Reverse name: " + reverseName);
    }
}