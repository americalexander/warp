package edu.utexas.warp;

import java.util.Map;

/**
 * @author William
 *
 */
public abstract class RouteChooser{
	protected final Network net;
	protected final Double th;
	protected final Double stepSpan;
	
	// This class should take in the current state of the network and the OD matrix
	// to determine the shortest paths from every origin to every destination, as
	// well as how much flow that shortest path serves. How the shortest path is 
	// determined is implementation-specific. We don't store the OD matrix locally,
	// as it may change. 
	//TODO: But then where does it change?
	protected RouteChooser(Network net, Double th, Double stepSpan) {
		this.net = net;
		this.th = th;
		this.stepSpan = stepSpan;
	}
	public abstract Map<Path, Map<Double, Double>> Hstar(Demand od);
	
}