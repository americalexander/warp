package edu.utexas.warp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author William
 *
 */
public abstract class ConvexComboFlowShifter extends FlowShifter{
	protected Double lambda;
	
	protected ConvexComboFlowShifter(Map<Path,Double> currentH) {
		super(currentH);
	}
	
	protected ConvexComboFlowShifter() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see edu.utexas.warp.FlowShifter#shiftFlows(java.util.Map)
	 * 
	 * This class updates the path flow matrix H using the method
	 * of convex combinations, as described by Eq. 12.20 in the 
	 * Boyles textbook: H := kH* + (1-k)H
	 * 
	 * @param hStar a mapping between the shortest paths and the amount of OD demand
	 */
	@Override
	public void shiftFlows(Map<Path, Double> hStar) {
		// TODO Auto-generated method stub
		if (currentH == null) {
			currentH = hStar;
			return;
		}
		
		Map<Path, Double> h = new HashMap<Path, Double>();
		
		Set<Path> active = currentH.keySet();
		active.addAll(hStar.keySet());
		
		updateLambda(lambda);
		
		for (Path p : active) {
			Double newVal = lambda*hStar.getOrDefault(p, 0.0)
					+ (1-lambda)*currentH.getOrDefault(p, 0.0);
			h.put(p, newVal);
		}
		currentH = h;
		
	}
	
	protected abstract void updateLambda(Double lambda);
	
}