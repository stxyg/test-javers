package com.cnn.testjavers.simple;

import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.repository.jql.QueryBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * 直接比较2个对象，即可得到变更
 */

@Slf4j
public class SimpleTest {
    public static void main(String[] args) {
        //javers实例
        Javers javers = JaversBuilder.javers().build();
        //领域对象
        Cat cat1 = new Cat("gengxin",24);
        Cat cat2 = new Cat("gengxin2",26);

        //比较(默认类名作为globalId)
        Diff diff = javers.compare(cat1, cat2);
        log.info("差异：{}",diff.prettyPrint());

        //审计
        //第一次提交
        javers.commit("ningning", cat1);
        //第二次提交
        cat1.setAge(30);
        javers.commit("ningning", cat1);
        //变更
        Changes changes = javers.findChanges(QueryBuilder.anyDomainObject().build());

        log.info(changes.prettyPrint());

        //自动审计的化结合注解实现

    }
}
