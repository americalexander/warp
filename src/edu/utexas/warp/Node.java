package edu.utexas.warp;

import java.util.ArrayList;
import java.util.Set;

public abstract class Node {
	private Integer ID;
	
	public Node(Integer ID) {
		this.ID = ID;
	}
	
	private final Set<Link> outgoing = null;
	private final Set<Link> incoming = null;
	
	public Set<Link> getOutgoing(){
		return outgoing;
	}
	
	public Set<Link> getIncoming(){
		return incoming;
	}
	
	public Integer getID() {
		return ID;
	}
	
	public void addOutgoingLink(Link link) {
		outgoing.add(link);
	}
	
	public void addIncomingLink(Link link) {
		incoming.add(link);
	}
}