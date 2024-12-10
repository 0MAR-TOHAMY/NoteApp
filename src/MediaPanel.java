import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class MediaPanel extends JPanel {

    public MediaPanel() {
        setPreferredSize(new Dimension(600, 600));
        setLayout(new BorderLayout());
        setBackground(Color.white);

        // List of image file paths
        List<String> imagePaths = new ArrayList<>();
        imagePaths.add("database/UsersData/test.png");
        imagePaths.add("database/UsersData/test.png");
        imagePaths.add("database/UsersData/test.png");

        int gridRows = imagePaths.size();
        JPanel imagePanel = new JPanel(new GridLayout(gridRows, 1, 5, 5));
        imagePanel.setBackground(Color.decode("#EBF8FF"));

        for (String imagePath : imagePaths) {
            try {
                BufferedImage image = ImageIO.read(new File(imagePath));
                Image scaledImage = image.getScaledInstance(590, 350, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                imagePanel.add(imageLabel);
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
    }
}
