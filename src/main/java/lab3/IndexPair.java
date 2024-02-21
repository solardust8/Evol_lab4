package lab3;

import java.util.Random;

class IndexPair {
    int smaller;
    int bigger;

    public IndexPair(int smaller, int bigger) {
        this.bigger = bigger;
        this.smaller = smaller;
    }

    static IndexPair getRandomIndexPair(int range, Random random) {
        int i = random.nextInt(range);

        int j = random.nextInt(range);

        while (j == i) {
            j = random.nextInt(range);
        }

        if (i < j) {
            return new IndexPair(i, j);
        }

        else {
            return new IndexPair(j, i);
        }


    }
}