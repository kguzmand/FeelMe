package Logic;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        MainGUI gui = new MainGUI();
        gui.setVisible(true);
    });
    }
}
