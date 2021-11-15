package com.cnn.testjavers.controller;

import java.util.List;

import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnn.testjavers.bean.Store;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ningning.cheng
 * @date 2021/10/18
 **/
@Slf4j
@RestController
public class TestController {
    @Autowired
    private Javers javers;

    @GetMapping("/stores/snapshots")
    public String getStoresSnapshots() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Store.class);
        List<CdoSnapshot> snapshots = this.javers.findSnapshots(jqlQuery.build());
        return this.javers.getJsonConverter().toJson(snapshots);
    }

    @GetMapping("/stores/change")
    public String change() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Store.class);
        Changes snapshots = this.javers.findChanges(jqlQuery.build());
        Changes changes = this.javers.findChanges(jqlQuery.limit(1).build());
        log.info(changes.devPrint());
//        log.info(snapshots.devPrint());
        return snapshots.prettyPrint();
    }

}
