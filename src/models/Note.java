package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Note {
  private String title;
  private String content;
  private String folderPath;
  private List<Image> images = new ArrayList<>();
  public Sketch sketch = new Sketch();

  public Note(String title, String folderPath) {
    this.title = title;
    this.folderPath = folderPath;
  }

  public Note(String title, String content, String folderPath) {
    this.title = title;
    this.content = content;
    this.folderPath = folderPath;
  }

  public Note(String title, String content, String folderPath, ArrayList<Image> images) {
    this.title = title;
    this.content = content;
    this.images = images;
    this.folderPath = folderPath;
  }

  public Note(String title, String content, String folderPath, Sketch sketch) {
    this.title = title;
    this.content = content;
    this.sketch = sketch;
    this.folderPath = folderPath;
  }

  public Note(String title, String content, String folderPath, ArrayList<Image> images, Sketch sketch) {
    this.title = title;
    this.content = content;
    this.images = images;
    this.sketch = sketch;
    this.folderPath = folderPath;
  }

  public boolean isSecure() {
    return false;
  }

  public String getFolderPath() {
    return folderPath;
  }

  public void addImage(String imageOriginalPath) throws IOException {
    System.out.println(imageOriginalPath);
    String imageTitle = Paths.get(imageOriginalPath.substring(1)).getFileName().toString();
    Long createdAt = System.currentTimeMillis();
    String imagePath = this.folderPath + "/images/" + imageTitle;
    Image newImage = new Image(imageTitle, createdAt, imagePath);
    // storing the image in the images folder
    src.FileManager.addImageToNoteFolder(imageTitle, imageOriginalPath.substring(1), this.folderPath);
    // adding the image to the images array so it can be displayed

    this.images.add(newImage);
  }

  public String getImageTitle(String imagePath) {
    String[] folders = imagePath.split("/");
    return folders[folders.length - 1];
  }

  public Image removeImage(int index) throws IOException {
    Image removedImage = images.remove(index);
    // this will return a boolean you can use it if you want
    src.FileManager.removeImageFromNoteFolder(folderPath + "/" + removedImage.title);
    return removedImage;
  }

  public File addSketch() {
    String title = "sketch.png";
    String sketchPath = this.folderPath + "/" + title;
    Long createdAt = System.currentTimeMillis();
    this.sketch = new Sketch(title, createdAt, sketchPath);
    return new File(sketchPath);
  }


  public List<Image> getImages() {
    return images;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }
}
