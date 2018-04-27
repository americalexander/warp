package edu.utexas.warp;

import java.io.File;

public class Project{

	public Assigner assigner;
	public Network network;
	
	public Project(File netFile, File demandFile) {
		//TODO create data structure to hold network and demand before creating objects
		assigner = new Assigner(); 
		network = new Network();
	}
	
	public Assigner buildAssigner() {
		Assigner assign = new Assigner();
		return assign;
		//TODO do we want this to return an assigner or set the project's assigner?
	}
	
	public void readNetworkFile(File netFile) {
		
	}
	
	public void readDemandFile(File demandFile) {
		
	}
	
	
}