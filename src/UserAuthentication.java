import src.FileManager;

public class UserAuthentication {

  public static int authenticateRegister(String username, String password, String confirm) {
    if (username.length() < 1 || password.length() < 1) {
      return -3;
    }
    if (FileManager.users.containsKey(username)) {
      return -1;
    }
    if (!password.equals(confirm)) {
      return -2;
    }
    FileManager fileManager = new FileManager();
    fileManager.createUser(username, password);
    return 1;
  }

  public static boolean authenticateLogin(String username, String password) {
    if (FileManager.users.containsKey(username)) {
      return FileManager.users.get(username).password.equals(password);
    }
    return false;
  }
}