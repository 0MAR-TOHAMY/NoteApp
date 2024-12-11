import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Sketch extends JPanel {

  JButton outBtn, b, r, g, y, w, s, m, l, bl, save;
  private BufferedImage canvas;
  private Graphics2D g2d;
  private int lastX, lastY;

  public Sketch() {
    setPreferredSize(new Dimension(1200, 700));
    setLayout(new BorderLayout());
    setBackground(Color.decode("#EBF8FF"));

    JPanel topPanel = new JPanel();
    topPanel.setPreferredSize(new Dimension(1200, 60));
    topPanel.setLayout(null);
    topPanel.setBackground(Note.dark);

    save = new JButton("Save");
    save.setFont(Fonts.font3);
    save.setForeground(Note.dark);
    save.setBackground(Color.white);
    save.setBorder(null);
    save.setFocusable(false);
    save.setBounds(20, 15, 70, 30);
    save.addActionListener(e -> saveSketch(new File("database/UsersData/test.png")));
    topPanel.add(save);

    ImageIcon outIcon = new ImageIcon("./assets/next.png");
    Image scaledImage2 = outIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
    outBtn = new JButton(scaledIcon2);
    outBtn.setBounds(1128, 8, 40, 40);
    outBtn.setBackground(Note.dark);
    outBtn.setBorder(null);
    outBtn.setFocusable(false);
    outBtn.addActionListener(e -> loadSketch(new File("database/UsersData/test.png")));
    topPanel.add(outBtn);

    b = new JButton("");
    b.setBounds(390, 15, 30, 30);
    b.setBackground(Color.BLUE);
    b.setBorder(null);
    b.setFocusable(false);
    b.addActionListener(e -> g2d.setPaint(Color.BLUE));
    topPanel.add(b);

    r = new JButton("");
    r.setBounds(430, 15, 30, 30);
    r.setBackground(Color.RED);
    r.setBorder(null);
    r.setFocusable(false);
    r.addActionListener(e -> g2d.setPaint(Color.RED));
    topPanel.add(r);

    g = new JButton("");
    g.setBounds(470, 15, 30, 30);
    g.setBackground(Color.GREEN);
    g.setBorder(null);
    g.setFocusable(false);
    g.addActionListener(e -> g2d.setPaint(Color.GREEN));
    topPanel.add(g);

    y = new JButton("");
    y.setBounds(510, 15, 30, 30);
    y.setBackground(Color.YELLOW);
    y.setBorder(null);
    y.setFocusable(false);
    y.addActionListener(e -> g2d.setPaint(Color.YELLOW));
    topPanel.add(y);

    bl = new JButton("");
    bl.setBounds(550, 15, 30, 30);
    bl.setBackground(Color.BLACK);
    bl.setBorder(null);
    bl.setFocusable(false);
    bl.addActionListener(e -> g2d.setPaint(Color.BLACK));
    topPanel.add(bl);

    w = new JButton("");
    w.setBounds(590, 15, 30, 30);
    w.setBackground(Color.WHITE);
    w.setBorder(null);
    w.setFocusable(false);
    w.addActionListener(e -> {
      g2d.setPaint(Color.WHITE);
      g2d.setStroke(new BasicStroke(40));
    });
    topPanel.add(w);

    s = new JButton("S");
    s.setBounds(690, 15, 30, 30);
    s.setBackground(Color.WHITE);
    s.setFont(Fonts.font3);
    s.setForeground(Note.dark);
    s.setBorder(null);
    s.setFocusable(false);
    s.addActionListener(e -> g2d.setStroke(new BasicStroke(2)));
    topPanel.add(s);

    m = new JButton("M");
    m.setBounds(730, 15, 30, 30);
    m.setBackground(Color.WHITE);
    m.setFont(Fonts.font3);
    m.setForeground(Note.dark);
    m.setBorder(null);
    m.setFocusable(false);
    m.addActionListener(e -> g2d.setStroke(new BasicStroke(8)));
    topPanel.add(m);

    l = new JButton("L");
    l.setBounds(770, 15, 30, 30);
    l.setBackground(Color.WHITE);
    l.setFont(Fonts.font3);
    l.setForeground(Note.dark);
    l.setBorder(null);
    l.setFocusable(false);
    l.addActionListener(e -> g2d.setStroke(new BasicStroke(15)));
    topPanel.add(l);

    canvas = new BufferedImage(1200, 700, BufferedImage.TYPE_INT_ARGB);
    g2d = canvas.createGraphics();
    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(2));
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    clearCanvas();

    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        g2d.drawLine(lastX, lastY, x, y);
        lastX = x;
        lastY = y;
        repaint();
      }
    });

    add(topPanel, BorderLayout.NORTH);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(canvas, 0, 0, null);
  }

  public void clearCanvas() {
    g2d.setPaint(Color.WHITE);
    g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    g2d.setPaint(Color.BLACK);
    repaint();
  }

  public void saveSketch(File file) {
    try {
      ImageIO.write(canvas, "PNG", file);
      JOptionPane.showMessageDialog(this, "Sketch saved successfully!");
    } catch (Exception ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(this, "Error saving sketch.");
    }
  }

  public void loadSketch(File file) {
    try {
      canvas = ImageIO.read(file);
      g2d = canvas.createGraphics();
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      repaint();
    } catch (Exception ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(this, "Error loading sketch.");
    }
  }
}
