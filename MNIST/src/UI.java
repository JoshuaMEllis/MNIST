import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UI {

	UIHandler uiHandler = new UIHandler();
	JFrame frame;
	private JLabel lblSampleSize;
	private JLabel lblTrialImg;
	private JLabel lblEstimation;
	private JLabel lblConfidence;
	private JLabel lblTrialImagePath;
	private JTextField txtTrialImgPath;
	private JTextField txtSampleSize;
	private JButton btnComparison;
	private JButton btnLoadTrialImg;
	private JButton btnSampleSize;

	// Initialise the constructor
	public UI() throws IOException {
		initialize();
	}

	// Paint the components in the frame and add them to the frame
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(400, 400, 420, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtTrialImgPath = new JTextField();
		txtTrialImgPath.setBounds(10, 50, 293, 20);
		frame.getContentPane().add(txtTrialImgPath);
		txtTrialImgPath.setColumns(10);

		txtSampleSize = new JTextField();
		txtSampleSize.setColumns(10);
		txtSampleSize.setBounds(10, 98, 293, 20);
		frame.getContentPane().add(txtSampleSize);

		lblTrialImg = new JLabel();
		frame.getContentPane().add(lblTrialImg);

		btnLoadTrialImg = new JButton("Load");
		btnLoadTrialImg.setBounds(313, 49, 77, 23);
		frame.getContentPane().add(btnLoadTrialImg);
		// Upon button click, set the trial image
		btnLoadTrialImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTrialImage();
			}
		});

		btnComparison = new JButton("Compare");
		btnComparison.setBounds(10, 500, 89, 39);
		frame.getContentPane().add(btnComparison);
		// Upon button click, run the comparison between the trial image and the dataset
		// images
		btnComparison.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeComparison();
			}
		});

		lblEstimation = new JLabel("");
		lblEstimation.setBounds(170, 500, 240, 39);
		lblEstimation.setFont(new Font("Dialog", Font.BOLD, 42));
		frame.getContentPane().add(lblEstimation);

		lblConfidence = new JLabel("");
		lblConfidence.setBounds(220, 500, 240, 39);
		lblConfidence.setFont(new Font("Dialog", Font.BOLD, 26));
		frame.getContentPane().add(lblConfidence);

		lblTrialImagePath = new JLabel("Trial Image Path");
		lblTrialImagePath.setBounds(10, 33, 97, 14);
		frame.getContentPane().add(lblTrialImagePath);

		btnSampleSize = new JButton("Set");
		btnSampleSize.setBounds(313, 97, 77, 23);
		frame.getContentPane().add(btnSampleSize);
		// Upon button click, set the sample size
		btnSampleSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setSampleSize();
			}
		});

		lblSampleSize = new JLabel("Sample Size (50 by default)");
		lblSampleSize.setBounds(10, 81, 200, 14);
		frame.getContentPane().add(lblSampleSize);
	}

	// Run the comparison function and display the results in the ui
	private void makeComparison() {
		uiHandler.makeComparison();
		lblEstimation.setText(String.valueOf(uiHandler.getEstimation()));
		lblConfidence.setText(String.valueOf(uiHandler.getConfidence() + "%"));
	}

	// Pass the desired sample size to the comparator
	private void setSampleSize() {
		uiHandler.setSampleSize(txtSampleSize.getText());
	}

	/*
	 * Remove the label holding the trial, run a function to get the new image as an
	 * icon to then add the label and repaint the frame
	 */
	private void setTrialImage() {
		frame.remove(lblTrialImg);
		lblTrialImg.removeAll();
		lblTrialImg = uiHandler.setTrialImage(txtTrialImgPath.getText(), lblTrialImg);
		frame.getContentPane().add(lblTrialImg);
		frame.repaint();
	}
}
