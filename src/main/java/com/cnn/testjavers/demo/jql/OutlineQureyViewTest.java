package com.cnn.testjavers.demo;

import java.util.List;

import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;

import com.cnn.testjavers.demo.pojo.Dog;
import com.cnn.testjavers.demo.pojo.Home;

import lombok.extern.slf4j.Slf4j;

/**
 * 三种查询视图
 */
@Slf4j
public class OutlineQureyViewTest {
    private static final Javers javers;
    static {
        //1 shadows
        javers = JaversBuilder.javers().build();
        //2 提交
        Dog jimi = new Dog("jimi", 3);
        javers.commit("ningning",jimi);

        //3 修改提交
        jimi.setAge(5);
        jimi.setHome(new Home("bj"));
        javers.commit("ningning",jimi);
    }
    public static void main(String[] args) {

        shadows();
        snapshots();
        changes();

    }

    private static void changes() {
        Changes changes = javers.findChanges(QueryBuilder.anyDomainObject().build());
        log.info("changes:{}", changes);
    }

    private static void snapshots() {
        List<CdoSnapshot> snapshots = javers.findSnapshots(QueryBuilder.anyDomainObject().build());
        log.info("snapshots:{}",snapshots);
    }

    /**
     * shadow
     */
    private static void shadows() {


        //1查询shadows
        List<Shadow<Object>> shadows = javers.findShadows(QueryBuilder.anyDomainObject().build());
        //2 展示shadows
//        shadows.forEach(shadow->);
        log.info("shadows：{}",shadows);
    }
}
