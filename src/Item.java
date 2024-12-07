import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Item extends JPanel {
    JLabel Title, secure;
    JButton showBtn;
    JButton editBtn;

    public Item(String title, int id, boolean pass){
        setLayout(new BorderLayout());
        setBackground(Note.dark);

        Title = new JLabel(title + id);
        Title.setBorder(new EmptyBorder(0, 20, 0, 0));
        Title.setFont(Fonts.font3);
        Title.setForeground(Color.white);
        add(Title, BorderLayout.CENTER);

        JPanel btns = new JPanel(new BorderLayout());
        btns.setBackground(Note.dark);

        ImageIcon showIcon = new ImageIcon("/Users/Admin/IdeaProjects/NoteApp/assets/show.png");
        Image scaledImage1 = showIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        showBtn = new JButton(scaledIcon1);
        showBtn.setBackground(Note.dark);
        showBtn.setBorder(null);
        showBtn.setFocusable(false);
        showBtn.setBorder(new EmptyBorder(0, 10, 0, 10));
        showBtn.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "secPage"));
        btns.add(showBtn, BorderLayout.EAST);

        ImageIcon editIcon = new ImageIcon("/Users/Admin/IdeaProjects/NoteApp/assets/edit.png");
        Image scaledImage2 = editIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        editBtn = new JButton(scaledIcon2);
        editBtn.setBackground(Note.dark);
        editBtn.setBorder(null);
        editBtn.setFocusable(false);
        editBtn.setBorder(new EmptyBorder(0, 10, 0, 10));
        btns.add(editBtn, BorderLayout.CENTER);

        ImageIcon secIcon = new ImageIcon("/Users/Admin/IdeaProjects/NoteApp/assets/lock.png");
        Image scaledImage3 = secIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
        secure = new JLabel(scaledIcon3);
        secure.setBorder(new EmptyBorder(0, 10, 5, 10));
        secure.setVisible(pass);
        btns.add(secure, BorderLayout.WEST);

        add(btns, BorderLayout.EAST);
    }
}
