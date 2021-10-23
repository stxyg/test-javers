package com.cnn.testjavers.controller;

import java.util.List;

import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnn.testjavers.bean.Store;

/**
 * @author ningning.cheng
 * @date 2021/10/18
 **/
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
}
