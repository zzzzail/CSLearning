package com.java.dsa.struct;

import java.util.Arrays;

public class MGraphTest {
    
    public static void main(String[] args) {
        MGraph<Integer> g = new MGraph<>(10);
        System.out.println(g.getData());
        System.out.println(g.getData().size());
        
        int[][] og = {
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 0}
        };
        System.out.println(Arrays.deepToString(og));
    }
    
}
