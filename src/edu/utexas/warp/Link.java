package edu.utexas.warp;

public abstract class Link {
	
	private Double jamdensity;
	private Double capacity;
	private Double ffspeed;
	private Double length;
	
	public Link() {
		
	}
	
	public abstract Double SendingFlow(Integer t);
	
	public abstract Double ReceivingFlow(Integer t);
	
	public abstract Double UpstreamCount(Double t);
	
	public abstract Double DownstreamCount(Double t);
	
	public abstract Double EnterTime(Double count);
	
	public abstract Double ExitTime(Double count);
	
	public abstract Double TravelTimeEnter(Double enterTime);
	
	public abstract Double TravelTimeExit(Double exitTime);
}
