import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class MediaPanel extends JPanel {

    private static int GRID_ROWS = 1;
    public MediaPanel() {
        setPreferredSize(new Dimension(600, 550));
        setLayout(new BorderLayout());
        setBackground(Color.white);

        List<String> imagePaths = new ArrayList<>();
        imagePaths.add("database/UsersData/test.png");
        imagePaths.add("database/UsersData/test.png");
        imagePaths.add("database/UsersData/test.png");

        GRID_ROWS = imagePaths.toArray().length;
        JPanel imagePanel = new JPanel(new GridLayout(GRID_ROWS, 1));

        for (String imagePath : imagePaths) {
            try {
                BufferedImage image = ImageIO.read(new File(imagePath));
                Image scaledImage = image.getScaledInstance(590, 350, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                imagePanel.add(imageLabel);
            } catch (IOException e) {
                System.err.println("Error loading image: " + imagePath);
                e.printStackTrace();
            }
        }

        
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        
        ScrollBarStyler.styleScrollPane(scrollPane);

        add(scrollPane, BorderLayout.CENTER);
    }
}