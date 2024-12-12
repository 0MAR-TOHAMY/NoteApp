package models;

import java.util.ArrayList;

public class SecureNote extends Note {
  private String password;

  public SecureNote(String title, String password, String folderPath) {
    super(title, folderPath);
    this.password = password;
  }

  public SecureNote(String title, String content, String password, String folderPath) {
    super(title, content, folderPath);
    this.password = password;
  }

  public SecureNote(String title, String content, String folderPath, ArrayList<Image> images, String password) {
    super(title, content, folderPath, images);
    this.password = password;
  }

  public SecureNote(String title, String content, String folderPath, Sketch sketch, String password) {
    super(title, content, folderPath, sketch);
    this.password = password;
  }

  public SecureNote(String title, String content, String folderPath, ArrayList<Image> images, Sketch sketch, String password) {
    super(title, content, folderPath, images, sketch);
    this.password = password;
  }

  @Override
  public boolean isSecure() {
    return true;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
