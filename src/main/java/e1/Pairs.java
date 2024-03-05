package e1;

import java.util.Random;

public class Pairs {
    static Pair<Integer, Integer> getIntegerRandomPair(int bond){
        var random = new Random();
        return new Pair<Integer, Integer>(random.nextInt(bond), random.nextInt(bond));
    }
}
