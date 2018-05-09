package edu.utexas.warp;

import java.util.Map;

public abstract class Link {
	
	protected final Double jamdensity;
	protected final Double capacity;
	protected final Double ffspeed;
	protected final Double length;
	protected final Double stepSpan;
	
	protected final Double freeFlowTime;
	protected final Double backwardWaveTime;
	
	protected final Node head;
	protected final Node tail;
	
	private Map<Double, Map<Path, Double>> upstreamCount;
	private Map<Double, Map<Path, Double>> downstreamCount;
	
	protected Link(
			Double freeFlowSpeed, 
			Double backwardWaveSpeed,
			Double jamDensity, 
			Double length, 
			Double stepSpan, 
			Double capacity,
			Node head,
			Node tail) {
		this.head = head;
		this.tail = tail;
		this.capacity = capacity * stepSpan / 3600; 			//convert from vehicles/hour to vehicles/Double
		this.length = length * 5280; 							//convert from miles to feet
		this.jamdensity = jamDensity / 5280; 					//convert from vehicles/mile to vehicles/foot
		this.ffspeed = freeFlowSpeed * 5280 / 3600 * stepSpan;	//convert from mph to feet/Double
		this.stepSpan = stepSpan;
		
		this.freeFlowTime = length/freeFlowSpeed;
		this.backwardWaveTime = length/backwardWaveSpeed;
		
		// TODO Auto-generated constructor stub
	}

	public abstract Double SendingFlow(Double t);
	
	public abstract Double ReceivingFlow(Double t);
	
	public Double UpstreamCount(Double t) {
		Double sum = Double.valueOf(0);
		for (Double v : upstreamCount.get(t).values()) {
			sum += v;
		}
		return sum;
	}
	
	public Double DownstreamCount(Double t) {
		Double sum = Double.valueOf(0);
		for (Double v : downstreamCount.get(t).values()) {
			sum += v;
		}
		return sum;
	}
	
	public Double EnterTime(Double count) {
		// TODO Auto-generated method stub
		return null;
	}

	public Double ExitTime(Double count) {
		// TODO Auto-generated method stub
		return null;
	}

	public Double TravelTimeEnter(Double enterTime) {
		return Double.max(freeFlowTime, ExitTime(UpstreamCount(enterTime))-enterTime);
	}

	public Double TravelTimeExit(Double exitTime) {
		return Double.max(freeFlowTime, exitTime - EnterTime(DownstreamCount(exitTime)));
	}
	
	public Node getHead() {
		return head;
	}
	
	public Node getTail() {
		return tail;
	}
}
