package edu.utexas.warp;

public class PointQueueLink extends Link {
	private final Double downstreamCapacity;
	private final Double upstreamCapacity;
	
	public PointQueueLink(
			Double freeFlowSpeed,
			Double jamDensity, 
			Double length, 
			Double stepSpan,
			Double downstreamCapacity,
			Node head,
			Node tail) {
		this(freeFlowSpeed, jamDensity, length, stepSpan, downstreamCapacity, downstreamCapacity, head, tail);
	}
	
	public PointQueueLink(
			Double freeFlowSpeed,
			Double jamDensity, 
			Double length, 
			Double stepSpan,
			Double downstreamCapacity,
			Double upstreamCapacity,
			Node head,
			Node tail) {
		super(freeFlowSpeed, Double.MIN_VALUE, jamDensity, length, stepSpan, downstreamCapacity, head, tail);
		this.downstreamCapacity = this.capacity;
		this.upstreamCapacity = upstreamCapacity * stepSpan / 3600; //convert from vehicles/hour to vehicles/timestep

	}
	
	@Override
	public Double SendingFlow(Double t) {
		return Double.min(UpstreamCount(t+stepSpan-freeFlowTime)-DownstreamCount(t), downstreamCapacity);
	}

	@Override
	public Double ReceivingFlow(Double t) {
		return upstreamCapacity;
	}


}
