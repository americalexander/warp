package edu.utexas.warp;

public abstract class Assigner {
	
	private Network network;
	private Demand demand;
	
	public Assigner(Network network, Demand demand) {
		this.network = network;
		this.demand = demand;
		
		//TODO multiple constructors for simulator?
	}
	
	
}