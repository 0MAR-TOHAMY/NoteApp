import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class UserAuthentication {
  private static final String PROPERTIES_FILE = "./database/users.properties";

  public static int authenticateRegister(String username, String password, String confirm) throws Exception {
    Properties properties = new Properties();
    properties.load(new FileInputStream(PROPERTIES_FILE));

    if (username.length() < 1 || password.length() < 1) {
      return -3;
    } else {
      if (properties.containsKey(username)) {
        return -1;
      } else {
        if (password.equals(confirm)) {
          properties.setProperty(username, password);
          properties.store(new FileOutputStream(PROPERTIES_FILE), null);
          new File("./database/UsersData/" + username).mkdir();
          return 1;
        } else {
          return -2;
        }
      }
    }
  }

  public static boolean authenticateLogin(String username, String password) throws Exception {
    Properties properties = new Properties();
    properties.load(new FileInputStream(PROPERTIES_FILE));

    if (properties.containsKey(username)) {
      return properties.getProperty(username).equals(password);
    } else {
      return false;
    }
  }
}
