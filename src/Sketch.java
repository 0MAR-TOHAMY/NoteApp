import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Sketch extends JPanel {

    JButton outBtn, b, r, g, y, w, s, m, l, bl;
    JLabel title;
    private BufferedImage canvas;
    private Graphics2D g2d;
    private int lastX, lastY;

    public Sketch(){
        setPreferredSize(new Dimension(1200, 700));
        setLayout(new BorderLayout());
        setBackground(Color.decode("#EBF8FF"));

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(1200, 60));
        topPanel.setLayout(null);
        topPanel.setBackground(Note.dark);

        title = new JLabel("save");
        title.setFont(Fonts.font4);
        title.setForeground(Color.white);
        title.setBounds(20, 12, 500, 33);
        topPanel.add(title);

        ImageIcon outIcon = new ImageIcon("/Users/Admin/IdeaProjects/NoteApp/assets/next.png");
        Image scaledImage2 = outIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        outBtn = new JButton(scaledIcon2);
        outBtn.setBounds(1128, 8, 40, 40);
        outBtn.setBackground(Note.dark);
        outBtn.setBorder(null);
        outBtn.setFocusable(false);
        outBtn.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "dashboard"));
        topPanel.add(outBtn);

        b = new JButton("");
        b.setBounds(390, 15, 30, 30);
        b.setBackground(Color.BLUE);
        b.setBorder(null);
        b.setFocusable(false);
        b.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "dashboard"));
        topPanel.add(b);

        r = new JButton("");
        r.setBounds(430, 15, 30, 30);
        r.setBackground(Color.RED);
        r.setBorder(null);
        r.setFocusable(false);
        r.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "dashboard"));
        topPanel.add(r);

        g = new JButton("");
        g.setBounds(470, 15, 30, 30);
        g.setBackground(Color.GREEN);
        g.setBorder(null);
        g.setFocusable(false);
        g.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "dashboard"));
        topPanel.add(g);

        y = new JButton("");
        y.setBounds(510, 15, 30, 30);
        y.setBackground(Color.YELLOW);
        y.setBorder(null);
        y.setFocusable(false);
        y.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "dashboard"));
        topPanel.add(y);

        bl = new JButton("");
        bl.setBounds(550, 15, 30, 30);
        bl.setBackground(Color.BLACK);
        bl.setBorder(null);
        bl.setFocusable(false);
        bl.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "dashboard"));
        topPanel.add(bl);

        w = new JButton("");
        w.setBounds(590, 15, 30, 30);
        w.setBackground(Color.WHITE);
        w.setBorder(null);
        w.setFocusable(false);
        w.addActionListener(e -> Note.cardLayout.show(Note.cardLayoutPanel, "dashboard"));
        topPanel.add(w);

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