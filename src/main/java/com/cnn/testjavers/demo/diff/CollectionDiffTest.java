package com.cnn.testjavers.demo;

import java.util.List;

import org.javers.common.collections.Lists;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

import com.cnn.testjavers.demo.simple.Dog;

import lombok.extern.slf4j.Slf4j;

/**
 * 集合比对
 */
@Slf4j
public class CollectionDiffTest {
    public static void main(String[] args) {
        Javers javers = JaversBuilder.javers().build();
        List<Dog> lucy = Lists.asList(new Dog("lucy", 12));
        List<Dog> lucy1 = Lists.asList(new Dog("lucy", 12));
        List<Dog> lily = Lists.asList(new Dog("lily", 13));

        Diff diff = javers.compare(lucy, lily);
        log.info(diff.prettyPrint());

        Diff diff1 = javers.compare(lucy, lucy1);
        log.info(diff1.prettyPrint());



        Diff diff2 = javers.compareCollections(lucy, lily, Dog.class);

        log.info(diff2.prettyPrint());

    }
}
