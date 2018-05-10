package edu.utexas.warp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demand {
	
	Map<Integer, Map<Node, Map<Node, Double>>> dynamicOD;
	
	public Demand(Map<Integer, Map<Node, Map<Node, Double>>> dynamicOD) {
		this.dynamicOD = dynamicOD;
	}
	
	public static void createDynamicOD(Map<Node, Map<Node, Double>> staticOD, Integer timeStep, Integer timeHorizon) {
		List<Double> deptProps = new ArrayList<Double>();
		for(int i = 0; i < timeHorizon; i += timeStep) {
			
		}
	}
	
}
