package edu.utexas.warp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Network {
	private Set<Link> links;
	private Map<Integer, Node> nodes;
	
	public Network(Set<Link> links, Map<Integer, Node> nodes) {
		this.links = links;
		this.nodes = nodes;
	}
	//TODO create method for getting set of node objects
	
	public Set<Node> getNodes() {
		return new HashSet(nodes.values());
	}
	
	public Set<Link> getLinks() {
		return links;
	}
}
