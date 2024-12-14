import models.SecureNote;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class Item extends JPanel {
  JLabel Title, secure;
  JButton showBtn, removeBtn;

  public Item(String title, boolean pass, models.Note note) {
    setLayout(new BorderLayout());
    setBackground(Note.dark);

    Title = new JLabel(title);
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
    showBtn.addActionListener(e -> {
      try {
        models.Note newNote = note;
        if (note.isSecure()) {
          newNote = (SecureNote) note;
        }
        models.Note itemNote = Note.user.getNote(newNote);

        if (itemNote.isSecure()) {
          Security secPage = new Security((SecureNote) note);
          Note.cardLayoutPanel.add(secPage, "secPage");
          Note.cardLayout.show(Note.cardLayoutPanel, "secPage");
        } else {
          Display displayPage = new Display(itemNote);
          Note.cardLayoutPanel.add(displayPage, "displayPage");
          Note.cardLayout.show(Note.cardLayoutPanel, "displayPage");
        }
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    btns.add(showBtn, BorderLayout.EAST);

    ImageIcon secIcon = new ImageIcon("./assets/lock.png");
    Image scaledImage3 = secIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
    secure = new JLabel(scaledIcon3);
    secure.setBorder(new EmptyBorder(0, 10, 5, 10));
    secure.setVisible(pass);
    btns.add(secure, BorderLayout.WEST);

    ImageIcon removeIcon = new ImageIcon("./assets/remove.png");
    Image scaledImage2 = removeIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
    removeBtn = new JButton(scaledIcon2);
    removeBtn.setBackground(Note.dark);
    removeBtn.setBorder(null);
    removeBtn.setFocusable(false);
    removeBtn.setBorder(new EmptyBorder(0, 10, 0, 10));
    removeBtn.addActionListener(e -> {
        try {
          int response = JOptionPane.showConfirmDialog(
                  Note.cardLayoutPanel,
                  "Are you sure you want to remove this Note?",
                  "Confirm Deletion",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.WARNING_MESSAGE
          );
          if (response == JOptionPane.YES_OPTION) {
            Note.user.removeNote(note);
            Note.user.loadNotes();
            Dashboard dashboard = new Dashboard();
            dashboard.welcome.setText("Hi, " + Note.user.userName);
            Note.cardLayoutPanel.add(dashboard, "dashboard");
            Note.cardLayout.show(Note.cardLayoutPanel, "dashboard");
          }
        } catch (IOException ex) {ex.getMessage();}
    });

    btns.add(removeBtn, BorderLayout.CENTER);
    add(btns, BorderLayout.EAST);
  }
}
