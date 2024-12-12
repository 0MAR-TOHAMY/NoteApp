import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class Display extends JPanel {
    private JButton outBtn, clearTextBtn, changeTitleBtn;
    static JLabel title;
    private JTextArea textArea;

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
        outBtn.addActionListener(e -> {
            try {
                AddNote.sessionNote.setTitle(title.getText());
                Note.user.saveNote(AddNote.sessionNote, textArea.getText());
                Note.user.loadNotes();
                Dashboard dashboard = new Dashboard();
                dashboard.welcome.setText("Hi, " + Note.user.userName);
                Note.cardLayoutPanel.add(dashboard, "dashboard");
                Note.cardLayout.show(Note.cardLayoutPanel, "dashboard");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        topPanel.add(outBtn);


        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(590, 600));
        contentPanel.setBackground(Color.decode("#EBF8FF"));


        JPanel buttonsPanel = new JPanel(null);
        buttonsPanel.setPreferredSize(new Dimension(600, 50));
        buttonsPanel.setBackground(Color.decode("#EBF8FF"));

        clearTextBtn = new JButton("Clear Text");
        clearTextBtn.setBounds(5, 5, 285, 40);
        clearTextBtn.setBorder(null);
        clearTextBtn.setFocusable(false);
        clearTextBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearTextBtn.setBackground(Note.dark);
        clearTextBtn.setForeground(Color.white);
        clearTextBtn.setFont(Fonts.font2);
        clearTextBtn.addActionListener(e -> textArea.setText(""));
        buttonsPanel.add(clearTextBtn);

        changeTitleBtn = new JButton("Change Title");
        changeTitleBtn.setBounds(295, 5, 285, 40);
        changeTitleBtn.setBorder(null);
        changeTitleBtn.setFocusable(false);
        changeTitleBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changeTitleBtn.setBackground(Note.dark);
        changeTitleBtn.setForeground(Color.white);
        changeTitleBtn.setFont(Fonts.font2);
        changeTitleBtn.addActionListener(e -> new Change());
        buttonsPanel.add(changeTitleBtn);

        textArea = new JTextArea(noteContent);
        textArea.setFont(Fonts.font3);
        textArea.setForeground(Note.dark);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textScrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        textScrollPane.setBackground(Color.decode("#EBF8FF"));
        ScrollBarStyler.styleScrollPane(textScrollPane);

        contentPanel.add(buttonsPanel, BorderLayout.NORTH);
        contentPanel.add(textScrollPane, BorderLayout.CENTER);

        JPanel sketchPanel = new JPanel(new BorderLayout());
        sketchPanel.setPreferredSize(new Dimension(590, 600));
        sketchPanel.setBackground(Color.decode("#EBF8FF"));
        
        MediaPanel mediaPanel = new  MediaPanel();

        JPanel addingPanel = new JPanel(null);
        addingPanel.setPreferredSize(new Dimension(590, 50));
        addingPanel.setBackground(Color.decode("#EBF8FF"));

        JButton addSketchBtn = new JButton("Add Sketch");
        addSketchBtn.setBounds(0, 5, 290, 40);
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
        addImageBtn.setBounds(295, 5, 290, 40);
        addImageBtn.setBorder(null);
        addImageBtn.setFocusable(false);
        addImageBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addImageBtn.setBackground(Note.dark);
        addImageBtn.setForeground(Color.white);
        addImageBtn.setFont(Fonts.font2);
        addImageBtn.addActionListener(e -> {
            String img = FileChooser.chooseImageFile();
            if (img!= null) {
                try {
                    mediaPanel.addImage(img);
                    AddNote.sessionNote.addImage(img);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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