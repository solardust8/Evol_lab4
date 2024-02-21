package lab3;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.*;

public class TspCrossover extends AbstractCrossover<TspSolution> {
    protected TspCrossover(double crossOverProbability) {
        super(1, new Probability(crossOverProbability));
    }

    private TspSolution orderedCrossover(TspSolution p1, TspSolution p2, int start, int end) {
        ArrayList<Integer> route1 = p1.getRoute();
        ArrayList<Integer> route2 = p2.getRoute();

        ArrayList<Integer> childRoute = new ArrayList<Integer>(route1);


        Set<Integer> already_used_city_indexes_set = new HashSet<Integer>();
        for (int i = start; i < end + 1; i++) {
            already_used_city_indexes_set.add(route1.get(i));
        }

        int route_len = route1.size();

        int r2end = (end + 1) % route_len;
        int child_i, r2i;
        r2i = child_i = r2end;

        do{
            int r2v = route2.get(r2i);
            if (!already_used_city_indexes_set.contains(r2v)) {
                childRoute.set(child_i, r2v);
                child_i = (child_i + 1) % route_len;
            }
            r2i = (r2i + 1) % route_len;
        } while (r2i != r2end);

        return new TspSolution(childRoute);

    }

    private TspSolution orderedCrossover(TspSolution p1, TspSolution p2, Random random) {
        int route_len = p1.getCitiesNum();

        int start = random.nextInt(route_len);
        int end = random.nextInt(route_len);
        while(end == start) {
            end = random.nextInt(route_len);
        }

        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        return orderedCrossover(p1,  p2, start,  end);
    }

    protected List<TspSolution> mate(TspSolution p1, TspSolution p2, int crossoverPointsNum, Random random) {
//        return dummy_mate(p1, p2, crossoverPointsNum, random);

        ArrayList children = new ArrayList();

        TspSolution child1 = orderedCrossover(p1, p2, random);
        TspSolution child2 = orderedCrossover(p2, p1, random);

//        child1.checkAllCitiesPresent();
//        child2.checkAllCitiesPresent();

        children.add(child1);
        children.add(child2);

        return children;
    }

    private List<TspSolution> dummy_mate(TspSolution p1, TspSolution p2, int crossoverPointsNum, Random random) {
        ArrayList children = new ArrayList();
        // ! не знаю, легально ли использовать именно p1 и p2 или необхдимо сделать их копии
        children.add(p1);
        children.add(p2);
        return children;
    }
}
