package com.cnn.testjavers.demo;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

import com.cnn.testjavers.demo.simple.Cat;
import com.cnn.testjavers.demo.simple.Home;

import lombok.extern.slf4j.Slf4j;

/**
 * 值对象的比较
 */
@Slf4j
public class ValueDiffTest {
    public static void main(String[] args) {
        //javers实例
        Javers javers = JaversBuilder.javers().build();

        Cat xiaoHei = new Cat("xiaoHei", 30);
        Home catHome = new Home("home");
        xiaoHei.setCatHome(catHome);


        Cat xiaoBai = new Cat("xiaoBai", 40);
        Home catHome1 = new Home("home1");
        xiaoBai.setCatHome(catHome1);

        Diff diff = javers.compare(xiaoHei, xiaoBai);


        log.info(diff.prettyPrint());



    }
}
