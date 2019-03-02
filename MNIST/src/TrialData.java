import java.awt.image.BufferedImage;

public class TrialData extends Image{
	ImageHandler imageHandler = new ImageHandler();
	BufferedImage trialImage;

	// Initialise constructor
	public TrialData() {
	}

	/*
	 * Read the image at the given path and convert pixel vlaues to array of integer
	 * values to be compared to the dataset images
	 */
	public void setTrialData(String trialImgPath, int desiredHeight, int desiredWidth) {
		trialImage = imageHandler.readBufferedImage(trialImgPath, desiredHeight, desiredWidth);
		if (trialImage != null) {
			imgHeight = trialImage.getHeight();
			imgWidth = trialImage.getWidth();

			int[] imgValues = trialImage.getRGB(0, 0, imgWidth, imgHeight, null, 0, imgHeight);
			int[] cleanedImageValuess = new int[imgWidth * imgHeight];

			/*
			 * Loop through each pixel in the first row, then increment a row and loop
			 * through that row until the number of rows has been met
			 */
			/*
			 * If the pixel has a value that is darker than plain white, give it a value of
			 * 255 (black) this is to ensure the full area of the number is captured
			 * equally, giving a more accurate estimation
			 */
			for (int y = 0; y < imgHeight; y++) {
				for (int x = 0; x < imgWidth; x++) {
					if (imgValues[(y * imgHeight) + x] < -1) {
						cleanedImageValuess[(y * imgHeight) + x] = 255;
					} else {
						cleanedImageValuess[(y * imgHeight) + x] = 0;
					}
				}
			}
			imgIntValues = cleanedImageValuess;
		}
	}

	// Return trial image
	public BufferedImage getBufferedImage() {
		return trialImage;
	}
}
