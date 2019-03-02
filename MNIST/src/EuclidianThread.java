import java.util.concurrent.Callable;

class EuclidianThread implements Callable<double[][]> {

	int imgHeight;
	int imgWidth;
	int[] comparandImgData;
	int[] trialImgData;
	byte[] comparandListgData;
	double[][] distances;
	int size;
	int startAt;
	int comparandSize;

	/*
	 * Loop through the dataset image data calculating the euclidian distance
	 * between the value of the pixels in each iteration, number of iterations and
	 * initial position set by variables passed
	 */
	public double[][] call() throws Exception {
		for (int index = startAt; index < comparandSize; index++) {
			int sum = 0;
			for (int y = 0; y < imgHeight; y++) {
				for (int x = 0; x < imgWidth; x++) {
					// This calculates the euclidian distance, a crucial part of the KNN algorithm
					sum += (comparandImgData[(y * imgHeight) + x + (index * (imgHeight * imgWidth))] - trialImgData[(y * imgHeight) + x]) * (comparandImgData[(y * imgHeight) + x + (index * (imgHeight * imgWidth))] - trialImgData[(y * imgHeight) + x]);
				}
			}
			distances[index - startAt][0] = java.lang.Math.sqrt(sum);
			distances[index - startAt][1] = comparandListgData[8 + index];
			sum = 0;
		}
		return distances;
	}

	// Set the necessary values to run call()
	public void passVars(int imgHeight, int imgWidth, int[] trialImgData, int[] comparandImgData,
			byte[] comparandListgData, int startAt, int comparandSize, int size) {
		this.imgHeight = imgHeight;
		this.imgWidth = imgWidth;
		this.trialImgData = trialImgData;
		this.comparandImgData = comparandImgData;
		this.comparandListgData = comparandListgData;
		this.startAt = startAt;
		this.comparandSize = comparandSize;
		this.size = size;
		this.distances = new double[size][2];
	}
}
