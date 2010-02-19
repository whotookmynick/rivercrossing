package evolutionary;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
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

	static BoardState[] bestCandidate;
	static FitnessResult maxFitness = new FitnessResult();//For debugging.

	private static final int SIZE_OF_POP = 150;
	public static final int SIZE_OF_SOLUTION = 14;
	private static final int NUMBER_OF_GENERATIONS = 1000;
	private static double SIZE_OF_GENOM;
	private static double PROB_OF_SPLICE = 1;

	private static double probOfMut = (1.0 / SIZE_OF_GENOM)*0.1;
	
	private static PrintStream _fileStream;

	public static Random rand = new Random();

	public static final PrintStream	origOut	= System.out;

	public static void evolutionaryRiverCrossing(Level level)
	{	
		SIZE_OF_GENOM = (level.getSizeOnePlanks() + level.getSizeTwoPlanks() + level.getSizeThreePlanks()) * SIZE_OF_SOLUTION;
		BoardState[][] population = generateRandomSolutions(level);
		BoardState[] solution = null;//getGoodSolution(population,solutionForLevel1,level);
		for (int i = 0; i <= NUMBER_OF_GENERATIONS & solution == null; i++) // This should be replaced with the end condition of the alg
		{
			GeneralAlg.redirectOutput("C:\\River\\allWinners.txt");
			System.out.println("GENERATION " + i);
			population = createNewPopulation(population, i);
			createMutation(population,i);
		}
//		redirectOutput("C:\\River\\LastGen.txt");
//		for (int i = 0;i < population.length;i++)
//		{
//			System.out.println("******************** Citizen number " + i);
//			for (int j = 0; j < population[i].length;j++)
//				population[i][j].print();
//		}
		new RealFitness().fitnessFunction(bestCandidate);
		redirectOutput("c:\\River\\bestCandidate.txt");
		System.out.println("The best candidate is with fitness: " + maxFitness);
		for (int i = 0; i < bestCandidate.length;i++) 
			bestCandidate[i].print();
	}

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
			Edge lastMove = level.findStartingEdge();
			for (int i = 1; i < SIZE_OF_SOLUTION; i++)
			{
//				BoardState currBoardState = generateNextRandomBoardState(ans[k][i-1]);
								RandomBoardStateResult rbsr = generateNextRandomBoardState(ans[k][i-1], lastMove);
								BoardState currBoardState = rbsr.bs;
								lastMove = rbsr.lastMove;
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

	private static RandomBoardStateResult generateNextRandomBoardState(BoardState previous,Edge lastEdgeToMove)
	{
		Level level = previous.getLevel();
		BoardState ans = new BoardState(level);
		for (int i = 0; i <3; i++)
		{
			int []originalEdges = previous.getChosenEdges(i);
			int []newEdges = Arrays.copyOf(originalEdges,originalEdges.length);
			ans.setChosenEdges(newEdges, i+1);
		}

		Vector<Edge> touchingPlanks = new Vector<Edge>();
		touchingPlanks.add(lastEdgeToMove);
		Vector<Edge> touchingEdges = previous.findAllTouchingEdges(touchingPlanks, new Vector<Edge>());
		int newEdgeRandIndex = rand.nextInt(touchingEdges.size());
		int originalEdgeRandIndex = rand.nextInt(touchingPlanks.size());
		Edge newEdge = touchingEdges.get(newEdgeRandIndex);
		Edge origEdge = touchingPlanks.get(originalEdgeRandIndex);
		while (newEdge.getSize() != origEdge.getSize())
		{
			newEdgeRandIndex = rand.nextInt(touchingEdges.size());
			originalEdgeRandIndex = rand.nextInt(touchingPlanks.size());
			newEdge = touchingEdges.get(newEdgeRandIndex);
			origEdge = touchingPlanks.get(originalEdgeRandIndex);	
		}
		HashMap<Edge,Integer> inverseMap = level.getInverseEdgeMap();
		int newEdgeIndex = inverseMap.get(newEdge);
		int origEdgeIndex = inverseMap.get(origEdge);

		int[] chosenPlanks = ans.getChosenEdges(origEdge.getSize() - 1);
		chosenPlanks[origEdgeIndex] = 0;
		chosenPlanks[newEdgeIndex] = 1;
		ans.setChosenEdges(chosenPlanks, origEdge.getSize());
		RandomBoardStateResult res = new RandomBoardStateResult();
		res.bs = ans;
		res.lastMove = newEdge;
		return res;
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

		int placeToStartSplice = rand.nextInt(SIZE_OF_SOLUTION);//(int)(Math.random() * SIZE_OF_SOLUTION);
		int placeToEndSplice = rand.nextInt(SIZE_OF_SOLUTION - placeToStartSplice) + placeToStartSplice;
		BoardState []ans = new BoardState[SIZE_OF_SOLUTION];
		for (int i = 0; i < placeToStartSplice; i++)
		{
			ans[i] = solve1[i];
		}
		for (int i = placeToStartSplice; i < placeToEndSplice; i++)
		{
			ans[i] = solve2[i];
		}

		for (int i = placeToEndSplice; i < SIZE_OF_SOLUTION; i++)
		{
			ans[i] = solve1[i];
		}
		return ans;

	}

	public static BoardState[][] createNewPopulation(BoardState[][] originalPop, int generationCounter)
	{
		Fitness fit = new RealFitness();
		BoardState[][] ans = new BoardState[SIZE_OF_POP][];
		int[] allFitness = new int[SIZE_OF_POP];
		int sumOfFitness = 0;
		for (int i=0; i < SIZE_OF_POP;i++)
		{
			FitnessResult fr = fit.fitnessFunction(originalPop[i]);
			int currFit = fr.total;
			originalPop[i][0].setFitness(currFit); // the first boardstate will hold fitness for the solution
			if (generationCounter % 10 == 0)
			{
				String outputFileName = "c:\\River\\"+
				"generation="+generationCounter+
				"_citizen="+i+
				"_fitness="+currFit+
				".txt";
				//				redirectOutput(outputFileName);		
				//				for (int k=0; k<SIZE_OF_SOLUTION; k++) {
				//					originalPop[i][k].print();
				//				}
				//				System.out.println(fr);
			}
			//			redirectOutput("C:\\River\\Fitness" + generationCounter);
			//			System.out.println(currFit);
			if (fr.legal == RealFitness.MAX_LEGALITY_GRADE)
			{
				redirectOutput("C:\\River\\allWinners.txt");
				if (fr.hasFinalEdge)
				{
					currFit = currFit * 3;
					fr.total = fr.total * 3;
					System.out.println("I HAVE A WINNER gen = " + generationCounter + " citizen = " + i);
					//				else
					//					System.out.println("I HAVE A PERFECT SPECIMIN gen = " + generationCounter + " citizen = " + i);
					System.out.println(fr);
					for (int ll=0; ll < originalPop[i].length;ll++)
						originalPop[i][ll].print();
					//return new BoardState[][]{originalPop[i]};
				}
			}
			allFitness[i] = currFit;
			sumOfFitness += currFit;
			if (currFit > maxFitness.total) {bestCandidate = originalPop[i];maxFitness=fr;}
		}
		redirectOutput("C:\\River\\AVGFITNESS.fit");
		System.out.println("AVG Fitness of " + generationCounter + " : " + ((double)sumOfFitness)/SIZE_OF_POP);

		for (int i=0; i < SIZE_OF_POP; i++)
		{
			BoardState[] newBaby = originalPop[i];
			double chanceForSplice = rand.nextDouble();
			if (chanceForSplice <= PROB_OF_SPLICE)
			{
				int randNum1 = rand.nextInt(sumOfFitness);//(int)(Math.random() * sumOfFitness);
				int randIndex1 = getIndexFromNum(randNum1,allFitness);
				int randNum2 = rand.nextInt(sumOfFitness);//(int)(Math.random() * sumOfFitness);
				int randIndex2 = getIndexFromNum(randNum2,allFitness);
				while (randIndex1 == randIndex2)
				{
					randNum2 = rand.nextInt(sumOfFitness+1);//(int)(Math.random() * sumOfFitness);
					randIndex2 = getIndexFromNum(randNum2,allFitness);
				}
				newBaby = spliceTwoSolutions(originalPop[randIndex1], originalPop[randIndex2]);
			}
			ans[i] = newBaby;
		}
		return ans;
	}

	private static int getIndexFromNum(int randNum, int[] allFitness) {
		int startPlace = 0;
		boolean found = false;
		for (int i = 0; i < allFitness.length & !found;i++)
		{
			if (randNum >= startPlace &  randNum <= allFitness[i]+startPlace)
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
	 * This function goes over the whole population and with probability of 1/SIZE_OF_GENOM
	 * creates a mutation which means to pick a random board and a random plank and move it randomly.
	 * @param origPop
	 */
	private static void createMutation(BoardState[][] origPop,int genCounter)
	{
		for (BoardState currCit[] : origPop)
		{
			double chanceOfMut = rand.nextDouble();
			if (chanceOfMut < probOfMut)
			{
				int randBoardIndex = rand.nextInt(currCit.length-1)+1;
				int randSizeOfPlank = rand.nextInt(3)+1;
				int []chosenPlanks = currCit[randBoardIndex].getChosenEdges(randSizeOfPlank-1);
				while (chosenPlanks.length <= 0 || !existsZero(chosenPlanks))
				{
					randSizeOfPlank = rand.nextInt(3)+1;
					chosenPlanks = currCit[randBoardIndex].getChosenEdges(randSizeOfPlank-1);
				}
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
				currCit[randBoardIndex].setChosenEdges(chosenPlanks, randSizeOfPlank);
			}
		}
	}

	public static void redirectOutput(String fileName) {
		try {
			_fileStream = new PrintStream(new FileOutputStream(fileName,true));
			System.setOut(_fileStream);
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}  
	}
}
