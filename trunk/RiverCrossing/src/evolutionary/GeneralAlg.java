package evolutionary;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import game.BoardState;
import game.Edge;
import game.Level;

/**
 * @author NARAD
 *
 */
public class GeneralAlg {

	private static final int SIZE_OF_POP = 50;
	private static final int SIZE_OF_SOLUTION = 20;

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
//	public static BoardState[][] generateRandomSolutions(Level level) {
//		BoardState [][]ans = new BoardState[SIZE_OF_POP][SIZE_OF_SOLUTION];
//		for (int k = 0; k < SIZE_OF_POP; k++)
//		{
//			ans[k][0] = level.getInitialState();
//			for (int i = 1; i < SIZE_OF_SOLUTION; i++)
//			{
//				BoardState currBoardState = new BoardState(level);
//				int []sizeOneBoardState = createRandomPlankPositions(0, level.getSizeOnePlanks(), level,level.getSizeOneEdges().size()); 
//				currBoardState.setS1chosenEdges(sizeOneBoardState);
//				int []sizeTwoBoardState = createRandomPlankPositions(1, level.getSizeTwoPlanks(), level,level.getSizeTwoEdges().size()); 
//				currBoardState.setS2chosenEdges(sizeTwoBoardState);
//				int []sizeThreeBoardState = createRandomPlankPositions(2, level.getSizeThreePlanks(), level,level.getSizeThreeEdges().size()); 
//				currBoardState.setS3chosenEdges(sizeThreeBoardState);
//				currBoardState.initHorEdgeMat();
//				currBoardState.initVerEdgeMat();
//				ans[k][i] = currBoardState;
//			}
//		}
//		return ans;
//	}

//	private static int[] createRandomPlankPositions(int sizeOfPlanks,int numOfPlanks,Level level,int randFactor)
//	{
//		Vector<Edge> possiblePlaces =level.findPossibleEdges(level.getStumps(), sizeOfPlanks);
//		Vector<Integer> randomPlanks = new Vector<Integer>(numOfPlanks);
//		for (int i = 0; i < numOfPlanks; i++)
//		{
//			int randPlank = (int)(Math.random() * randFactor +1);
//			while (randomPlanks.contains(randPlank))
//			{
//				randPlank = (int)(Math.random() * randFactor +1);
//			}
//			randomPlanks.add(randPlank);
//		}
//		int []randomBoardState = new int[possiblePlaces.size()];
//		for (int i = 0; i < randomBoardState.length; i++)
//		{
//			randomBoardState[i] = 0;
//		}
//		for (int curr : randomPlanks)
//		{
//			randomBoardState[curr-1] = 1;
//		}
//		return randomBoardState;
//	}

	/**
	 * This method creates random Board-States that have the right number of planks
	 * from each size. The number of board states created is in the number of the
	 * population.
	 * This function creates each step in the level to have exactly one plank different from the previous
	 * boardstate.
	 * @param level
	 * @return
	 */
	public static BoardState[][] generateRandomSolutions(Level level) {
		BoardState [][]ans = new BoardState[SIZE_OF_POP][SIZE_OF_SOLUTION];
		for (int k = 0; k < SIZE_OF_POP; k++)
		{
			ans[k][0] = level.getInitialState();
			for (int i = 1; i < SIZE_OF_SOLUTION; i++)
			{
				BoardState currBoardState = generateNextRandomBoardState(ans[k][i-1]);
				ans[k][i] = currBoardState;
			}
		}
		return ans;
	}
	
	private static BoardState generateNextRandomBoardState(BoardState previous)
	{
		BoardState ans = new BoardState(previous.getLevel());
		for (int i = 0; i <3; i++)
		{
			int []originalEdges = previous.getChosenEdges(i);
			int []newEdges = Arrays.copyOf(originalEdges,originalEdges.length);
			ans.setChosenEdges(newEdges, i+1);
		}
		boolean createdNewRandomPlank = false;
		Random rand = new Random();
		while (!createdNewRandomPlank)
		{
			int randSizeOfPlank = rand.nextInt(3)+1;
			int []chosenPlanks = ans.getChosenEdges(randSizeOfPlank-1);
			if (chosenPlanks.length > 0)
			{
				createdNewRandomPlank = true;
				int numberOfEdges = chosenPlanks.length;
				int newRandomPlank = rand.nextInt(numberOfEdges);
				while (chosenPlanks[newRandomPlank] == 1)
				{
					newRandomPlank = rand.nextInt(numberOfEdges);
				}
				int plankToReplace = rand.nextInt(numberOfEdges);
				while (chosenPlanks[plankToReplace] == 0)
				{
					plankToReplace = rand.nextInt(numberOfEdges);
				}
				chosenPlanks[plankToReplace] = 0;
				chosenPlanks[newRandomPlank] = 1;
				ans.setChosenEdges(chosenPlanks,randSizeOfPlank);
			}
		}
		return ans;
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

	public static BoardState[][] createNewPopulation(BoardState[][] originalPop)
	{
		Fitness fit = new RealFitness();
		BoardState[][] ans = new BoardState[SIZE_OF_POP][];
		int[] allFitness = new int[SIZE_OF_POP];
		int sumOfFitness = 0;
		for (int i=0; i < SIZE_OF_POP;i++)
		{
			int currFit = fit.fitnessFunction(originalPop[i]);
			System.out.println("Fitness is "+currFit);
			allFitness[i] = currFit;
			sumOfFitness += currFit;
		}

		//change this to be in a loop
		for (int i=0; i < SIZE_OF_POP; i++)
		{
			int randNum1 = (int)(Math.random() * sumOfFitness);
			int randIndex1 = getIndexFromNum(randNum1,allFitness);
			int randNum2 = (int)(Math.random() * sumOfFitness);
			int randIndex2 = getIndexFromNum(randNum2,allFitness);
			while (randIndex1 == randIndex2)
			{
				randNum2 = (int)(Math.random() * sumOfFitness);
				randIndex2 = getIndexFromNum(randNum2,allFitness);
			}
			BoardState[] newBaby = spliceTwoSolutions(originalPop[randIndex1], originalPop[randIndex2]);
			ans[i] = newBaby;
		}
		return ans;
	}

	private static int getIndexFromNum(int randNum, int[] allFitness) {
		int startPlace = 0;
		boolean found = false;
		for (int i = 0; i < allFitness.length & !found;i++)
		{
			if (randNum >= startPlace &  randNum < allFitness[i]+startPlace)
			{
				found = true;
				return i;
			}
			else
			{
				startPlace += allFitness[i];
			}
		}
		return -1; // not found that means that the data isn't good.
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