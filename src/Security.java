import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class Security extends JPanel {
    JButton submit, back;
    JLabel title, title2;
    JPasswordField password;

    public Security(){
        setPreferredSize(new Dimension(1200, 700));
        setLayout(new BorderLayout());
        setBackground(Color.white);

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(500, 700));
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.white);
        ImageIcon imageIcon = new ImageIcon("assets/security.jpg");
        Image scaledImage2 = imageIcon.getImage().getScaledInstance(500, 545, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage2);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        leftPanel.add(imageLabel);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(700, 700));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        title = new JLabel("Enter your note");
        title2 = new JLabel("security key");
        password = new JPasswordField();
        submit = new JButton("Submit");
        back = new JButton("Back");

        title.setBounds(221, 205, 258, 38);
        title2.setBounds(246, 243, 208, 38);
        password.setBounds(100, 320, 500, 50);
        submit.setBounds(100, 409, 500, 50);
        back.setBounds(324, 486, 51, 24);

        Border paddingBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        Border lineBorder = BorderFactory.createLineBorder(Note.dark, 2);
        Border compoundBorder = new CompoundBorder(lineBorder, paddingBorder);
        password.setBorder(compoundBorder);
        submit.setBorder(null);
        back.setBorder(null);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setHorizontalAlignment(SwingConstants.CENTER);


        submit.setFocusable(false);
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setFocusable(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        title.setForeground(Note.dark);
        title2.setForeground(Note.dark);
        password.setForeground(Note.dark);
        submit.setBackground(Note.dark);
        submit.setForeground(Color.white);
        back.setBackground(Color.WHITE);
        back.setForeground(Note.dark);

        title.setFont(Fonts.font6);
        title2.setFont(Fonts.font6);
        password.setFont(Fonts.font4);
        submit.setFont(Fonts.font2);
        back.setFont(Fonts.font3);

        submit.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "displayPage"));

        rightPanel.add(title);
        rightPanel.add(title2);
        rightPanel.add(submit);
        rightPanel.add(password);
        rightPanel.add(back);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
    }
}
