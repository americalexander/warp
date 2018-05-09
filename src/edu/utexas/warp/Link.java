package edu.utexas.warp;

public abstract class Link {
	
	private final Double jamdensity = null;
	private final Double capacity = null;
	private final Double ffspeed = null;
	private final Double length = null;
	
	private final Node head = null;
	private final Node tail = null;
	
	public abstract Double SendingFlow(Integer t);
	
	public abstract Double ReceivingFlow(Integer t);
	
	public abstract Double UpstreamCount(Double t);
	
	public abstract Double DownstreamCount(Double t);
	
	public abstract Double EnterTime(Double count);
	
	public abstract Double ExitTime(Double count);
	
	public abstract Double TravelTimeEnter(Double enterTime);
	
	public abstract Double TravelTimeExit(Double exitTime);
	
	public Node getHead() {
		return head;
	}
	
	public Node getTail() {
		return tail;
	}
}
