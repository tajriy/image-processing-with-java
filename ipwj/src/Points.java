import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
 
import javax.swing.JPanel;
import javax.swing.JFrame;
 
import java.util.Random;
 
public class Points extends JPanel {
 
//  public void paintComponent(Graphics g) {
//      super.paintComponent(g);
// 
//      Graphics2D g2d = (Graphics2D) g;
// 
//      g2d.setColor(Color.blue);
// 
//      Dimension size = getSize();
//      Insets insets = getInsets();
// 
//      int w =  size.width - insets.left - insets.right;
//      int h =  size.height - insets.top - insets.bottom;
// 
//      Random r = new Random();
// 
//      for (int i=0; i<100; i++) {
//          int x = Math.abs(r.nextInt()) % w;
//          int y = Math.abs(r.nextInt()) % h;
//          g2d.drawLine(x, y, x, y);
//      }
//  }
// 
  public static void main(String[] args) {
 
      JFrame frame = new JFrame("Points");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(new Points());
      frame.setSize(250, 200);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
  }
}