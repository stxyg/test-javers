package com.cnn.testjavers.dao;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cnn.testjavers.bean.Store;

/**
 * 注解：JaversSpringDataAuditable，提供保存到数据库
 * @author ningning.cheng
 * @date 2021/10/18
 **/
@Repository
@JaversSpringDataAuditable
public interface StoreRepository extends CrudRepository<Store, Integer> {
}
