import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {
  Font Jura;

  {
    try {
      Jura = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/Jura.ttf"));
    } catch (FontFormatException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  static Font main = new Fonts().Jura;
  static Font font1 = main.deriveFont(Font.BOLD, 48);
  static Font font2 = main.deriveFont(Font.BOLD, 24);
  static Font font3 = main.deriveFont(Font.BOLD, 20);
  static Font font4 = main.deriveFont(Font.BOLD, 28);
  static Font font5 = main.deriveFont(Font.BOLD, 15);
  static Font font6 = main.deriveFont(Font.BOLD, 32);
  static Font font7 = main.deriveFont(Font.PLAIN, 18);
}
