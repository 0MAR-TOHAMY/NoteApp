package src;

import models.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FileManager {
  public static HashMap<String, User> users = new HashMap<>();

  public FileManager() {
    loadAllUsers();
  }

  public static void loadAllUsers() {
    try {
      // Read the content of the json file as a String
      String filePath = "./database/users.json";
      String content = new String(Files.readAllBytes(Paths.get(filePath)));

      // Parse the String into a JSONObject
      JSONObject jsonObject = new JSONObject(content);
      JSONArray usersArray = jsonObject.getJSONArray("users");

      // Storing users into a hash map
      for (int i = 0; i < usersArray.length(); i++) {
        String userName = usersArray.getJSONObject(i).getString("userName");
        String password = usersArray.getJSONObject(i).getString("password");
        String folderPath = usersArray.getJSONObject(i).getString("folderPath");

        users.put(userName, new User(userName, password, folderPath));
      }

    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    } catch (org.json.JSONException e) {
      System.err.println("Invalid JSON format: " + e.getMessage());
    }
  }


  // the following 2 methods for creating the user
  public User createUser(String userName, String password) {
    Path folderPath = Paths.get("./database/users/" + userName);
    try {
      Files.createDirectories(folderPath); // making a new folder for the user
      storeUserInJSONFile(userName, password, folderPath.toString()); // store in the json file
      users.put(userName, new User(userName, password, folderPath.toString())); //store the user in the hashmap
      return new User(userName, password, folderPath.toString());
    } catch (IOException e) {
      System.err.println("Failed to create the folder: " + e.getMessage());
    }
    return null;
  }

  private JSONObject createUserJsonObject(String userName, String password, String folderPath) {
    return new JSONObject()
            .put("userName", userName)
            .put("password", password);
  }

  private void storeUserInJSONFile(String userName, String password, String folderPath) {
    String filePath = "./database/users.json";
    try {
      String content = new String(Files.readAllBytes(Paths.get(filePath)));

      JSONObject jsonObject;
      if (content.trim().isEmpty()) {
        jsonObject = new JSONObject(); // Empty file, create new JSONObject
      } else {
        jsonObject = new JSONObject(content); // Parse the existing content
      }

      JSONArray usersArray = jsonObject.optJSONArray("users");
      if (usersArray == null) {
        usersArray = new JSONArray(); // Initialize an empty array if "users" doesn't exist
        jsonObject.put("users", usersArray); // Add the "users" array to the JSON object
      }

      usersArray.put(createUserJsonObject(userName, password, folderPath));

      Files.write(Paths.get(filePath), jsonObject.toString(4).getBytes());
      System.out.println("User added successfully!");

    } catch (IOException e) {
      System.err.println("Error reading or writing the file: " + e.getMessage());
    } catch (org.json.JSONException e) {
      System.err.println("Invalid JSON format: " + e.getMessage());
    }
  }


  // this will be used in the user class to make a note
  public static void saveNote(Note note) throws IOException {
    String notePath = note.getFolderPath();
    Path noteFolderPath = Paths.get(notePath);
    Path noteTextFilePath = Paths.get(notePath + "/content.txt");
    // create the note folder
    Files.createDirectories(noteFolderPath);
    // create the note json file
    createNoteJsonFile(notePath, note);

    // create the text file
    Files.createFile(noteTextFilePath);
    // save the note content
    saveNoteContentToTxtFile(notePath + "/content.txt", note.getContent());

  }

  public static void saveSecureNote(SecureNote note) throws IOException {
    saveNote(note);
    Path jsonFilePath = Paths.get(note.getFolderPath() + "/note.json");
    Files.createFile(jsonFilePath);
    JSONObject noteJsonObject = new JSONObject();
    noteJsonObject.put("password", note.getPassword());
  }

  // these 4 methods are helpers for the create note method
  private static void saveNoteContentToTxtFile(String filePath, String content) {
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write(content);
    } catch (IOException e) {
      System.err.println("An error occurred: " + e.getMessage());
    }
  }

  private static void createNoteJsonFile(String notePath, Note note) {
    Path jsonFilePath = Paths.get(notePath + "/note.json");
    List<Image> images = note.getImages();
    Sketch sketch = note.sketch;
    try {
      Files.createFile(jsonFilePath);
      JSONObject noteJsonObject = new JSONObject();

      // adding the title to the note
      noteJsonObject.put("title", note.getTitle());

      // adding the isSecure property
      noteJsonObject.put("isSecure", note.isSecure());

      // adding the images array
      JSONArray imagesArray = new JSONArray();
      images.forEach(image -> {
        imagesArray.put(makeImageJsonObject(image));
      });
      noteJsonObject.put("images", imagesArray);

      // adding the sketch object
      noteJsonObject.put("sketch", makeSketchJsonObject(sketch));

      // adding the json object to the json file
      Files.write(Paths.get(notePath + "note.json"), noteJsonObject.toString(4).getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static JSONObject makeImageJsonObject(Image image) {
    return new JSONObject()
            .put("title", image.title)
            .put("createdAt", image.createdAt)
            .put("filePath", image.filePath);
  }

  private static JSONObject makeSketchJsonObject(Sketch sketch) {
    if (sketch == null) return null;
    return new JSONObject()
            .put("title", sketch.title)
            .put("createdAt", sketch.createdAt)
            .put("filePath", sketch.filePath);
  }


  // this will be used in the note class to add an image
  // this is used to copy the note from its location to the note folder
  public static void addImageToNoteFolder(String imageTitle, String imagePath, String notePath) throws IOException {
    File noteFolder = new File(notePath);
    File imagesFolder = new File(noteFolder, "images");

    if (!imagesFolder.exists()) {
      imagesFolder.mkdirs(); // Create the images directory if it doesn't exist
    }

    File destinationFile = new File(imagesFolder, imageTitle);
    // Copy the image to the images directory
    Files.copy(Paths.get(imagePath), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
  }

  public static void removeImageFromNoteFolder(String imagePath) throws IOException {
    File image = new File(imagePath);
    image.delete();
  }

  // the following 4 methods for loading all user notes
  public static ArrayList<Note> loadAllUserNotes(String folderPath) {
    // the folder path should be like this "./database/users/user/"
    ArrayList<Note> Notes = new ArrayList<>();
    File userFolder = new File(folderPath);

    if (userFolder.exists() && userFolder.isDirectory()) {
      File[] notesFolders = userFolder.listFiles();
      if (notesFolders != null) {
        for (File folder : notesFolders) {
          if (folder.isDirectory()) {
            Notes.add(readShallowNote(folder.getPath()));
          }
        }
      }
    }
    return Notes;
  }

  // this will go inside the note folder and read the json file of the note
  private static Note readShallowNote(String folderPath) {
    String jsonFilePath = folderPath + "/note.json";
    try {
      String noteJson = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
      JSONObject noteObject = new JSONObject(noteJson);

      String title = noteObject.getString("title");
      String password = noteObject.getString("password");
      boolean isSecure = noteObject.getBoolean("isSecure");

      if (isSecure)
        return new SecureNote(title, password, folderPath);
      else
        return new Note(title, folderPath);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  } // this returns a note with a title and a content

  public static Note getNote(Note note) throws IOException {
    String textFilePath = note.getFolderPath() + "/content.txt";
    String jsonFilePath = note.getFolderPath() + "/note.json";
    String content = new String(Files.readAllBytes(Paths.get(textFilePath)));
    String noteJson = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

    JSONObject noteObject = new JSONObject(noteJson);
    JSONObject sketchObject = noteObject.getJSONObject("sketch");
    JSONArray imagesArray = noteObject.getJSONArray("images");

    note.setContent(content);
    note.setImages(getImages(imagesArray));
    note.sketch = readSketchFromJson(sketchObject);
    return note;
  }

  public static SecureNote getSecureNote(SecureNote note) throws IOException {
    return (SecureNote) getNote(note);
  }

  private static ArrayList<Image> getImages(JSONArray imagesJsonArray) {
    ArrayList<Image> images = new ArrayList<>();
    if (!imagesJsonArray.isEmpty())
      for (int i = 0; i < imagesJsonArray.length(); i++) {
        JSONObject image = imagesJsonArray.getJSONObject(i);
        images.add(readImageFromJson(image));
      }
    return images;
  }

  private static Image readImageFromJson(JSONObject imageJsonObject) {
    String title = imageJsonObject.getString("title");
    Long createdAt = imageJsonObject.getLong("createdAt");
    String filePath = imageJsonObject.getString("filePath");
    return new Image(title, createdAt, filePath);
  }

  private static Sketch readSketchFromJson(JSONObject sketchJsonObject) {
    if (sketchJsonObject == null) {
      return null;
    }
    String title = sketchJsonObject.getString("title");
    Long createdAt = sketchJsonObject.getLong("createdAt");
    String filePath = sketchJsonObject.getString("filePath");
    return new Sketch(title, createdAt, filePath);
  }

  // this may be usefull
  private File findFileInFolder(File folder, String fileName) {

    File[] files = folder.listFiles();
    if (files != null) {
      for (File file : files) {
        // Check if it's the note.json file
        if (file.isFile() && file.getName().equalsIgnoreCase(fileName)) {
          return file; // Return the File object if found
        }
      }
    }
    return null;
  }
}
