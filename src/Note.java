import javax.swing.*;
import java.awt.*;

public class Note {

    static Color dark = Color.decode("#316DA3");
    static Color sub = Color.decode("#E8505C");

    static String userName;
    String password, confirm;
    static CardLayout cardLayout;
    static JPanel cardLayoutPanel;

    String longText = "This is a very long text that should wrap when it reaches 900 pixels in width. " +
            "It will automatically wrap to the next line to fit within the specified width."
            + "This is a very long text that should wrap when it reaches 900 pixels in width. " +
            "It will automatically wrap to the next line to fit within the specified width."
            + "This is a very long text that should wrap when it reaches 900 pixels in width. ";

    public Note(){
        JFrame frame = new JFrame("Note App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1200, 700);
        Image icon = Toolkit.getDefaultToolkit().getImage("/Users/Admin/IdeaProjects/NoteApp/assets/notebook.png");
        frame.setIconImage(icon);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        cardLayoutPanel = new JPanel();
        cardLayoutPanel.setLayout(new CardLayout());
        cardLayoutPanel.setPreferredSize(new Dimension(1200, 700));
        cardLayout = (CardLayout) cardLayoutPanel.getLayout();

        Login loginPage = new Login();
        Sign SignPage = new Sign();
        Dashboard dashboard = new Dashboard();
        Security secPage = new Security();
        Sketch sketch = new Sketch();
        Display displayPage = new Display("myNote", longText);
        AddNote addNote = new AddNote();
        cardLayoutPanel.add(loginPage, "loginPage");
        cardLayoutPanel.add(SignPage, "SignPage");
        cardLayoutPanel.add(dashboard, "dashboard");
        cardLayoutPanel.add(secPage, "secPage");
        cardLayoutPanel.add(displayPage, "displayPage");
        cardLayoutPanel.add(addNote, "addNote");
        cardLayoutPanel.add(sketch, "sketch");

        loginPage.signInButton.addActionListener(event -> {
            userName = loginPage.usernameField.getText();
            password = loginPage.passwordField.getText();
            dashboard.welcome.setText("Hi, " + userName);
            try {
                if(UserAuthentication.authenticateLogin(userName, password)){
                    CardLayout cardLayout = (CardLayout) cardLayoutPanel.getLayout();
                    cardLayout.show(cardLayoutPanel, "dashboard");
                }
                else {new Error("Wrong Username or Password");}
            } catch (Exception e) {throw new RuntimeException(e);}
        });

        SignPage.signUpButton.addActionListener(event -> {
            userName = SignPage.usernameField.getText();
            password = SignPage.passwordField.getText();
            confirm = SignPage.confirmField.getText();
            dashboard.welcome.setText("Hi, " + userName);
            try {
                if(UserAuthentication.authenticateRegister(userName, password, confirm) == 1){
                    cardLayout.show(cardLayoutPanel, "dashboard");
                }
                else if(UserAuthentication.authenticateRegister(userName, password, confirm) == -1) {
                    new Error("This Username Is Already Exist");
                }else if(UserAuthentication.authenticateRegister(userName, password, confirm) == -3) {
                    new Error("Invalid Username or Password");
                }else {
                    new Error("Check Your Confirm Password");
                }
            } catch (Exception e) {throw new RuntimeException(e);}
        });

        dashboard.outBtn.addActionListener(e -> cardLayout.show(cardLayoutPanel, "loginPage"));
        loginPage.switchScreen.addActionListener(e -> cardLayout.show(cardLayoutPanel, "SignPage"));
        SignPage.switchScreen.addActionListener(e -> cardLayout.show(cardLayoutPanel, "loginPage"));
        secPage.back.addActionListener(e -> cardLayout.show(cardLayoutPanel, "dashboard"));
        addNote.back.addActionListener(e -> cardLayout.show(cardLayoutPanel, "dashboard"));
        dashboard.addBtn.addActionListener(e -> cardLayout.show(cardLayoutPanel, "addNote"));

        frame.add(cardLayoutPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Note();
    }
}