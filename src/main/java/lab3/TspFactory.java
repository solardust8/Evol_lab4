package lab3;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

public class TspFactory extends AbstractCandidateFactory<TspSolution> {

    private int cities_num;

    public TspFactory(int cities_num) {
        this.cities_num = cities_num;
    }

    public TspSolution generateRandomCandidate(Random random) {
        TspSolution solution = new TspSolution(cities_num);
        //your implementation

        return solution;
    }

}

