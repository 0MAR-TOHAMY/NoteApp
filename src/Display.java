import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Display extends JPanel {
    JButton outBtn;
    JLabel title, content;

    public Display(String noteTitle, String noteContent){
        setPreferredSize(new Dimension(1200, 700));
        setLayout(new BorderLayout());
        setBackground(Color.decode("#EBF8FF"));

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(1200, 60));
        topPanel.setLayout(null);
        topPanel.setBackground(Note.dark);

        title = new JLabel(noteTitle);
        title.setFont(Fonts.font4);
        title.setForeground(Color.white);
        title.setBounds(20, 12, 500, 33);
        topPanel.add(title);

        ImageIcon outIcon = new ImageIcon("assets/next.png");
        Image scaledImage2 = outIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        outBtn = new JButton(scaledIcon2);
        outBtn.setBounds(1128, 8, 40, 40);
        outBtn.setBackground(Note.dark);
        outBtn.setBorder(null);
        outBtn.setFocusable(false);
        outBtn.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "dashboard"));
        topPanel.add(outBtn);

        content = new JLabel("<html><div style='width:430px;'>" + noteContent + "</div></html>");
        content.setFont(Fonts.font7);
        content.setForeground(Note.dark);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(585, 600));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.white);
        contentPanel.add(content, BorderLayout.NORTH);

        JPanel sketchPanel = new JPanel(new BorderLayout());
        sketchPanel.setPreferredSize(new Dimension(600, 600));
        sketchPanel.setBackground(Color.decode("#EBF8FF"));
        
        MediaPanel mediaPanel = new  MediaPanel();

        JPanel addingPanel = new JPanel(null);
        addingPanel.setPreferredSize(new Dimension(600, 50));
        addingPanel.setBackground(Color.decode("#EBF8FF"));

        JButton addSketchBtn = new JButton("Add Sketch");
        addSketchBtn.setBounds(0, 5, 295, 40);
        addSketchBtn.setBorder(null);
        addSketchBtn.setFocusable(false);
        addSketchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addSketchBtn.setBackground(Note.dark);
        addSketchBtn.setForeground(Color.white);
        addSketchBtn.setFont(Fonts.font2);
        addSketchBtn.addActionListener(e -> {
            Note.cardLayout.show(Note.cardLayoutPanel, "sketch");
        });
        addingPanel.add(addSketchBtn);

        JButton addImageBtn = new JButton("Add Image");
        addImageBtn.setBounds(300, 5, 295, 40);
        addImageBtn.setBorder(null);
        addImageBtn.setFocusable(false);
        addImageBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addImageBtn.setBackground(Note.dark);
        addImageBtn.setForeground(Color.white);
        addImageBtn.setFont(Fonts.font2);
        addImageBtn.addActionListener(e -> {
            String img = FileChooser.chooseImageFile();
            if (img!= null) {
                mediaPanel.addImage(img);
            }
        });
        addingPanel.add(addImageBtn);

        sketchPanel.add(addingPanel, BorderLayout.NORTH);
        sketchPanel.add(mediaPanel, BorderLayout.CENTER);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.WEST);
        this.add(sketchPanel, BorderLayout.EAST);
    }
}