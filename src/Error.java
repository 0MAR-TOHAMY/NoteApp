import javax.swing.*;
import java.awt.*;

public class Error extends JFrame {

    JLabel title ,subTitle, sec;
    JButton close;
    Color border = Color.decode("#1B2739");

    public Error(String message){
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(Color.white);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(500, 300));
        mainPanel.setBackground(Color.white);
        mainPanel.setBorder(BorderFactory.createLineBorder(border, 2));

        title = new JLabel("Access Denied!");
        subTitle = new JLabel(message);
        close = new JButton("Close");

        ImageIcon secIcon = new ImageIcon("assets/error.png");
        Image scaledImage2 = secIcon.getImage().getScaledInstance(150, 140, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage2);
        sec = new JLabel(icon);

        sec.setBounds(184, 33, 132, 120);
        title.setBounds(171, 160, 159, 24);
        subTitle.setBounds(91, 194, 318, 24);
        close.setBounds(200, 240, 100, 30);

        title.setForeground(border);
        subTitle.setForeground(border);
        close.setForeground(Color.white);
        close.setBackground(Color.decode("#F33342"));

        title.setFont(Fonts.font3);
        subTitle.setFont(Fonts.font3);
        close.setFont(Fonts.font5);

        close.setBorder(null);
        close.setFocusable(false);
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        close.addActionListener(e -> dispose());

        mainPanel.add(sec);
        mainPanel.add(title);
        mainPanel.add(subTitle);
        mainPanel.add(close);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
