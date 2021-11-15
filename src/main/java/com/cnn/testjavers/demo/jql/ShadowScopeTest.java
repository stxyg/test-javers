package com.cnn.testjavers.demo.jql;

import java.util.List;

import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.repository.jql.QueryBuilder;
import org.javers.repository.jql.ShadowScope;
import org.javers.shadow.Shadow;
import org.springframework.util.Assert;

import com.cnn.testjavers.demo.pojo.Home;
import com.cnn.testjavers.demo.pojo.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShadowScopeTest {
    public static void main(String[] args) {
        Javers javers = JaversBuilder.javers().build();
        Person bigLilei = new Person("bigLilei");
        Person lilei = new Person("lilei",bigLilei);
        Person lixiaolei = new Person("lixiaolei",lilei,new Home("bj"));


        javers.commit("ningning", lixiaolei);  //commit lixiaolei和lilei

        lixiaolei.setHome(new Home("sh"));
        javers.commit("ningning",lixiaolei);

        Changes all = javers.findChanges(QueryBuilder.anyDomainObject().build());

        log.info("all:{}",all);

        //根据对象查询，默认scope时shallow，浅的
        List<Shadow<Person>> shadows = javers.findShadows(QueryBuilder.byInstance(lixiaolei).build());

        log.info("shadows-shallow:{}",shadows);
        Assert.isTrue(shadows.size()==2,"shadow个数不对");
        Assert.isNull(shadows.get(0).get().getFather(),"引用Entry在scope之外，应该为null");
        Assert.isTrue("sh".equals(shadows.get(0).get().getHome().getAddress()),"值对象总是在scope内");


        log.info("---------------------------------");
        //scope=child-value-object,子值对象
        List<Shadow<Person>> childValueObjShadows = javers.findShadows(QueryBuilder.byInstance(lixiaolei).
                withShadowScope(ShadowScope.CHILD_VALUE_OBJECT).build());
        log.info("shadows-childvalueobj-:{}",childValueObjShadows);
        Assert.isNull(childValueObjShadows.get(0).get().getFather(),"引用Entry在scope之外，应该为null");
        Assert.isTrue("sh".equals(childValueObjShadows.get(0).get().getHome().getAddress()),"值对象总是在scope内");

        log.info("---------------------------------");
        //scope=withScopeCommitDeep
        List<Shadow<Person>> commitDeepShadows = javers.findShadows(QueryBuilder.byInstance(lixiaolei).withScopeCommitDeep()
               .build());
        log.info("shadows-commitDeepShadows-:{}",commitDeepShadows);
        Assert.isTrue("lilei".equals(commitDeepShadows.get(0).get().getFather().getName()),"引用类型在scope之外，应该为null");
        Assert.isTrue("sh".equals(commitDeepShadows.get(0).get().getHome().getAddress()),"值对象总是在scope内");

        log.info("---------------------------------");
        //scope=withScopeDeepPlus
        List<Shadow<Person>> deepPlusShadows = javers.findShadows(QueryBuilder.byInstance(lixiaolei).withScopeDeepPlus()
                .build());
        log.info("shadows-deepPlusShadows-:{}",deepPlusShadows);
        Assert.isTrue("bigLilei".equals(deepPlusShadows.get(0).get().getFather().getFather().getName()),"引用类型在scope之外，应该为null");
        Assert.isTrue("sh".equals(deepPlusShadows.get(0).get().getHome().getAddress()),"值对象总是在scope内");




    }
}
