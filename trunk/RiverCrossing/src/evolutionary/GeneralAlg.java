package evolutionary;

import java.util.Vector;

import game.Edge;
import game.Level;

/**
 * @author NARAD
 *
 */
public class GeneralAlg {

	private static final int SIZE_OF_POP = 50;
	
	public static void evolutionaryRiverCrossing(Level level)
	{
		int[][][] stumps = generateRandomSolutions(level);
	}

	/**
	 * Not finished yet.
	 * @param level
	 * @return
	 */
	private static int[][][] generateRandomSolutions(Level level) {
		int ans[][][] = new int[SIZE_OF_POP][][];
		Vector<Edge> sizeOnePlaces =level.findPossibleEdges(level.getStumps(), 0);
		Vector<Edge> sizeTwoPlaces =level.findPossibleEdges(level.getStumps(), 1);
		Vector<Edge> sizeThreePlaces =level.findPossibleEdges(level.getStumps(), 2);
		
		Vector<Integer> planksForOne = new Vector<Integer>(level.getSizeOnePlanks());
		for (int i = 0; i < level.getSizeOnePlanks(); i++)
		{
			int randPlank = (int)(Math.random() * level.getSizeOnePlanks()+1);
			while (planksForOne.contains(randPlank))
			{
				randPlank = (int)(Math.random() * level.getSizeOnePlanks()+1);
			}
			planksForOne.add(randPlank);
		}

		return ans;
	}
}
