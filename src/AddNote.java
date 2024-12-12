
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class AddNote extends JPanel {
    JButton submit, back;
    JLabel title, title2, passLabel;
    JTextField titleField;
    JPasswordField password;

    static models.Note sessionNote;

    public AddNote(){
        setPreferredSize(new Dimension(1200, 700));
        setLayout(new BorderLayout());
        setBackground(Color.white);

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(500, 700));
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.white);
        ImageIcon imageIcon = new ImageIcon("assets/illu5.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        leftPanel.add(imageLabel);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(700, 700));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        title = new JLabel("Add New Note");
        title2 = new JLabel("Note Title:");
        passLabel = new JLabel("Password: (optional)");
        password = new JPasswordField();
        titleField = new JTextField();
        submit = new JButton("Create");
        back = new JButton("Back");

        title.setBounds(221, 166, 258, 38);
        title2.setBounds(100, 227, 105, 24);
        titleField.setBounds(100, 264, 500, 50);
        passLabel.setBounds(100, 321, 207, 24);
        password.setBounds(100, 358, 500, 50);
        submit.setBounds(100, 440, 500, 50);
        back.setBounds(324, 510, 51, 24);

        Border paddingBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        Border lineBorder = BorderFactory.createLineBorder(Note.dark, 2);
        Border compoundBorder = new CompoundBorder(lineBorder, paddingBorder);
        password.setBorder(compoundBorder);
        titleField.setBorder(compoundBorder);
        submit.setBorder(null);
        back.setBorder(null);
        title.setHorizontalAlignment(SwingConstants.CENTER);


        submit.setFocusable(false);
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setFocusable(false);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        title.setForeground(Note.dark);
        title2.setForeground(Note.dark);
        passLabel.setForeground(Note.dark);
        titleField.setForeground(Note.dark);
        password.setForeground(Note.dark);
        submit.setBackground(Note.dark);
        submit.setForeground(Color.white);
        back.setBackground(Color.WHITE);
        back.setForeground(Note.dark);

        title.setFont(Fonts.font6);
        title2.setFont(Fonts.font3);
        passLabel.setFont(Fonts.font3);
        titleField.setFont(Fonts.font3);
        password.setFont(Fonts.font4);
        submit.setFont(Fonts.font2);
        back.setFont(Fonts.font3);

        submit.addActionListener(e -> {
            String title = titleField.getText();
            String pass = password.getText();
            if (title.length() > 0){
                if (pass.length() > 0){
                    sessionNote = Note.user.createNote(title, pass);
                }else {
                    sessionNote = Note.user.createNote(title);
                }
                Display displayPage = new Display(title, "");
                Note.cardLayoutPanel.add(displayPage, "displayPage");
                Note.cardLayout.show(Note.cardLayoutPanel, "displayPage");
                System.out.println(sessionNote.getFolderPath());
            }else {
                new Error("Please, Enter a valid note title.");
            }
        });

        rightPanel.add(title);
        rightPanel.add(title2);
        rightPanel.add(titleField);
        rightPanel.add(passLabel);
        rightPanel.add(submit);
        rightPanel.add(password);
        rightPanel.add(back);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
    }
}
