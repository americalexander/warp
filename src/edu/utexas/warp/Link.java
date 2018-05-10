package edu.utexas.warp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author William
 *
 */
public abstract class Link {

	protected final Double jamDensity;
	protected final Double downstreamCapacity;
	protected final Double upstreamCapacity;
	protected final Double freeFlowSpeed;
	protected final Double length;
	protected final Double stepSpan;

	protected final Double criticalDensity;
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
			Double criticalDensity,
			Double length, 
			Double stepSpan, 
			Double downstreamCapacity,
			Double upstreamCapacity,
			Node head,
			Node tail) {
		this.head = head;
		this.tail = tail;
		this.downstreamCapacity = downstreamCapacity * stepSpan / 3600; //convert from vehicles/hour to vehicles/timestep
		this.upstreamCapacity = upstreamCapacity * stepSpan / 3600;
		this.length = length * 5280; 							//convert from miles to feet
		this.jamDensity = jamDensity / 5280; 					//convert from vehicles/mile to vehicles/foot
		this.criticalDensity = criticalDensity / 5280;
		this.freeFlowSpeed = freeFlowSpeed * 5280 / 3600 * stepSpan;	//convert from mph to feet/Double
		this.stepSpan = stepSpan;

		this.freeFlowTime = length/freeFlowSpeed;
		this.backwardWaveTime = length/backwardWaveSpeed;

	}

	public Double sendingFlow(Double t) {
		Double min = downstreamCapacity;
		for (Double u : uncongestedTheta(t)) {
			min = Double.min(
					upstreamCount(t + stepSpan - (length / gamma(u))) - downstreamCount(t)
					+ Double.max(0.0, uncongestedDeltaK(u))*length, min);
		}
		return min;
	}

	public Double receivingFlow(Double t) {
		Double min = upstreamCapacity;
		for (Double v : congestedTheta(t)) {
			min = Double.min(downstreamCount(t + stepSpan + (length / omega(v))) 
					- upstreamCount(t) + jamDensity*length
					+ congestedDeltaK(v)*length, min);
		}
		return min;
	}

	private Set<Double> uncongestedTheta(Double t) {
		// TODO Auto-generated method stub
		return null;
	}

	private Set<Double> congestedTheta(Double t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Double upstreamCount(Double t) {
		Double sum = Double.valueOf(0);
		for (Double v : upstreamCount.get(t).values()) {
			sum += v;
		}
		return sum;
	}

	public Double downstreamCount(Double t) {
		Double sum = Double.valueOf(0);
		for (Double v : downstreamCount.get(t).values()) {
			sum += v;
		}
		return sum;
	}

	public Double enterTime(Double count) {
		// TODO Auto-generated method stub
		return null;
	}

	public Double exitTime(Double count) {
		// TODO Auto-generated method stub
		return null;
	}

	public Double travelTimeEnter(Double enterTime) {
		return Double.max(freeFlowTime, exitTime(upstreamCount(enterTime))-enterTime);
	}

	public Double travelTimeExit(Double exitTime) {
		return Double.max(freeFlowTime, exitTime - enterTime(downstreamCount(exitTime)));
	}

	public Node getHead() {
		return head;
	}

	public Node getTail() {
		return tail;
	}

	private Double uncongestedDeltaK(Double u) {
		return (u / gamma(u)) - uncongestedPhiInverse(u);
	}

	private Double congestedDeltaK(Double v) {
		return congestedPhiInverse(v) - (v / omega(v)) - jamDensity;
	}

	protected abstract Double omega(Double v);

	protected abstract Double gamma(Double u);

	protected abstract Double uncongestedPhiInverse(Double u);

	protected abstract Double congestedPhiInverse(Double v);

	protected abstract Double phi(Double k);
	
	public void reset() {
		upstreamCount = new HashMap<Double,Map<Path,Double>>();
		downstreamCount = new HashMap<Double,Map<Path,Double>>();
	}
}
