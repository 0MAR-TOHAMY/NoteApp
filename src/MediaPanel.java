import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class MediaPanel extends JPanel {

    List<String> imagePaths = new ArrayList<>();

    public MediaPanel() {
        setPreferredSize(new Dimension(600, 600));
        setLayout(new BorderLayout());
        setBackground(Color.white);
        updateImages();
    }

    public void addImage(String imagePath) {
        imagePaths.add(imagePath);
        updateImages();
    }

    private void updateImages() {
        removeAll();

        JPanel imagePanel = new JPanel(new GridLayout(imagePaths.size(), 1, 5, 5));
        imagePanel.setBackground(Color.decode("#EBF8FF"));
        for (String imagePath : imagePaths) {
            try {
                Image image = ImageIO.read(new File(imagePath)).getScaledInstance(590, 350, Image.SCALE_SMOOTH);
                imagePanel.add(new JLabel(new ImageIcon(image)));
            } catch (IOException e) {
                System.err.println("Error loading image: " + imagePath);
                e.printStackTrace();
            }
        }

        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        scrollPane.setBorder(null);
        ScrollBarStyler.styleScrollPane(scrollPane);

        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
