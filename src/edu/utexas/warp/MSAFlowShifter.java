package edu.utexas.warp;

import java.util.Map;

/**
 * @author William
 *
 */
public class MSAFlowShifter extends ConvexComboFlowShifter{
	private int i;
	
	public MSAFlowShifter(Map<Double, Map<Path, Double>> currentH) {
		super(currentH);
		i = 1;
		lambda = 1.0/i;
	}
	
	public MSAFlowShifter() {
		super();
		i = 1;
		lambda = 1.0/i;
	}
	
	/* (non-Javadoc)
	 * @see edu.utexas.warp.ConvexComboFlowShifter#updateLambda(java.lang.Double)
	 * 
	 * The MSA algorithm increases the denominator of the lambda function by one
	 * each iteration, i.e. 1/2, 1/3, 1/4, etc.
	 */
	@Override
	protected void updateLambda(Double lambda) {
		lambda = 1.0/(i++);
	}

	
}