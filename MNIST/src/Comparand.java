import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Comparand extends Image {
	FileHandler fileHandler = new FileHandler();
	String DEFAULT_IMG_PATH = System.getProperty("user.dir")+"/Dataset/train-images.idx3-ubyte";
	String DEFAULT_LABEL_PATH = System.getProperty("user.dir")+"/Dataset/train-labels.idx1-ubyte";
	byte[] datasetLabelData;
	int comparandSize = 0;
	
	//Initialise constructor and fill necessary varibles
	public Comparand() {
		try {
			initialiseComparand();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Fill both dataset image file variables and dataset label file vairables
	private void initialiseComparand() throws IOException {
		initialiseDatasetImgData();
		initialiseDatasetLabelData();
	}
	
	/*Read the dataset image file, read first four bytes to ensure it is the correct file
	 * and then read in four byte intervals to get necessary values
	 * (key and other information is stored at beginning of file)*/
	/*
	 * If the pixel has a value that is darker than plain white, give it a value of
	 * 255 (black) this is to ensure the full area of the number is captured
	 * equally, giving a more accurate estimation
	 */
	private void initialiseDatasetImgData() throws IOException {
		DataInputStream imgDatasetStream = new DataInputStream(new FileInputStream(DEFAULT_IMG_PATH));
		if (imgDatasetStream.readInt() == 2051) {
			byte[] datasetImgBytes = fileHandler.readFileBytes(DEFAULT_IMG_PATH);			
			comparandSize = imgDatasetStream.readInt();
			imgHeight = imgDatasetStream.readInt();
			imgWidth = imgDatasetStream.readInt();

			imgIntValues = new int[((imgWidth * imgHeight)*comparandSize)];
			for (int index = 0; index < comparandSize; index++) {
				for (int y = 0; y < imgHeight; y++) {
					for (int x = 0; x < imgWidth; x++) {
						// Start at a minimum of 17 to avoid the initial 18 bytes of dataset information
						if (datasetImgBytes[16 + (y * imgHeight) + x + (index * (imgHeight * imgWidth))] < -1) {
							imgIntValues[(y * imgHeight) + x + (index * (imgHeight * imgWidth))] = 255;
						} else {
							imgIntValues[(y * imgHeight) + x + (index * (imgHeight * imgWidth))] = 0;
						}
					}
				}
			}
		}
		imgDatasetStream.close();
	}
	
	/*Read the dataset label file, read first four bytes to ensure it is the correct file
	 * (key stored at beginning of file)*/
	private void initialiseDatasetLabelData() throws IOException {
		DataInputStream labelDatasetStream = new DataInputStream(new FileInputStream(DEFAULT_LABEL_PATH));
		if (labelDatasetStream.readInt() == 2049) {
			datasetLabelData = fileHandler.readFileBytes(DEFAULT_LABEL_PATH);
		}
		labelDatasetStream.close();
	}
	
	//Return bytes from dataset label file 
	public byte[] getDatasetLabelData() {
		return datasetLabelData;
	}

	//Return total number of dataset images
	public int getComparandSize() {
		return comparandSize;
	}
}
