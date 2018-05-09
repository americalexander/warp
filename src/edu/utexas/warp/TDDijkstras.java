package edu.utexas.warp;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class TDDijkstras extends RouteChooser{

	@Override
	public Map<Path, Map<Timestep, Double>> Hstar(Network net, Demand od, TimeHorizon th) {
		// TODO Auto-generated method stub
		Map<Path, Map<Timestep, Double>> hStar = new HashMap<Path, Map<Timestep, Double>>();
		for (Timestep ts : th) {
			for (Node origin : od.getOrigins()) {
				Set<Node> finalized = new HashSet<Node>();
				Map<Node, Double> lMap = new HashMap<Node, Double>();
				Map<Node, Link> backLink = new HashMap<Node, Link>();

				lMap.put(origin, Double.valueOf(0));
				
				while (true) {
					// THIS IS BAD AND SHOULD BE REPLACED WITH A FIBONNACI HEAP
					Node min = null; Double minCost = Double.MAX_VALUE;
					for (Node n : net.getNodes()) {
						Double d = lMap.getOrDefault(n, Double.MAX_VALUE);
						if (d < minCost){
							min = n; minCost = d;
						}
					}
					// END THE BAD STUFF
					
					if (min == null) break;
					finalized.add(min);
					for (Link ij : min.getOutgoing()) {
						if (lMap.getOrDefault(ij.getHead(), Double.MAX_VALUE) > minCost + ij.TravelTimeEnter(minCost)) {
							lMap.put(ij.getHead(), minCost + ij.TravelTimeEnter(minCost));
							backLink.put(ij.getHead(), ij);
							
						}
					}
				}
			}
		}
		return hStar;
	}
	
}