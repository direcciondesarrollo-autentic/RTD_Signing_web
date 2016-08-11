package com.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by root on 8/10/16.
 */
@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findByLastName(String lastName);
}
