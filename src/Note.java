import models.User;
import src.FileManager;

import javax.swing.*;
import java.awt.*;

public class Note {
  static User user;

  static Color dark = Color.decode("#316DA3");
  static Color sub = Color.decode("#E8505C");

  static String userName;
  String password, confirm;
  static CardLayout cardLayout;
  static JPanel cardLayoutPanel;

  public Note() {
    JFrame frame = new JFrame("Note App");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setSize(1200, 700);
    Image icon = Toolkit.getDefaultToolkit().getImage("./assets/notebook.png");
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

    AddNote addNote = new AddNote();
    cardLayoutPanel.add(loginPage, "loginPage");
    cardLayoutPanel.add(SignPage, "SignPage");
    cardLayoutPanel.add(addNote, "addNote");

    loginPage.signInButton.addActionListener(event -> {
      userName = loginPage.usernameField.getText();
      password = loginPage.passwordField.getText();
      try {
        if (UserAuthentication.authenticateLogin(userName, password)) {
          user = FileManager.users.get(userName);
          user.loadNotes();
          Dashboard dashboard = new Dashboard();
          cardLayoutPanel.add(dashboard, "dashboard");
          dashboard.welcome.setText("Hi, " + user.userName);
          cardLayout.show(cardLayoutPanel, "dashboard");
        } else {
          new Error("Wrong Username or Password");
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    SignPage.signUpButton.addActionListener(event -> {
      userName = SignPage.usernameField.getText();
      password = SignPage.passwordField.getText();
      confirm = SignPage.confirmField.getText();
      try {
        if (UserAuthentication.authenticateRegister(userName, password, confirm) == 1) {
          user = FileManager.users.get(userName);
          Dashboard dashboard = new Dashboard();
          cardLayoutPanel.add(dashboard, "dashboard");
          dashboard.welcome.setText("Hi, " + userName);
          cardLayout.show(cardLayoutPanel, "dashboard");
        } else if (UserAuthentication.authenticateRegister(userName, password, confirm) == -1) {
          new Error("This Username Is Already Exist");
        } else if (UserAuthentication.authenticateRegister(userName, password, confirm) == -3) {
          new Error("Invalid Username or Password");
        } else {
          new Error("Check Your Confirm Password");
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    loginPage.switchScreen.addActionListener(e -> cardLayout.show(cardLayoutPanel, "SignPage"));
    SignPage.switchScreen.addActionListener(e -> cardLayout.show(cardLayoutPanel, "loginPage"));
    addNote.back.addActionListener(e -> cardLayout.show(cardLayoutPanel, "dashboard"));

    frame.add(cardLayoutPanel, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    new FileManager();
    new Note();
  }
}
