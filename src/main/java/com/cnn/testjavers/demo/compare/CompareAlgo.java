package com.cnn.testjavers.demo.compare;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.ListCompareAlgorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompareAlgo {
    public static void main(String[] args) {
        Javers javers = JaversBuilder.javers().withListCompareAlgorithm(ListCompareAlgorithm.SIMPLE).build();

    }
}
