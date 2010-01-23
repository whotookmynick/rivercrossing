package evolutionary;

public class FitnessResult {

	/**
	 * This is a value class that is used to return the fitness and all "sub-fitnesses"
	 */
	
	public int total;
	public double legal;
	public double progress;
	public double repeat;
	public boolean hasFinalEdge; //This is mainly for debugging purposes
	
	public String toString()
	{
		 return "total = " + total + " legal = " + legal + " progress = " + progress + " repeat = " + repeat + " final edge = " + hasFinalEdge;
	}
}
