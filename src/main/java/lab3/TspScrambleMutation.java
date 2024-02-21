package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TspScrambleMutation extends TspMutation implements EvolutionaryOperator<TspSolution> {

    public TspScrambleMutation(){
        super();
    }

    public TspScrambleMutation(double probThreshold){
        super(probThreshold);
    }

    protected void applyForOneSolution(TspSolution solution, Random random) {
        IndexPair iPair = IndexPair.getRandomIndexPair(solution.getCitiesNum(), random);

        solution.scrambleRegion(iPair.smaller, iPair.bigger);

        solution.checkAllCitiesPresent();
    }
}