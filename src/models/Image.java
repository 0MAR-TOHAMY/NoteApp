package models;

public class Image {
  public String title;
  public Long createdAt;
  public String filePath;

  public Image() {
  }

  public Image(String title, Long createdAt, String filePath) {
    this.title = title;
    this.createdAt = createdAt;
    this.filePath = filePath;
  }

}
