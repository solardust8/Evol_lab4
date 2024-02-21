package lab3;

import java.util.ArrayList;
import java.util.Collections;

public class TspSolution {
    private ArrayList<Integer> route;

    private static int citiesNum;

    protected TspSolution(int citiesNum) {
        /**
         * Initialize Route attribute with an ArrayList
         * that contains a random permutation
         * of integers in range [1, cities_num]
         */

        this.citiesNum = citiesNum;

        route = new ArrayList<Integer>();

        for(int i = 0; i < citiesNum; i++) {
            route.add(i);
        }

        Collections.shuffle(route);
    }


    public TspSolution(TspSolution s) {
        this.citiesNum = s.citiesNum;
        this.route = new ArrayList<Integer>(s.route);
    }


    protected TspSolution(ArrayList<Integer> route) {
        this.route = route;
    }

//        public void setRoute() {
//
//    }

    public ArrayList<Integer> getRoute() {
        return route;
    }

    public void swapIndexes(int i, int j) {
        int i_val = route.get(i);
        route.set(i, route.get(j));
        route.set(j, i_val);
    }

    public void insertIAfterJ(int i, int j) {
        route.add(j, route.get(i));
        if (i < j) {
            route.remove(i);
        }
        else {
            route.remove(i+1);
        }
    }

    public void invertRegion(int start, int end) {
        int[] invertedSubArray = getInvertedSubArray(start, end);
        for (int i = start; i <= end; i++) {
            route.set(i, invertedSubArray[i-start]);
        }
    }


    public void scrambleRegion(int start, int end) {
        ArrayList<Integer> subArray = getSubArray(start, end);
        Collections.shuffle(subArray);

        for (int i = start; i <= end; i++) {
            route.set(i, subArray.get(i - start));
        }
    }

    private ArrayList<Integer> getSubArray(int start, int end) {
        ArrayList<Integer> subArray =  new ArrayList<Integer>();
        for (int i = start; i <= end; i++) {
            subArray.add(route.get(i));
        }
        return subArray;
    }

    private int[] getInvertedSubArray(int start, int end) {
        int subArrayLen = end-start+1;
        int[] invertedSubArray = new int[subArrayLen];
        for (int i = 0; i < subArrayLen; i++) {
            invertedSubArray[subArrayLen-1-i] = route.get(start + i);
        }
        return invertedSubArray;
    }


    @Override
    public String toString() {
        String representation = "";

        for(int i = 0; i < route.size(); i++) {
            representation += route.get(i) + " ";
        }

        return representation;
    }

    public int getCitiesNum() {
        return route.size();
    }


    public void checkAllCitiesPresent() {
        if (route.size() != citiesNum)
            throw new AssertionError("Number of cities (" + route.size() + ") is different from it's initial value!");

        boolean[] cityEncountered = new boolean[citiesNum];
        for (int i =0; i < citiesNum; i++) {
            cityEncountered[i] = false;
        }

        for (int i = 0; i < citiesNum; i++) {
            int cityIndex = route.get(i);
            if (cityEncountered[cityIndex]) {
                throw new AssertionError("City " + cityIndex + " is present at least twice in the solution");
            }
            else {
                cityEncountered[cityIndex] = true;
            }
        }
    }




}
