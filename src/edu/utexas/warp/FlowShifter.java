package edu.utexas.warp;

import java.util.Map;

/**
 * @author William
 *
 */
public abstract class FlowShifter{
	//TODO: Talk about Path type
	protected Map<Path,Double> currentH;
	
	protected FlowShifter(Map<Path,Double> initialFlows) {
		currentH = initialFlows;
	}
	
	protected FlowShifter() {currentH = null;}
	

	// TODO: Talk about what's different if, instead of one, we have k shortest paths
	
	/**
	 * This class takes in an all-or-nothing assignment from the
	 * route choice model and shift flows from the current Paths to the
	 * shortest paths. How much flow to shift and which paths to shift
	 * onto are implementation-specific.
	 * 
	 * @param allOrNothing a mapping from the shortest Path between an origin
	 * and a destination to the amount (Double) of flow on that destination
	 */
	public abstract void shiftFlows(Map<Path,Double> hStar);
}