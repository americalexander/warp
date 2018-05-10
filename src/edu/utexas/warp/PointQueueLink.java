package edu.utexas.warp;

public class PointQueueLink extends Link {
	
	public PointQueueLink(
			Double freeFlowSpeed,
			Double jamDensity, 
			Double criticalDensity,
			Double length, 
			Double stepSpan,
			Double downstreamCapacity,
			Node head,
			Node tail) {
		this(freeFlowSpeed, jamDensity, criticalDensity, length, stepSpan, downstreamCapacity, downstreamCapacity, head, tail);
	}
	
	public PointQueueLink(
			Double freeFlowSpeed,
			Double jamDensity, 
			Double criticalDensity,
			Double length, 
			Double stepSpan,
			Double downstreamCapacity,
			Double upstreamCapacity,
			Node head,
			Node tail) {
		super(freeFlowSpeed, Double.MIN_VALUE, jamDensity, criticalDensity, length, stepSpan, downstreamCapacity, upstreamCapacity, head, tail);

	}

	@Override
	protected Double omega(Double v) {
		return Double.valueOf(0);
	}

	@Override
	protected Double gamma(Double u) {
		return freeFlowSpeed;
	}

	@Override
	protected Double uncongestedPhiInverse(Double u) {
		return u/gamma(u);
	}

	@Override
	protected Double congestedPhiInverse(Double v) {
		return criticalDensity;
	}

	@Override
	protected Double phi(Double k) {
		return Double.min(k * freeFlowSpeed, downstreamCapacity);
	}
	


}
