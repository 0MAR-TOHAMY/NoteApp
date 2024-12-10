import javax.swing.*;
import java.io.File;

public class FileChooser {

    public static String chooseImageFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image File");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().matches(".*\\.(png|jpg|jpeg|gif|bmp)");
            }

            @Override
            public String getDescription() {
                return "Image Files (PNG, JPG, GIF, BMP)";
            }
        });
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                return selectedFile.toURI().getPath();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
