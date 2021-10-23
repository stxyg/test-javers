package com.cnn.testjavers.dao;

import org.javers.spring.annotation.JaversAuditable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cnn.testjavers.bean.Product;

/**
 * @author ningning.cheng
 * @date 2021/10/18
 **/
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    /**
     * JaversAuditable 注解，不使用jpa报存
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    @JaversAuditable
    <S extends Product> S save(S entity);
}