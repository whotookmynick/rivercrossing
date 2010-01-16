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

	private static final int SIZE_OF_POP = 20;
	private static final int SIZE_OF_SOLUTION = 20;

	private static BoardState[] createMockSolution(Level level)
	{
		BoardState[] solutionForLevel1 = new BoardState[4];
		solutionForLevel1[0] = new BoardState(level);
		solutionForLevel1[0].setS1chosenEdges(new int[]{1,0});
		solutionForLevel1[0].setS2chosenEdges(new int[]{1,0,0});
		solutionForLevel1[0].setS3chosenEdges(new int[0]);

		solutionForLevel1[1] = new BoardState(level);
		solutionForLevel1[1].setS1chosenEdges(new int[]{1,0});
		solutionForLevel1[1].setS2chosenEdges(new int[]{0,1,0});
		solutionForLevel1[1].setS3chosenEdges(new int[0]);

		solutionForLevel1[2] = new BoardState(level);
		solutionForLevel1[2].setS1chosenEdges(new int[]{0,1});
		solutionForLevel1[2].setS2chosenEdges(new int[]{0,1,0});
		solutionForLevel1[2].setS3chosenEdges(new int[0]);

		solutionForLevel1[3] = new BoardState(level);
		solutionForLevel1[3].setS1chosenEdges(new int[]{0,1});
		solutionForLevel1[3].setS2chosenEdges(new int[]{0,0,1});
		solutionForLevel1[3].setS3chosenEdges(new int[0]);
		return solutionForLevel1;
	}

	public static void evolutionaryRiverCrossing(Level level)
	{
		BoardState[] solutionForLevel1 = createMockSolution(level);

		BoardState[][] population = generateRandomSolutions(level);
		BoardState[] solution = null;//getGoodSolution(population,solutionForLevel1,level);
		for (int i = 0; i < 10 & solution == null; i++) // This should be replaced with the end condition of the alg
		{
			population = createNewPopulation(population);
//			solution = getGoodSolution(population,solutionForLevel1,level);
		}
		for (int i = 0;i < population.length;i++)
		{
			System.out.println("******************** Citizen number " + i);
			for (int j = 0; j < population[i].length;j++)
			population[i][j].print();
		}
		if (solution != null)
		{
			System.out.println("Solution found");

		}
	}

	private static BoardState[] getGoodSolution(BoardState[][] population,
			BoardState[] solutionForLevel1, Level level) {

		for (int i = 0; i < population.length; i++)
		{
			boolean isGoodSolution = true;
			for (int j = 0; j < solutionForLevel1.length & isGoodSolution;j++)
			{
				isGoodSolution = isGoodSolution & population[i][j].equals(solutionForLevel1[j]);
			}
			if (isGoodSolution)
			{
				return population[i];
			}
		}
		return null;
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
			if (chosenPlanks.length > 0 && existsZero(chosenPlanks))
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

	private static boolean existsZero(int[] chosenPlanks) {
		for (int i = 0; i < chosenPlanks.length; i++)
		{
			if (chosenPlanks[i] == 0)
			{
				return true;
			}
		}
		return false;
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
}