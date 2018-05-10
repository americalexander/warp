package edu.utexas.warp;

import java.util.HashMap;
import java.util.Map;

public class Demand {
	
	Map<Integer, Map<Integer, Double>> dynamicOD;
	
	public Demand(Map<Node, Map<Node, Double>> staticOD, Integer timeStep, Integer timeHorizon) {
		createDynamicOD(staticOD, timeStep, timeHorizon);
	}
	
	public static void createDynamicOD(Map<Node, Map<Node, Double>> staticOD, Integer timeStep, Integer timeHorizon) {
		
	}
	
}
