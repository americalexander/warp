package edu.utexas.warp;

import java.util.Set;

public abstract class Node {
	private final Set<Link> outgoing = null;
	private final Set<Link> incoming = null;
	
	public Set<Link> getOutgoing(){
		return outgoing;
	}
	
	public Set<Link> getIncoming(){
		return incoming;
	}
}