package edu.utexas.warp;

import java.util.Map;

/**
 * @author William
 *
 */
public abstract class RouteChooser{
	//TODO: Talk about structure of Network and ODMatrix - can Network class from wrap be used?
	
	// This class should take in the current state of the network and the OD matrix
	// to determine the shortest paths from every origin to every destination, as
	// well as how much flow that shortest path serves. How the shortest path is 
	// determined is implementation-specific. We don't store the OD matrix locally,
	// as it may change. 
	//TODO: But then where does it change?
	
	public abstract Map<Path, Map<Timestep, Double>> Hstar(Network net, Demand od, TimeHorizon th);
	
}