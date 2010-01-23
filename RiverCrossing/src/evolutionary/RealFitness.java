package evolutionary;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import game.BoardState;
import game.Edge;
import game.Level;

/**
 * this class wraps the fitness function used to calculate the success
 * of a solution, for future-generation solution choosing.
 * @author AWARMAN
 *
 */
public class RealFitness implements Fitness{


	/**
	 * 4 parameters are used to calculate grade for solution:
	 * length - shorter solutions are preferred.
	 * legality - illegal solutions are allowed but receive lower grades.
	 * progress - solution should show movement from left to right.
	 * repetition - solution should be penalized for back-and-forth movement
	 **/

	private static final double INITIAL_UNCONTEXTED_LEGALITY = 100;
	private static final double INITIAL_CONTEXTED_LEGALITY = 100;
	public static final double PERFECT_LEGALITY = INITIAL_UNCONTEXTED_LEGALITY + INITIAL_CONTEXTED_LEGALITY;
	private static final int TOO_MUCH_MOVING_PLANKS_PENALTY = 10;
	private static final int IMPOSSIBLE_PLANK_MOVEMENT_PENALTY = 20;
	private static final int CROSSED_PLANKS_PENALTY = 10;
	private static final int PROGRESS_PENALTY = 10;
	private static final int REPETITION_PENALTY = 10;
	private static final double ACTIVE_PLANK_MOVED_BUT_OUT_OF_REACH_PENALTY = 10;
	private static final double NO_CHANGES_MADE_PENALTY = 10;
	private static final double MOVING_PLANK_WAS_NOT_ACTIVE_PENALTY = 20;
	
	/** 
	 * these constants define initial grades.
	 * initial value can be zero (and a solution will be rewarded for being good)
	 * and it can be initially high (and a solution will be penalized for being bad)
	 */
	private final double INITIAL_LENGTH_GRADE = 100;
	private final double INITIAL_LEGALITY_GRADE = 100;
	private final double INITIAL_STEP_LEGALITY_GRADE = 0;
	private final double INITIAL_PROGRESS_GRADE = 100;
	private final double INITIAL_REPETITION_GRADE = 100;
	
	/**
	 * different weights can be defined for each quality
	 */
	private static double _LEGALITY_WEIGHT = 1;
	private static double _LENGTH_WEIGHT = 0;
	private static double _PROGRESS_WEIGHT = 0;
	private static double _REPETITIONS_WEIGHT = 0;

	/**
	 * These next constants exist in order to be able to check if there is a winner in different classes.
	 */
	public static final double MAX_LEGALITY_GRADE = _LEGALITY_WEIGHT * (INITIAL_CONTEXTED_LEGALITY + INITIAL_UNCONTEXTED_LEGALITY);
	
	/**
	 * this is the main function of this class
	 */
	public FitnessResult fitnessFunction(BoardState[] sequence) {
		double legality = gradeLegality(sequence);
		double length = gradeLength(sequence);
		double progress = gradeProgress(sequence);
		double repeatition = gradeRepetition(sequence);
		double total = (legality * _LEGALITY_WEIGHT) +
		(length * _LENGTH_WEIGHT) +
		(progress * _PROGRESS_WEIGHT) +
		(repeatition * _REPETITIONS_WEIGHT);
		FitnessResult fr = new FitnessResult();
		fr.total = Math.max(1,(int)total);
		fr.legal = (legality * _LEGALITY_WEIGHT);
		fr.progress = (progress * _PROGRESS_WEIGHT);
		fr.repeat = (repeatition * _REPETITIONS_WEIGHT);
		return  fr;
	}

	/**
	 * measures repetition parameter
	 * @param sequence
	 * @return
	 */
	private double gradeRepetition(BoardState[] sequence) {
		int repititionVal = 0;
		Vector<Integer> indicesAlreadyFound = new Vector<Integer>();
		for (int i = 0; i < sequence.length; i++)
		{
			if (!indicesAlreadyFound.contains(i))
			{
				for (int j = i+1; j < sequence.length; j++)
				{
					if (sequence[i].equals(sequence[j]))
					{
						repititionVal += (1/(double)(j-i)) * REPETITION_PENALTY;
						indicesAlreadyFound.add(j);
					}
				}
			}
		}
//		GeneralAlg.origOut.println("the penalty for repetition is : " + repititionVal);
		return INITIAL_REPETITION_GRADE - repititionVal;
	}


	/**
	 * measures length parameter
	 * @param sequence
	 * @return
	 */
	private double gradeLength(BoardState[] sequence) {
		return INITIAL_LENGTH_GRADE - sequence.length;
	}

	/**
	 * measures progress parameter
	 * The progress is defined as the amount of progress done in each third of the sequence.
	 * e.g. If the sequence has moves in the first third of the sequence that constitutes moves in the
	 * second third of the board then that move is penalized.
	 * @param sequence
	 * @return
	 */
	private double gradeProgress(BoardState[] sequence) {
		Level level = sequence[0].getLevel();
		int penalizeVal = 0;
		for (int i = 0; i < sequence.length/3; i++)
		{
			for (int k = 0; k < 3; k++)
			{
				int[] chosenBefore = sequence[i].getChosenEdges(k);
				int[] chosenAfter = sequence[i+1].getChosenEdges(k);
				for (int l=0;l < chosenBefore.length; l++)
				{
					if (chosenAfter[l] == 1 & chosenBefore[l] == 0)
					{
						Edge different = level.getEdge(l, k);
						if (different.getX1() > 3)
						{
							penalizeVal += different.getX1() - 3;
						}
						if (different.getX2() > 3)
						{
							penalizeVal += different.getX2() - 3;
						}
					}
				}
			}
		}

		for (int i = sequence.length/3 + 1; i < sequence.length*2/3; i++)
		{
			for (int k = 0; k < 3; k++)
			{
				int[] chosenBefore = sequence[i].getChosenEdges(k);
				int[] chosenAfter = sequence[i+1].getChosenEdges(k);
				for (int l=0;l < chosenBefore.length; l++)
				{
					if (chosenAfter[l] == 1 & chosenBefore[l] == 0)
					{
						Edge different = level.getEdge(l, k);
						if (different.getX1() > 5)
						{
							penalizeVal += different.getX1() - 5;
						}
						if (different.getX1() <= 3)
						{
							penalizeVal += 4 - different.getX1();
						}
						if (different.getX2() > 5)
						{
							penalizeVal += different.getX2() - 5;
						}
						if (different.getX2() <= 3)
						{
							penalizeVal += 4 - different.getX2();
						}
					}
				}
			}
		}

		for (int i = sequence.length*2/3 + 1; i < sequence.length-1; i++)
		{
			for (int k = 0; k < 3; k++)
			{
				int[] chosenBefore = sequence[i].getChosenEdges(k);
				int[] chosenAfter = sequence[i+1].getChosenEdges(k);
				for (int l=0;l < chosenBefore.length; l++)
				{
					if (chosenAfter[l] == 1 & chosenBefore[l] == 0)
					{
						Edge different = level.getEdge(l, k);
						if (different.getX1() <= 5)
						{
							penalizeVal += 6 - different.getX1();
						}

						if (different.getX2() <= 5)
						{
							penalizeVal += 6 - different.getX2();
						}
					}
				}
			}
		}

		return INITIAL_PROGRESS_GRADE - (penalizeVal * PROGRESS_PENALTY);
	}

	/**
	 * measures legality
	 * @param sequence
	 * @return
	 */
	public double gradeLegality(BoardState[] sequence) {
		double contextedLegality = INITIAL_CONTEXTED_LEGALITY;
		double uncontextedLegality = INITIAL_UNCONTEXTED_LEGALITY;
		Vector<Edge> activeSet = sequence[0].findAllTouchingEdges(sequence[0].findStartingEdge());
		for (int i=0; i<=sequence.length-2; i++){
			if (sequence[i].equals(sequence[i+1])) {
				contextedLegality -= NO_CHANGES_MADE_PENALTY;
			}
			else {
				uncontextedLegality -= uncontextedPenalty(sequence[i],sequence[i+1]);		
				HashMap<Edge,Edge> activePlanksMoved = new HashMap<Edge,Edge>();
				for (Edge e : activeSet) {
					for (Edge e1 : sequence[i+1].getAllCurrentEdges()) {
						boolean isMoved = isPlankMoved(e,e1,sequence[i+1]);
						if (isMoved) {
							activePlanksMoved.put(e, e1);		
							boolean reachableMovement = false;
							Vector<Edge> reachableFromE = sequence[i].findAllTouchingEdges(e);
							for (Edge e2 : reachableFromE) {
								if (e2.isTouching(e1)) {
									reachableMovement = true;
								}
							}
							if ( ! reachableMovement) {
								contextedLegality -= ACTIVE_PLANK_MOVED_BUT_OUT_OF_REACH_PENALTY;
							}						
						}
					}
				}
				Edge chosenTarget = null;
				if (activePlanksMoved.isEmpty()) {
					contextedLegality -= MOVING_PLANK_WAS_NOT_ACTIVE_PENALTY;
					chosenTarget = chooseRandomMovement(sequence[i],sequence[i+1]);
				}
				else {
					chosenTarget = chooseActivePlankMovement(activePlanksMoved);
				}
				activeSet = sequence[i+1].findAllTouchingEdges(chosenTarget);
			}
		}
		if (contextedLegality+uncontextedLegality == INITIAL_CONTEXTED_LEGALITY + INITIAL_UNCONTEXTED_LEGALITY)
		{
			System.out.println("I HAVE A WINNER");
		}
		return uncontextedLegality + contextedLegality;
	}

	private Edge chooseRandomMovement(BoardState from, BoardState to) {
		// for better selection we should prefer "from" edge to be empty but as for now we ignore it
		Random generator = new Random();
		Object[] values = to.getAllCurrentEdges().toArray();
		int len = values.length;
		if (len > 0) {
			Object randomValue = values[generator.nextInt(len)];
			return (Edge)randomValue;
		}
		else {
			return null;
		}
	}

	private Edge chooseActivePlankMovement(HashMap<Edge, Edge> possibleMovements) {
		Random generator = new Random();
		Object[] values = possibleMovements.values().toArray();
		Object randomValue = values[generator.nextInt(values.length)];
		return (Edge)randomValue;
	}

	private boolean isPlankMoved(Edge from, Edge to, BoardState boardState) {
		boolean hasFromPlank = boardState.hasPlankOn(from);
		boolean hasToPlank = boardState.hasPlankOn(to);
		return ((from.getSize() == to.getSize()) &&
				( ! hasFromPlank) && (hasToPlank));
	}

	public double uncontextedPenalty(BoardState from, BoardState to) {
		int[] penalty1 = {0,0,0};
		int[] penalty2 = {0,0,0};
		int penalty3 = 0;
		for (int plankSize = 0; plankSize <= 2; plankSize++) {
			Vector<Integer> diffSet = new Vector<Integer>();
			for (int i=0; i<to.getChosenEdges(plankSize).length; i++) {
				if ((to.getChosenEdges(plankSize)[i] == 1) && (from.getChosenEdges(plankSize)[i] == 0)) {
					diffSet.add(i);
				}
			}
			if (diffSet.size() > 0)
			{
				penalty1[plankSize] = (diffSet.size() - 1) * TOO_MUCH_MOVING_PLANKS_PENALTY;
			}
			for (int i : diffSet) {
				if ( ! from.canReachEdge(i, plankSize)) {
					penalty2[plankSize] += IMPOSSIBLE_PLANK_MOVEMENT_PENALTY;
				}
			}		
		}
		if (to.hasCrossedPlanks()) {
			penalty3 = CROSSED_PLANKS_PENALTY;
		}
		double totalPenalty = penalty1[0] + penalty1[1] + penalty1[2] +
						   penalty2[0] + penalty2[1] + penalty2[2] +
						   penalty3;
		return totalPenalty;
	}





}
