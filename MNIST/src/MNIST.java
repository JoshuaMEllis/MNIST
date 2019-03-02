import javax.swing.ImageIcon;

public class MNIST {
	static Comparator comparator = new Comparator();
	static Comparand comparand = new Comparand();
	static TrialData trialData = new TrialData();
	static Comparison comparison = new Comparison();
	static ImageHandler imageHandler = new ImageHandler();
	static UIHandler uIHandler;
	
	//Start user interface
	public static void main(String[] args) throws Exception {
		uIHandler = new UIHandler();
		uIHandler.initialiseUI();
	}
	
	/*Set the local comparison instance to the object value of the return of the comparison
	 * between the trial image and the dataset images*/ 
	public static void makeComparison() {
		try {
			comparison = comparator.makeComparison(comparand, trialData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Returns the estimation of the comparison
	public static int getEstimation() {
		return comparison.getEstimation();
	}

	//Returns the confidence of the comparison
	public static double getConfidence() {
		return comparison.getConfidence();
	}
	
	//Set the sample size of the comparator
	public static void setSampleSize(String sampleSize) {
		comparator.setSampleSize(sampleSize);
	}

	//Set the trial image and return an image icon of the new trial image
	public static ImageIcon setTrialData(String trialImgPath) {
		trialData.setTrialData(trialImgPath, comparand.getImageHeight(), comparand.getImageWidth());
		return imageHandler.getImageIcon(trialData.getBufferedImage(), 350, 350);
	}
}
