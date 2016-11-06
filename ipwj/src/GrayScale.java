/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asrory
 */
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GrayScale extends JPanel {

    BufferedImage image;
    int width;
    int height;

    public ImageIcon gray() throws IOException {

	File input = new File("/opt/lampp/htdocs/myrepo/java/image-processing-with-java/ipwj/src/tama.jpg");
	image = ImageIO.read(input);
	width = image.getWidth();
	height = image.getHeight();

	for (int i = 0; i < height; i++) {

	    for (int j = 0; j < width; j++) {

		Color c = new Color(image.getRGB(j, i));		
		int red = (int) (c.getRed() * 0.299);
		int green = (int) (c.getGreen() * 0.587);
		int blue = (int) (c.getBlue() * 0.114);
		int rgbTogray=(red + green + blue)/3;
		Color gray = new Color(rgbTogray,rgbTogray,rgbTogray);
		image.setRGB(j, i, gray.getRGB());
	    }
	}
	ImageIcon ic = new ImageIcon(image);
	return ic;
    }

    public static void main(String args[]) throws Exception {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JLabel img = new JLabel();
	GrayScale obj = new GrayScale();
	img.setIcon(obj.gray());
	frame.add(img);
	frame.setSize(obj.width,obj.height);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
    }
}
