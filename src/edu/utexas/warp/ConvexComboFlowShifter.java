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
	
	protected ConvexComboFlowShifter(Map<Double, Map<Path, Double>> currentH) {
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
	 * This class doesn't yet handle having multiple lambdas
	 * 
	 * @param hStar a mapping between the shortest paths and the amount of OD demand
	 */
	@Override
	public void shiftFlows(Map<Double, Map<Path, Double>> hStar) {
		// TODO Auto-generated method stub
		if (currentH == null) {
			currentH = hStar;
			return;
		}
		Set<Double> tsteps = hStar.keySet();
		tsteps.addAll(currentH.keySet());
		
		Map<Double,Map<Path,Double>> H = new HashMap<Double, Map<Path,Double>>();
		
		for (Double tstep : tsteps) {
			Map<Path, Double> h = new HashMap<Path, Double>();
			
			Set<Path> active = currentH.get(tstep).keySet();
			active.addAll(hStar.get(tstep).keySet());
			
			updateLambda(lambda);
			
			for (Path p : active) {
				Double newVal = lambda*hStar.getOrDefault(tstep,new HashMap<Path,Double>()).getOrDefault(p, 0.0)
						+ (1-lambda)*currentH.getOrDefault(tstep,new HashMap<Path,Double>()).getOrDefault(p, 0.0);
				h.put(p, newVal);
			}
			H.put(tstep, h);
		}
		currentH = H;
		
	}
	
	protected abstract void updateLambda(Double lambda);
	
}