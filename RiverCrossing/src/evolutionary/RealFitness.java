package evolutionary;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;

import game.BoardState;

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
	 * repeativity - solution should be penalized for back-and-forth movement
	 **/

	private static final double INITIAL_UNCONTEXTED_LEGALITY = 0;
	private static final double INITIAL_CONTEXTED_LEGALITY = 0;
	private static final double ILLEGAL_MOVEMENT_PENALTY = 0;
	private static final double PERFECT_LEGALITY = INITIAL_UNCONTEXTED_LEGALITY + INITIAL_CONTEXTED_LEGALITY;
	private static final int TOO_MUCH_MOVING_PLANKS_PENALTY = 0;
	private static final int IMPOSSIBLE_PLANK_MOVEMENT_PENALTY = 0;
	 /** 
	 * these constants define initial grades.
	 * initial value can be zero (and a solution will be rewarded for being good)
	 * and it can be initially high (and a solution will be penalized for being bad)
	 */
	private final double INITIAL_LENGTH_GRADE = 100;
	private final double INITIAL_LEGALITY_GRADE = 0;
	private final double INITIAL_STEP_LEGALITY_GRADE = 0;
	private final double INITIAL_PROGRESS_GRADE = 0;
	private final double INITIAL_REPEATIVITY_GRADE = 0;
		
	/**
	 * different weights can be defined for each quality
	 */
	private double _legalityWeight = 0.25;
	private double _lengthWeight = 0.25;
	private double _progressWeight = 0.25;
	private double _repeativityWeight = 0.25;
	
	/**
	 * this is the main function of this class
	 */
	public int fitnessFunction(BoardState[] sequence) {
		double legality = gradeLegality(sequence);
		double length = gradeLength(sequence);
		double advancement = gradeProgress(sequence);
		double repeativity = gradeRepeativity(sequence);
		double total = legality * _legalityWeight +
					   length * _lengthWeight +
					   advancement * _progressWeight +
					   repeativity * _repeativityWeight;
		return (int)total;
	}

	/**
	 * measures repeativity parameter
	 * @param sequence
	 * @return
	 */
	private double gradeRepeativity(BoardState[] sequence) {
		// TODO Auto-generated method stub
		return 0;
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
	 * @param sequence
	 * @return
	 */
	private double gradeProgress(BoardState[] sequence) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * measures legality
	 * @param sequence
	 * @return
	 */
	private double gradeLegality(BoardState[] sequence) {
		double contextedLegality = INITIAL_CONTEXTED_LEGALITY;
		double uncontextedLegality = INITIAL_UNCONTEXTED_LEGALITY;
		int[] activeSet = findInitialActiveSet(sequence[0]);
		for (int i=0; i<=sequence.length-2; i++){
			uncontextedLegality -= uncontextedPenalty(sequence[i],sequence[i+1]);
			int p = findMovingPlank(sequence[i],sequence[i+1]);
			if (!contains(activeSet,p)) {
				contextedLegality -= ILLEGAL_MOVEMENT_PENALTY;
			}
			activeSet = findNextActiveSet(sequence[i], sequence[i+1]);		
		}
		return uncontextedLegality + contextedLegality;
	}
	

	private int[] findNextActiveSet(BoardState boardState,
			BoardState boardState2) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean contains(int[] activeSet, int p) {
		// TODO Auto-generated method stub
		return false;
	}

	private int[] findInitialActiveSet(BoardState boardState) {
//		for (int i : boardState.getLevel().get)
		return null;
	}

	private int[] findActiveSet(BoardState boardState, BoardState boardState2) {
		// TODO Auto-generated method stub
		return null;
	}

	private int findMovingPlank(BoardState boardState, BoardState boardState2) {
		// TODO Auto-generated method stub
		return 0;
	}

	private double uncontextedPenalty(BoardState from, BoardState to) {
		int[] penalty1 = {0,0,0};
		int[] penalty2 = {0,0,0};
		for (int plankSize = 0; plankSize <= 2; plankSize++) {
			Vector<Integer> diffSet = new Vector<Integer>();
			for (int i=0; i<to.getChosenEdges(plankSize).length; i++) {
				if ((to.getChosenEdges(plankSize)[i] == 1) && (from.getChosenEdges(plankSize)[i] == 0)) {
					diffSet.add(i);
				}
			}
			penalty1[plankSize] = (diffSet.size() - 1) * TOO_MUCH_MOVING_PLANKS_PENALTY;
			for (int i : diffSet) {
				if (!from.canReachEdge(i, plankSize)) {
					penalty2[plankSize] += IMPOSSIBLE_PLANK_MOVEMENT_PENALTY;
				}
			}		
		}

		return penalty1[0] + penalty1[1] + penalty2[2] +
			   penalty2[0] + penalty2[1] + penalty2[2];
	}




	
}
