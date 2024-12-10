import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class Change extends JFrame {

    JLabel title, sec;
    JButton close;
    JTextField textField;
    Color border = Color.decode("#5B6E80");

    public Change(){
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

        title = new JLabel("Enter New Title");
        textField = new JTextField();
        close = new JButton("Done");

        ImageIcon secIcon = new ImageIcon("assets/change.jpg");
        Image scaledImage2 = secIcon.getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage2);
        sec = new JLabel(icon);

        sec.setBounds(204, 25, 95, 95);
        title.setBounds(180, 117, 159, 24);
        textField.setBounds(100, 171, 300, 40);
        close.setBounds(200, 240, 100, 30);

        title.setForeground(border);
        textField.setForeground(border);
        close.setForeground(Color.white);
        close.setBackground(Color.decode("#97D5C9"));

        title.setFont(Fonts.font3);
        textField.setFont(Fonts.font3);
        close.setFont(Fonts.font5);

        Border paddingBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        Border lineBorder = BorderFactory.createLineBorder(border, 2);
        Border compoundBorder = new CompoundBorder(lineBorder, paddingBorder);

        close.setBorder(null);
        textField.setBorder(compoundBorder);
        close.setFocusable(false);
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        close.addActionListener(e -> {
            String newTitle = textField.getText().toString();
            if (newTitle != null && !newTitle.trim().isEmpty()) {
                Display.title.setText(newTitle);
            }
            dispose();
        });

        mainPanel.add(sec);
        mainPanel.add(title);
        mainPanel.add(textField);
        mainPanel.add(close);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
