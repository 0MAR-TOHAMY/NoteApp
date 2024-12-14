import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
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
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                imagePanel.add(imageLabel);

                // Add a mouse listener to handle click events
                imageLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Show confirmation dialog
                        int response = JOptionPane.showConfirmDialog(
                                MediaPanel.this,
                                "Are you sure you want to remove this image?",
                                "Confirm Deletion",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE
                        );

                        if (response == JOptionPane.YES_OPTION) {
                            try {note.removeImage(imagePath);}
                            catch (IOException ex) {throw new RuntimeException(ex);}
                            updateImages(note);
                        }
                    }
                });
            } catch (IOException e) {
                System.err.println("Error loading image: " + imagePath.filePath);
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
