package edu.utexas.warp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import edu.utexas.wrap.Link;
import edu.utexas.wrap.Network;
import edu.utexas.wrap.Node;
import edu.utexas.wrap.Origin;

public class Project{

	public Assigner assigner;
	public Network network;
	public Demand demand;
	private Set<Link> links;
	private Map<Integer, Node> nodes;
	private Map<Node, Map<Node, Double>> staticOD;
	
	public Project(File netFile, File demandFile, Integer timeStep, Integer timeHorizon) {
		//TODO create data structure to hold network and demand before creating objects
		readNetworkFile(netFile);
		readDemandFile(demandFile);
		network = new Network(links, nodes);
		demand = new Demand(staticOD, timeStep, timeHorizon);
		assigner = new Assigner(network, demand);

	}
	
	public Assigner buildAssigner() {
		Assigner assign = new Assigner();
		return assign;
		//TODO do we want this to return an assigner or set the project's assigner?
	}
	
	public void readNetworkFile(File netFile) throws IOException {
		//TODO actually read in network file and return the filled data structure
		//TODO Reference alg. B code to make this method more efficient/not trash
	
		// Open the files for reading
		BufferedReader lf = new BufferedReader(new FileReader(netFile));
		
		//TODO read in a new nodes.txt file which specifies nodeID and type (IDEA: create a mapping of int nodeID to int type from the nodes.txt file) 
		Integer nodetype = null;
		
		//////////////////////////////////////////////
		// Read links and build corresponding nodes
		//////////////////////////////////////////////
		String line;
		do { //Move past headers in the file
			line = lf.readLine();
		} while (!line.startsWith("~"));
		
		while (true) { //Iterate through each link (row)
			line = lf.readLine();
			if (line == null) break;	// End of link list reached
			if (line.startsWith("~") || line.trim().equals("")) continue;
			line = line.trim();
			String[] cols 	= line.split("\\s+");
			Integer tail 	= Integer.parseInt(cols[0]);
			Integer head 	= Integer.parseInt(cols[1]);
			Double capacity 	= Double.parseDouble(cols[2]);
			Double length 	= Double.parseDouble(cols[3]);
			Double fftime 	= Double.parseDouble(cols[4]);
			Double B 		= Double.parseDouble(cols[5]);
			Double power 	= Double.parseDouble(cols[6]);
			Double speedlim	= Double.parseDouble(cols[7]);
			Double toll		= Double.parseDouble(cols[7]);
			Integer type		= Integer.parseInt(cols[9]);
			
			//Create new node(s) if new, then add to map
			if (!nodes.containsKey(tail)) {
				//TODO read in a new nodes.txt file which specifies nodeID and type
				this.nodes.put(tail, createNode(tail, nodetype));
			}
			if (!nodes.containsKey(head)) {
				this.nodes.put(head, createNode(head, nodetype));
			}
			
			//Construct new link and add to the set
			Link link = createLink(nodes.get(tail), nodes.get(head), capacity, length, fftime, B, power, type);
			nodes.get(tail).addOutgoingLink(link);
			nodes.get(head).addIncomingLink(link);
			this.links.add(link);
		}
		lf.close();
	}
	
	public void readDemandFile(File demandFile) throws IOException {
		BufferedReader of = new BufferedReader(new FileReader(demandFile));

		/////////////////////////////////////
		// Read OD Matrix and assign flows
		/////////////////////////////////////
		String line;
		do { // Move past headers in the file
			line = of.readLine();
		} while (!line.startsWith("Origin"));

		while (true) { // While more Origins to read
			Integer origID = Integer.parseInt(line.trim().split("\\s+")[1]);
			Node origin = nodes.get(origID); // Retrieve the existing node with that ID
			Map<Node, Double> dests = new HashMap<Node, Double>();
			String[] entries;

			while (true) {
				line = of.readLine();
				if (line.trim().startsWith("O") || line.trim().equals(""))
					break; // If we've reached the gap, move to the next origin
				entries = line.trim().split(";");

				for (String entry : entries) { // For each entry on this line
					String[] cols = entry.split(":"); // Get its values
					Integer destID = Integer.parseInt(cols[0].trim());
					Node destination = nodes.get(destID);
					Double demand = Double.parseDouble(cols[1].trim());
					dests.put(destination, demand);
				}
			}
			
			staticOD.put(origin, dests);

			while (line != null && !line.startsWith("O"))
				line = of.readLine(); // Read in the origin header
			if (line == null || line.trim().equals(""))
				break; // If the origin header is empty, we've reached the end of the list
		}
		of.close();		
	}
	
	public Link createLink(Node tail, Node head, Double capacity, Double length, Double fftime, Double B, Double power, Integer type) {
		switch (type) {
			case 1: return new PointQueueLink(tail, head, capacity, length, fftime, B, power, type);
			case 100: return new CentroidLink(tail, head, capacity, length, fftime, B, power, type);
			
			default: break;
		}
	}
	
	public Node createNode(Integer ID, Integer type) {
		switch (type) {
			case 1: return new MergeNode(ID, type);
			case 2: return new DivergeNode(ID, type);
			case 100: return new CentroidNode(ID, type);
			
			default: break;
		}
	}
	
	
}