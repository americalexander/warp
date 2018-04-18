package edu.utexas.warp;

import java.util.Map;

public abstract class FlowShifter{
	//TODO: Talk about Path type
	private Map<Path,Double> currentFlows;
	
	public FlowShifter(Map<Path,Double> initialFlows) {
		currentFlows = initialFlows;
	}
	
	public FlowShifter() {
		//TODO: Talk about how not having an initial solution is handled
	}
	
	// This class should take in an all-or-nothing assignment from the
	// route choice model and shift flows from the current paths to the
	// shortest paths. How much flow to shift and which paths to shift
	// onto are implementation-specific.
	// TODO: Talk about what's different if, instead of one, we have k shortest paths
	
	public abstract void shiftFlows(Map<Path,Double> allOrNothing);
}