package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// You shouldn't use this class for creating users
// instead use FileManager.createUser()

public class User {
  public String userName;
  public String password;
  public String folderPath;
  public List<Note> notes = new ArrayList<>();

  public User() {
  }

  public User(String name, String password, String folderPath) {
    this.userName = name;
    this.password = password;
    this.folderPath = folderPath;
  }

  public void loadNotes() {
    notes = src.FileManager.loadAllUserNotes(folderPath);
  }

  public Note createNote(String title) {
    UUID uniqueKey = UUID.randomUUID();
    String noteFolderPath = this.folderPath + "/" + uniqueKey;
    return new Note(title, noteFolderPath);
  }

  public SecureNote createNote(String title, String password) {
    UUID uniqueKey = UUID.randomUUID();
    String noteFolderPath = this.folderPath + "/" + uniqueKey;
    return new SecureNote(title, password, noteFolderPath);
  }

  public void saveNote(Note note, String content) throws IOException {
    note.setContent(content);
    src.FileManager.saveNote(note);
  }

  public void saveNote(SecureNote note, String content) throws IOException {
    note.setContent(content);
    src.FileManager.saveSecureNote(note);
  }

  public Note getNote(Note note) throws IOException {
    return src.FileManager.getNote(note);
  }

  public SecureNote getNote(SecureNote note) throws IOException {
    return src.FileManager.getSecureNote(note);
  }
}
