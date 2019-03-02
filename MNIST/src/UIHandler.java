import java.awt.EventQueue;
import javax.swing.JLabel;

public class UIHandler {

	// Initialise the constructor
	public UIHandler() {
	}

	// Initialise the ui and display
	public void initialiseUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Set the local comparison instance to the object value of the return of the
	 * comparison between the trial image and the dataset images
	 */
	public void makeComparison() {
		MNIST.makeComparison();
	}

	// Returns the estimation of the comparison
	public int getEstimation() {
		return MNIST.getEstimation();
	}

	// Returns the confidence of the comparison
	public double getConfidence() {
		return MNIST.getConfidence();
	}

	// Set the sample size of the comparator
	public void setSampleSize(String sampleSize) {
		MNIST.setSampleSize(sampleSize);
	}

	// Set the trial image and initialise a new label with an image icon of the new
	// trial image
	public JLabel setTrialImage(String trialImgPath, JLabel lblTrialImg) {
		JLabel lblNewLabel = new JLabel(MNIST.setTrialData(trialImgPath));
		lblNewLabel.setBounds(30, 130, 350, 350);
		return lblNewLabel;
	}
}
