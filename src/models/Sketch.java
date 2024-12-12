package models;

public class Sketch {
  public String title;
  public Long createdAt;
  public String filePath;

  public Sketch() {
  }

  public Sketch(String title, Long createdAt, String filePath) {
    this.title = title;
    this.createdAt = createdAt;
    this.filePath = filePath;
  }
}
