/**
 *
 * @author Tama Asrory Ridhana
 * @serial Pengolahan Citra Digital , Gary-Scale
 * @version 1.0
 * @since  هِجْرَة : ٩ سؤر ١٤٣٨  
 * 
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GrayScale extends JPanel {

    BufferedImage image;
    int width;
    int height;
    int counter = 0;

    public ImageIcon gray() throws IOException {
	File input = new File("/opt/lampp/htdocs/myrepo/java/image-processing-with-java/ipwj/src/tama1.png");
	image = ImageIO.read(input);
	width = image.getWidth();
	height = image.getHeight();
//	System.out.println("W : " + width + " x" + " H : " + height); // print resolution
	setColor(setPixel());
	ImageIcon ic = new ImageIcon(image);
	return ic;
    }

    public int[][] setPixel() {

	int pixel[][] = new int[height][width];

	for (int i = 0; i < height; i++) {
	    for (int j = 0; j < width; j++) {
		Color c = new Color(image.getRGB(j, i));
		int red = (int) (c.getRed() & 0x000000FF0);
		int green = (int) (c.getGreen() & 0x0000FF00) >> 8;
		int blue = (int) (c.getBlue() & 0x00FF0000) >> 16;
		counter += pixel[i][j] = (red + green + blue) / 3;
	    }
	}
	return pixel;
    }

    public void setColor(int[][] pixel) {
//	System.out.println("RATA2 = " + (counter / (width * height))); // print average color in integer
	for (int i = 0; i < height; i++) {
	    for (int j = 0; j < width; j++) {
		if (pixel[i][j] < (counter / (width * height))) {
		    pixel[i][j] = 0;
		} else {
		    pixel[i][j] = 255;
		}
//		System.out.println("BINER = " + pixel[i][j]); // print BINARI color as LOG
		Color gray = new Color(pixel[i][j], pixel[i][j], pixel[i][j]);
		image.setRGB(j, i, gray.getRGB());
	    }
	}

    }

    public static void main(String args[]) throws Exception {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JLabel img = new JLabel();
	GrayScale obj = new GrayScale();
	img.setIcon(obj.gray());
	frame.add(img);
	frame.setSize(obj.width, obj.height);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
    }
}
