package edu.utexas.warp;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class TDDijkstras extends RouteChooser{

	protected TDDijkstras(Network net, Double th, Double stepSpan) {
		super(net, th, stepSpan);
	}

	@Override
	public Map<Path, Map<Double, Double>> Hstar(Demand od) {
		// TODO Auto-generated method stub
		Map<Path, Map<Double, Double>> hStar = new HashMap<Path, Map<Double, Double>>();
		for (Double ts = Double.valueOf(0); ts < th; ts += stepSpan) {
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
						if (lMap.getOrDefault(ij.getHead(), Double.MAX_VALUE) > minCost + ij.travelTimeEnter(minCost)) {
							lMap.put(ij.getHead(), minCost + ij.travelTimeEnter(minCost));
							backLink.put(ij.getHead(), ij);
						}
					}
				}
			}
		}
		return hStar;
	}
}