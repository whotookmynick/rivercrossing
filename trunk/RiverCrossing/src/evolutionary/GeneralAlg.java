package evolutionary;

import java.util.Arrays;
import java.util.Vector;

import game.BoardState;
import game.Edge;
import game.Level;

/**
 * @author NARAD
 *
 */
public class GeneralAlg {

	private static final int SIZE_OF_POP = 3;
	private static final int SIZE_OF_SOLUTION = 3;

	public static void evolutionaryRiverCrossing(Level level)
	{
		BoardState[][] population = generateRandomSolutions(level);
		
		spliceTwoSolutions(population[0], population[1]);
	}

	/**
	 * This method creates random Board-States that have the right number of planks
	 * from each size. The number of board states created is in the number of the
	 * population.
	 * @param level
	 * @return
	 */
	public static BoardState[][] generateRandomSolutions(Level level) {
		BoardState [][]ans = new BoardState[SIZE_OF_POP][SIZE_OF_SOLUTION];
		for (int k = 0; k < SIZE_OF_POP; k++)
		{
			for (int i = 0; i < SIZE_OF_SOLUTION; i++)
			{
				BoardState currBoardState = new BoardState(level);
				int []sizeOneBoardState = createRandomPlankPositions(0, level.getSizeOnePlanks(), level,level.getSizeOneEdges().size()); 
				currBoardState.setS1chosenEdges(sizeOneBoardState);
				int []sizeTwoBoardState = createRandomPlankPositions(1, level.getSizeTwoPlanks(), level,level.getSizeTwoEdges().size()); 
				currBoardState.setS2chosenEdges(sizeTwoBoardState);
				int []sizeThreeBoardState = createRandomPlankPositions(2, level.getSizeThreePlanks(), level,level.getSizeThreeEdges().size()); 
				currBoardState.setS3chosenEdges(sizeThreeBoardState);
				currBoardState.initHorEdgeMat();
				currBoardState.initVerEdgeMat();
				ans[k][i] = currBoardState;
			}
		}
		return ans;
	}

	private static int[] createRandomPlankPositions(int sizeOfPlanks,int numOfPlanks,Level level,int randFactor)
	{
		Vector<Edge> possiblePlaces =level.findPossibleEdges(level.getStumps(), sizeOfPlanks);
		Vector<Integer> randomPlanks = new Vector<Integer>(numOfPlanks);
		for (int i = 0; i < numOfPlanks; i++)
		{
			int randPlank = (int)(Math.random() * randFactor +1);
			while (randomPlanks.contains(randPlank))
			{
				randPlank = (int)(Math.random() * randFactor +1);
			}
			randomPlanks.add(randPlank);
		}
		int []randomBoardState = new int[possiblePlaces.size()];
		for (int i = 0; i < randomBoardState.length; i++)
		{
			randomBoardState[i] = 0;
		}
		for (int curr : randomPlanks)
		{
			randomBoardState[curr-1] = 1;
		}
		return randomBoardState;
	}

	private static BoardState[] spliceTwoSolutions(BoardState[] solve1,BoardState[] solve2)
	{
		int placeToSplice = (int)(Math.random() * SIZE_OF_SOLUTION);
		BoardState []ans = new BoardState[SIZE_OF_SOLUTION];
		for (int i = 0; i < placeToSplice; i++)
		{
			ans[i] = solve1[i];
		}
		for (int i = placeToSplice; i < SIZE_OF_SOLUTION; i++)
		{
			ans[i] = solve2[i];
		}
		return ans;

	}
	
	/**
	 * I need to find according to before where are the stumps that can have planks
	 * in after. I will assume that more than one change is allowed.
	 * In the fitnes function less steps per board change should be preffered.
	 * @param before
	 * @param afterStep
	 * @return
	 */
	private static boolean checkLegalStep(BoardState before,BoardState afterStep)
	{
		boolean foundOneLegalChange = false;
		boolean moreThanOneChange = false;
		return foundOneLegalChange ^ moreThanOneChange;
	}
	
	private static Vector<Integer> legalPlacesToPutPlank(int plankLength,BoardState currState)
	{
		Vector<Integer> ans = new Vector<Integer>();
		currState.getChosenEdges(plankLength);

		
		return ans;
	}
}
