package edu.utexas.warp;

import java.util.Map;

public class Measurements {
	
	//the method returns total demand by summing up all the values of H matrix.
	public Double totalDemand(Map<Double, Map<Path, Double>> currentH) {
		Double demand = Double.valueOf(0);
		for (Map<Path,Double> te : currentH.values()) {
			for (Double d : te.values()) {
				demand += d;
			}
		}
		return demand;
	}
	

	//the method returns TTT by analyzing the matrix provided using iterations.
	public Double TTT(Map<Double, Map<Path, Double>> hMatrix) {
		Double totalTravelTime = Double.valueOf(0);
		for (Double depTime : hMatrix.keySet()) {
			for (Path p : hMatrix.get(depTime).keySet()) {
				Double pathTime = Double.valueOf(0);
				for (Link l : p) {
					pathTime += l.travelTimeEnter(depTime+pathTime);
				}
				totalTravelTime += pathTime * hMatrix.get(depTime).get(p);
			}
		}
		return totalTravelTime;
	}
	
	//the method returns AEC by analyzing the matrix provided using iterations.
	public Double AEC(
			Map<Double,Map<Path,Double>> currentH, 
			Map<Double,Map<Path,Double>> shortPathH, 
			Network net) {
		return (TTT(currentH)-TTT(shortPathH))/totalDemand(currentH);
	}
	
	//the method returns relative gap {1/2, 1/3, 1/4, 1/5, .......} by analyzing the matrix provided using iterations.
	public Double relativeGap(
			Map<Double,Map<Path,Double>> currentH, 
			Map<Double,Map<Path,Double>> shortPathH, 
			Network net) {
		return (TTT(currentH)-TTT(shortPathH))/TTT(shortPathH);
	}
}
