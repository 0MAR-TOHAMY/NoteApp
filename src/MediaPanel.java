import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class MediaPanel extends JPanel {

    public MediaPanel(models.Note note) {
        setPreferredSize(new Dimension(590, 600));
        setLayout(new BorderLayout());
        setBackground(Color.white);
        updateImages(note);
    }

    void updateImages(models.Note note) {
        removeAll();

        JPanel imagePanel = new JPanel(new GridLayout(note.getImages().size(), 1, 5, 5));
        imagePanel.setBackground(Color.decode("#EBF8FF"));
        for (models.Image imagePath : note.getImages()) {
            try {
                Image image = ImageIO.read(new File(imagePath.filePath)).getScaledInstance(580, 350, Image.SCALE_SMOOTH);
                imagePanel.add(new JLabel(new ImageIcon(image)));
            } catch (IOException e) {
                System.err.println("Error loading image: " + imagePath);
                e.printStackTrace();
            }
        }

        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(590, 600));
        scrollPane.setBorder(new EmptyBorder(10, 0, 10, 5));
        scrollPane.setBackground(Color.decode("#EBF8FF"));
        ScrollBarStyler.styleScrollPane(scrollPane);

        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
