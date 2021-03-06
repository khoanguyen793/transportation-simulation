package transportation_simulation.environment;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.vividsolutions.jts.geom.Coordinate;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import transportation_simulation.agent.IAgent;
import transportation_simulation.exceptions.NoIdentifierException;;

public class Building implements FixedGeography, Identified {
	
	private static Logger LOGGER = Logger.getLogger(Building.class.getName());
	
	/** TODO: Change to coordinate system */
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	
	/** A list of agents who live here */
	private List<IAgent> agents;
	
	/**
	 * A unique identifier for buildings, usually set from the 'identifier' column in a shapefile
	 */
	private String identifier;
	
	/**
	 * The coordinates of the Building. This is also stored by the projection that contains this Building but it is
	 * useful to have it here too. As they will never change (buildings don't move) we don't need to worry about keeping
	 * them in sync with the projection.
	 */
	private Coordinate coords;

	public Building() {
		this.agents = new ArrayList<IAgent>();
	}
	
	/** TODO: Change to coordinate system */
	public Building(ContinuousSpace<Object> space, Grid<Object> grid) {
		this.agents = new ArrayList<IAgent>();
		this.space = space;
		this.grid = grid;
	}
	
	@Override
	public Coordinate getCoords() {
		return this.coords;
	}
	
	@Override
	public void setCoords(Coordinate c) {
		this.coords = c;
	}
	
	/** TODO: Change to coordinate system */
	public void setSpaceandGrid(ContinuousSpace<Object> space, Grid<Object> grid) {
		this.space = space;
		this.grid = grid;
	}
	
	/** TODO: Change to coordinate system */
	public ContinuousSpace<Object> getSpace() {
		return space;
	}
	
	/** TODO: Change to coordinate system */
	public Grid<Object> getGrid() {
		return grid;
	}
	
	public String getIdentifier() throws NoIdentifierException {
		if (this.identifier == null) {
			throw new NoIdentifierException("This building has no identifier. This can happen "
					+ "when roads are not initialised correctly (e.g. there is no attribute "
					+ "called 'identifier' present in the shapefile used to create this Road)");
		} else {
			return identifier;
		}
	}
	
	public void setIdentifier(String id) {
		this.identifier = id;
	}
	
	public void addAgent(IAgent a) {
		this.agents.add(a);
	}
	
	public void removeAgent(IAgent a) {
		this.agents.remove(a);
	}
	
	public List<IAgent> getAgents() {
		return this.agents;
	}

	@Override
	public String toString() {
		return "building: " + this.identifier;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Building))
			return false;
		Building b = (Building) obj;
		return this.identifier.equals(b.identifier);
	}
	
	public int countAgents() {
		if (agents != null) {
			return agents.size();
		}
		return 0;
	}
	
	/**
	 * Returns the hash code of this <code>Building</code>'s identifier string. 
	 */
	@Override
	public int hashCode() {
		if (this.identifier==null) {
			LOGGER.severe("hashCode called but this object's identifier has not been set. It is likely that you're " +
					"reading a shapefile that doesn't have a string column called 'identifier'");
		}
		return this.identifier.hashCode();
	}
	 
	
}