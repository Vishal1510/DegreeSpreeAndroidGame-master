package com.jvn.degreespree.Utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by vishal on 11/11/15.
 */
public class RandomPlayer {
    private static ArrayList<String> names;
    private static String[] n = {"Nhut","Vishal","John"};
    static {
        reset();
    }

    public static void reset() {
        names = new ArrayList<String>();
        for(int i = 0; i < n.length; i++) {
            names.add(n[i]);
        }
        Collections.shuffle(names);
    }

    // This class is super fragile
    public static String getName() {
        String name = names.get(0);
        names.remove(0);
        return name;
    }
}
