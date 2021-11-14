package com.cnn.testjavers.demo.jql;

import java.util.List;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.springframework.util.Assert;

import com.cnn.testjavers.demo.pojo.Home;
import com.cnn.testjavers.demo.pojo.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShadowScope {
    public static void main(String[] args) {
        Javers javers = JaversBuilder.javers().build();
        Person lilei = new Person("lilei");
        Person lixiaolei = new Person("lixiaolei",lilei,new Home("bj"));

        javers.commit("ningning", lixiaolei);

        lixiaolei.setHome(new Home("sh"));
        javers.commit("ningning",lixiaolei);
        //根据对象查询，默认scope时shallow，浅的
        List<Shadow<Person>> shadows = javers.findShadows(QueryBuilder.byInstance(lixiaolei).build());

        log.info("shadows-shallow:{}",shadows);
        Assert.isTrue(shadows.size()==2,"shadow个数不对");
        Assert.isNull(shadows.get(0).get().getFather(),"引用类型在scope之外，应该为null");
        Assert.isTrue("sh".equals(shadows.get(0).get().getHome().getAddress()),"值对象总是在scope内");


        log.info("---------------------------------");
        //scope=child-value-object,子值对象
        List<Shadow<Person>> childValueObjShadows = javers.findShadows(QueryBuilder.byInstance(lixiaolei).
                withShadowScope(org.javers.repository.jql.ShadowScope.CHILD_VALUE_OBJECT).build());
        log.info("shadows-childvalueobj-:{}",childValueObjShadows);
        Assert.isTrue(childValueObjShadows.size()==2,"shadow个数不对");
        Assert.isTrue(childValueObjShadows.get(0).get().getFather().getName().equals("lilei"),"引用类型在scope之外，应该为null");
        Assert.isTrue("sh".equals(childValueObjShadows.get(0).get().getHome().getAddress()),"值对象总是在scope内");


    }
}
