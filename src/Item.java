import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Item extends JPanel {
  JLabel Title, secure;
  JButton showBtn;

  public Item(String title, int id, boolean pass) {
    setLayout(new BorderLayout());
    setBackground(Note.dark);

    Title = new JLabel(title + id);
    Title.setBorder(new EmptyBorder(0, 20, 0, 0));
    Title.setFont(Fonts.font3);
    Title.setForeground(Color.white);
    add(Title, BorderLayout.CENTER);

    JPanel btns = new JPanel(new BorderLayout());
    btns.setBackground(Note.dark);

    ImageIcon showIcon = new ImageIcon("./assets/show.png");
    Image scaledImage1 = showIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
    showBtn = new JButton(scaledIcon1);
    showBtn.setBackground(Note.dark);
    showBtn.setBorder(null);
    showBtn.setFocusable(false);
    showBtn.setBorder(new EmptyBorder(0, 10, 0, 10));
    showBtn.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "secPage"));
    btns.add(showBtn, BorderLayout.EAST);

    ImageIcon secIcon = new ImageIcon("./assets/lock.png");
    Image scaledImage3 = secIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
    secure = new JLabel(scaledIcon3);
    secure.setBorder(new EmptyBorder(0, 10, 5, 10));
    secure.setVisible(pass);
    btns.add(secure, BorderLayout.WEST);

    add(btns, BorderLayout.EAST);
  }
}
