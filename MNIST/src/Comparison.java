public class Comparison {
	//Assumed numerical value of trial image
	int estimation;
	//Percentage of values found in sample
	double confidence;
	
	//Initialise constructor
	public Comparison() {}
	
	//Set estimation value
	public void setEstimation(int estimation){
		this.estimation = estimation;
	}
	
	//Return estimation value
	public int getEstimation(){
		return estimation;
	}
	
	//Set confidence value
	public void setConfidence(double confidence){
		this.confidence = confidence;
	}
	
	//Return confidence value
	public double getConfidence(){
		return confidence;
	}
}
