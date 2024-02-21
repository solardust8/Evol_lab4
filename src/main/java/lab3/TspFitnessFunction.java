package lab3;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.ArrayList;
import java.util.List;

public class TspFitnessFunction implements FitnessEvaluator<TspSolution> {

    private DistanceGetter distanceGetter = null;

    public TspFitnessFunction(String pathToTsp) {
        distanceGetter = new DistanceGetter(pathToTsp);
    }

    public double getFitness(TspSolution solution, List<? extends TspSolution> list) {
        ArrayList<Integer> route = solution.getRoute();

        double totalDistance = 0.0D;

        for(int cur_i = 1; cur_i < route.size(); cur_i++){
            int prev_i = cur_i - 1;
            totalDistance += distanceGetter.getDistance(route.get(prev_i), route.get(cur_i));
        }
        return totalDistance;
    }

    // If we aim to minimize fitness, isNatural should return false.
    public boolean isNatural() {
        return false;
    }
}
