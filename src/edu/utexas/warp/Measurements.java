package edu.utexas.warp;

public class Measurements {

	public Double totalDemand() {
		//the method returns total demand by summing up all the values of H matrix.
	}
	
	public Double SPTT() {
		//the method returns SPTT by analyzing the matrix provided using iterations.
	}
	
	public Double TSTT() {
		//the method returns TSTT by analyzing the matrix provided using iterations.
	}
	
	public Double AEC() {
		//the method returns AEC by analyzing the matrix provided using iterations.
		return (TSTT()-SPTT())/totalDemand();
	}
	
	public Double relativeGap() {
		//the method returns relative gap {1/2, 1/3, 1/4, 1/5, .......} by analyzing the matrix provided using iterations.
		return (TSTT()-SPTT())/SPTT();
	}
}
