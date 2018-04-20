package edu.utexas.warp;

import java.util.Set;
import java.util.Map;

public abstract class NetworkLoader{
	//TODO: Talk propagate altering environment variable travelTimes vs returning travelTimes Map
	private Map<Path,Double> travelTimes;

	// This class should take in a path flow assignment from the flow shifter
	// to determine the travel time on all used paths.

	public abstract void propagate(Map<Path,Double> pathFlows);

}
