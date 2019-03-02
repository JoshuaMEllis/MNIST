import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageHandler {
	FileHandler fileHandler = new FileHandler();

	//Initialise the constructor
	public ImageHandler() {}

	//Return buffered image from given path, compressed to given dimensions
	public BufferedImage readBufferedImage(String imagePath, int desiredHeight, int desiredWidth) {
		BufferedImage imgBuffered = null;

		try {
			imgBuffered = compressImage(ImageIO.read(fileHandler.getFile(imagePath)), desiredHeight, desiredWidth);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return imgBuffered;
	}

	//Return given image after redrawing in a image file with the given dimensions
	public BufferedImage compressImage(BufferedImage uncompressedImg, int newHeight, int newWidth) {
		BufferedImage compressedBuffered = new BufferedImage(newHeight, newWidth, BufferedImage.TYPE_INT_RGB);
		Image compressedImage = uncompressedImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		
		compressedBuffered.getGraphics().drawImage(compressedImage, 0, 0, null);

		return compressedBuffered;
	}

	//Return given image as an image icon with the dimensions given
	public ImageIcon getImageIcon(BufferedImage orginialImage, int iconHeight, int iconWidth) {
		ImageIcon icon = null;
		
		if (orginialImage != null) {
			Image resizedImage = orginialImage.getScaledInstance(iconHeight, iconWidth, Image.SCALE_SMOOTH);
			icon = new ImageIcon(resizedImage);	
		}

		return icon;
	}
}
