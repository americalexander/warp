package edu.utexas.warp;

public class Timestep implements Comparable<Timestep> {
	private Double start = null;
	private Double length = null;
	
	public Timestep(Double start, Double length) {
		this.start = start;
		this.length = length;
	}
	
	public String toString() {
		return this.start.toString();
	}

	@Override
	public int compareTo(Timestep arg0) {
		return this.start.compareTo(arg0.start);
	}
}
