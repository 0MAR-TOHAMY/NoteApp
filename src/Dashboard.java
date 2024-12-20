import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Dashboard extends JPanel {
  JButton addBtn, outBtn;
  JLabel welcome;

  public Dashboard() {
    setPreferredSize(new Dimension(1200, 700));
    setLayout(new BorderLayout());
    setBackground(Color.white);

    JPanel topPanel = new JPanel();
    topPanel.setPreferredSize(new Dimension(1200, 60));
    topPanel.setLayout(null);
    topPanel.setBackground(Note.dark);

    welcome = new JLabel("Hi, UnKnown");
    welcome.setFont(Fonts.font4);
    welcome.setForeground(Color.white);
    welcome.setBounds(10, 12, 500, 33);
    topPanel.add(welcome);

    ImageIcon addIcon = new ImageIcon("./assets/add.png");
    Image scaledImage1 = addIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
    addBtn = new JButton(scaledIcon1);
    addBtn.setBounds(1078, 8, 40, 40);
    addBtn.setBackground(Note.dark);
    addBtn.setBorder(null);
    addBtn.setFocusable(false);
    addBtn.addActionListener(e -> {
      AddNote addNote = new AddNote();
      Note.cardLayoutPanel.add(addNote, "addNote");
      Note.cardLayout.show(Note.cardLayoutPanel, "addNote");});
    topPanel.add(addBtn);

    ImageIcon outIcon = new ImageIcon("./assets/out.png");
    Image scaledImage2 = outIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
    outBtn = new JButton(scaledIcon2);
    outBtn.setBounds(1128, 8, 40, 40);
    outBtn.setBackground(Note.dark);
    outBtn.setBorder(null);
    outBtn.setFocusable(false);
    outBtn.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "loginPage"));
    topPanel.add(outBtn);

    JPanel contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(1200, 640));
    GridLayout gridLayout = new GridLayout(9, 6, 20, 20);
    contentPanel.setLayout(gridLayout);
    contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
    contentPanel.setBackground(Color.decode("#EBF8FF"));

    for (models.Note note : Note.user.notes){
      contentPanel.add(new Item(note.getTitle(), note.isSecure(), note));
    }

    this.add(topPanel, BorderLayout.NORTH);
    this.add(contentPanel, BorderLayout.CENTER);
  }
}
