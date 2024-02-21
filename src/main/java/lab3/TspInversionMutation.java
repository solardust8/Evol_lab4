package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.Random;

public class TspInversionMutation extends TspMutation implements EvolutionaryOperator<TspSolution> {

    public TspInversionMutation(){
        super();
    }

    public TspInversionMutation(double probThreshold){
        super(probThreshold);
    }

    protected void applyForOneSolution(TspSolution solution, Random random) {
        IndexPair iPair = IndexPair.getRandomIndexPair(solution.getCitiesNum(), random);

        solution.invertRegion(iPair.smaller, iPair.bigger);

//        solution.checkAllCitiesPresent();
    }
}
