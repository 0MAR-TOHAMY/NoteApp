import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {
  JButton signInButton, switchScreen;
  JLabel titleLabel, subTitleLabel, usernameLabel, passwordLabel;
  JTextField usernameField;
  JPasswordField passwordField;

  public Login() {
    setPreferredSize(new Dimension(1200, 700));
    setLayout(new BorderLayout());
    setBackground(Color.white);

    JPanel leftPanel = new JPanel();
    leftPanel.setPreferredSize(new Dimension(500, 700));
    leftPanel.setLayout(new BorderLayout());
    leftPanel.setBackground(Color.white);
    ImageIcon imageIcon = new ImageIcon("./assets/illu5.jpg");
    JLabel imageLabel = new JLabel(imageIcon);
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageLabel.setVerticalAlignment(SwingConstants.CENTER);
    leftPanel.add(imageLabel);

    JPanel rightPanel = new JPanel();
    rightPanel.setPreferredSize(new Dimension(700, 700));
    rightPanel.setBackground(Color.decode("#EBF8FF"));
    rightPanel.setLayout(null);

    titleLabel = new JLabel("My Note App");
    subTitleLabel = new JLabel("Welcome Back");
    usernameLabel = new JLabel("UserName:");
    passwordLabel = new JLabel("Password:");
    usernameField = new JTextField();
    passwordField = new JPasswordField();
    signInButton = new JButton("LOG IN");
    switchScreen = new JButton("Don't have an account ? Sign Up");

    titleLabel.setBounds(208, 67, 313, 57);
    subTitleLabel.setBounds(270, 141, 190, 28);
    usernameLabel.setBounds(115, 227, 114, 24);
    usernameField.setBounds(115, 267, 500, 50);
    passwordLabel.setBounds(115, 328, 113, 24);
    passwordField.setBounds(115, 365, 500, 50);
    signInButton.setBounds(115, 451, 500, 50);
    switchScreen.setBounds(115, 530, 500, 50);

    usernameField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    passwordField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    signInButton.setBorder(null);
    switchScreen.setBorder(null);

    signInButton.setFocusable(false);
    signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    switchScreen.setFocusable(false);
    switchScreen.setFocusPainted(false);
    switchScreen.setContentAreaFilled(false);
    switchScreen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    titleLabel.setForeground(Note.dark);
    subTitleLabel.setForeground(Note.sub);
    usernameLabel.setForeground(Note.dark);
    passwordLabel.setForeground(Note.dark);
    usernameField.setForeground(Note.dark);
    passwordField.setForeground(Note.dark);
    signInButton.setBackground(Note.dark);
    signInButton.setForeground(Color.white);
    switchScreen.setBackground(Color.decode("#EBF8FF"));
    switchScreen.setForeground(Note.dark);

    titleLabel.setFont(Fonts.font1);
    subTitleLabel.setFont(Fonts.font2);
    usernameLabel.setFont(Fonts.font3);
    passwordLabel.setFont(Fonts.font3);
    usernameField.setFont(Fonts.font3);
    passwordField.setFont(Fonts.font3);
    signInButton.setFont(Fonts.font2);
    switchScreen.setFont(Fonts.font3);

    rightPanel.add(titleLabel);
    rightPanel.add(signInButton);
    rightPanel.add(passwordLabel);
    rightPanel.add(passwordField);
    rightPanel.add(usernameLabel);
    rightPanel.add(usernameField);
    rightPanel.add(subTitleLabel);
    rightPanel.add(switchScreen);

    this.add(leftPanel, BorderLayout.WEST);
    this.add(rightPanel, BorderLayout.EAST);
  }
}
