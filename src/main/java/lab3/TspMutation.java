package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class TspMutation implements EvolutionaryOperator<TspSolution> {

    protected double probThreshold;

    public TspMutation(double probThreshold) {
        this.probThreshold = probThreshold;
    }

    public TspMutation() {
        this.probThreshold = 0.5D;
    }

    abstract protected void applyForOneSolution(TspSolution solution, Random random);

    public List<TspSolution> apply(List<TspSolution> population, Random random) {
        ArrayList<TspSolution> newPopulation = new ArrayList<TspSolution>(population.size());

        for (int i=0; i < population.size(); ++i) {
            TspSolution solution =  population.get(i);
            if (random.nextDouble() < probThreshold) {
                TspSolution mutatedSolution = new TspSolution(solution);
                applyForOneSolution(mutatedSolution, random);
                newPopulation.add(mutatedSolution);
            }
            else {
                newPopulation.add(solution);
            }
        }
        return population;
    }
}