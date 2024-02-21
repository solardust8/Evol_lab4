package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.Random;

public class TspSwapMutation extends TspMutation implements EvolutionaryOperator<TspSolution> {

    public TspSwapMutation(){
        super();
    }

    public TspSwapMutation(double probThreshold){
        super(probThreshold);
    }

    protected void applyForOneSolution(TspSolution solution, Random random) {
        IndexPair iPair = IndexPair.getRandomIndexPair(solution.getCitiesNum(), random);

        solution.swapIndexes(iPair.smaller, iPair.bigger);

//        solution.checkAllCitiesPresent();
    }
}