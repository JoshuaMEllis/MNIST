import java.io.IOException;

public class Comparator {
	int K_DEFAULT = 50;
	int k;
	double[][] distances;
	int[] comparandImgData;
	byte[] comparandListgData;
	int[] trialImgData;
	int comparandSize;
	int imgHeight;
	int imgWidth;

	// Initialise constructor
	public Comparator() {
	}

	// Fill necessary variables
	private void intialiseVars(Comparand comparand, TrialData trialData) throws IOException {
		comparandImgData = comparand.getImgIntValues();
		imgHeight = comparand.getImageHeight();
		imgWidth = comparand.getImageWidth();
		comparandListgData = comparand.getDatasetLabelData();
		comparandSize = comparand.getComparandSize();
		trialImgData = trialData.getImgIntValues();
		// Ensure there is always a sample size larger than 0
		if (k <= 0) {
			setSampleSize(K_DEFAULT);
		}
	}
	
	// Compare trial image with dataset file
	public Comparison makeComparison(Comparand comparand, TrialData trialData) throws Exception {
		intialiseVars(comparand, trialData);
		distances = new double[comparandSize][2];
		distances = compareDistances(distances);
		distances = sortDistance(distances);

		// Calculate results and store in 'POJO'
		Comparison comparison = new Comparison();
		int estimation = (int) Math.floor(getEstimation(distances));
		comparison.setEstimation(estimation);
		comparison.setConfidence(getConfidence(estimation));
		return comparison;
	}

	/*
	 * Calculate total Euclidian distance between the trial image and each image in
	 * the training dataset, this is done in 2 separate threads
	 */
	private double[][] compareDistances(double[][] distances) throws Exception {
		EuclidianThread thread1 = new EuclidianThread();
		thread1.passVars(imgHeight, imgWidth, trialImgData, comparandImgData, comparandListgData, 0, comparandSize / 2,
				comparandSize / 2);
		double[][] a = thread1.call();
		EuclidianThread thread2 = new EuclidianThread();
		thread2.passVars(imgHeight, imgWidth, trialImgData, comparandImgData, comparandListgData, comparandSize / 2,
				comparandSize, comparandSize / 2);
		double[][] b = thread2.call();

		// Merge the result of both threads
		System.arraycopy(a, 0, distances, 0, a.length);
		System.arraycopy(b, 0, distances, a.length, b.length);

		return distances;
	}

	// Sort the 2 dimensional array by the values at [0]
	private double[][] sortDistance(double[][] unorderedArray) {
		java.util.Arrays.sort(unorderedArray, new java.util.Comparator<double[]>() {
			public int compare(double[] dbl1, double[] dbl2) {
				return Double.compare(dbl1[0], dbl2[0]);
			}
		});

		return unorderedArray;
	}

	// Find most common value in [1] of array
	private double getEstimation(double[][] array) {
		double[] tempArray = new double[array.length];
		
		for (int i = 0; i < array.length; i++) {
			tempArray[i] = array[i][1];
		}

		int tempCount;
		double tempVar = 0;
		int regularCount = 1;
		double regularValue = tempArray[0];

		for (int i = 0; i < k; i++) {
			tempVar = tempArray[i];
			tempCount = 0;

			for (int j = 1; j < k; j++) {
				if (tempVar == tempArray[j]) {
					tempCount++;
				}
			}

			if (tempCount > regularCount) {
				regularValue = tempVar;
				regularCount = tempCount;
			}
		}

		return regularValue;
	}

	/*
	 * Find percentage of values in sample of the array that are equal to the most
	 * common value in the entire array
	 */
	private double getConfidence(int estimation) {
		double confidence;
		double countEst = 0.0;

		for (int i = 0; i < k; i++) {
			if (distances[i][1] == estimation) {
				countEst++;
			}
		}

		confidence = (countEst / k) * 100;

		return confidence;
	}

	// Set sample size from integer value
	public void setSampleSize(int sampleSize) {
		k = sampleSize;
	}

	// Set sample size from string value
	public void setSampleSize(String sampleSize) {
		int newSampleSize = 0;

		// If the string is parsable to integer then do so, otherwise set to 50
		try {
			newSampleSize = Integer.parseInt(sampleSize);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (newSampleSize <= 0) {
			K_DEFAULT = 50;
		} else {
			k = newSampleSize;
		}
	}
}
