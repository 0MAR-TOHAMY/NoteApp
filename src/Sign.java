import javax.swing.*;
import java.awt.*;

public class Sign extends JPanel {
  JButton signUpButton, switchScreen;
  JLabel titleLabel, subTitleLabel, usernameLabel, passwordLabel, confirmLabel;
  JTextField usernameField;
  JPasswordField passwordField, confirmField;

  public Sign() {
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
    subTitleLabel = new JLabel("Hello, There");
    usernameLabel = new JLabel("UserName:");
    passwordLabel = new JLabel("Password:");
    confirmLabel = new JLabel("Confirm Password:");
    usernameField = new JTextField();
    passwordField = new JPasswordField();
    confirmField = new JPasswordField();
    signUpButton = new JButton("SIGN UP");
    switchScreen = new JButton("Already have an account ? Log In");

    titleLabel.setBounds(208, 47, 313, 57);
    subTitleLabel.setBounds(290, 121, 149, 28);
    usernameLabel.setBounds(115, 187, 114, 24);
    usernameField.setBounds(115, 217, 500, 50);
    passwordLabel.setBounds(115, 278, 113, 24);
    passwordField.setBounds(115, 305, 500, 50);
    confirmLabel.setBounds(115, 366, 190, 24);
    confirmField.setBounds(115, 396, 500, 50);
    signUpButton.setBounds(115, 477, 500, 50);
    switchScreen.setBounds(115, 550, 500, 50);

    usernameField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    passwordField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    confirmField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    signUpButton.setBorder(null);
    switchScreen.setBorder(null);

    signUpButton.setFocusable(false);
    signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    switchScreen.setFocusable(false);
    switchScreen.setFocusPainted(false);
    switchScreen.setContentAreaFilled(false);
    switchScreen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    titleLabel.setForeground(Note.dark);
    subTitleLabel.setForeground(Note.sub);
    usernameLabel.setForeground(Note.dark);
    passwordLabel.setForeground(Note.dark);
    usernameField.setForeground(Note.dark);
    confirmField.setForeground(Note.dark);
    confirmLabel.setForeground(Note.dark);
    passwordField.setForeground(Note.dark);
    signUpButton.setBackground(Note.dark);
    signUpButton.setForeground(Color.white);
    switchScreen.setBackground(Color.decode("#EBF8FF"));
    switchScreen.setForeground(Note.dark);

    titleLabel.setFont(Fonts.font1);
    subTitleLabel.setFont(Fonts.font2);
    usernameLabel.setFont(Fonts.font3);
    passwordLabel.setFont(Fonts.font3);
    usernameField.setFont(Fonts.font3);
    passwordField.setFont(Fonts.font3);
    confirmField.setFont(Fonts.font3);
    confirmLabel.setFont(Fonts.font3);
    signUpButton.setFont(Fonts.font2);
    switchScreen.setFont(Fonts.font3);

    rightPanel.add(titleLabel);
    rightPanel.add(signUpButton);
    rightPanel.add(passwordLabel);
    rightPanel.add(passwordField);
    rightPanel.add(usernameLabel);
    rightPanel.add(usernameField);
    rightPanel.add(subTitleLabel);
    rightPanel.add(confirmField);
    rightPanel.add(confirmLabel);
    rightPanel.add(switchScreen);

    this.add(leftPanel, BorderLayout.WEST);
    this.add(rightPanel, BorderLayout.EAST);
  }
}
