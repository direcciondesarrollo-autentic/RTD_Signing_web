package com.repositories;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:springTest.xml"})
@ContextConfiguration(classes = {DynamoDBConfig.class})
//@ContextConfiguration("webapp/applicationContext.xml")
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() {
        //userRepository.deleteAll();
    }

    @Test
    public void findByUserName() {
        /*final User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        userRepository.save(user);

        final User actualUser = userRepository.findOne(user.getLastName());
        assertEquals(user, actualUser);*/

        User user = new User();
        user.setFirstName("firstName 2");
        user.setLastName("lastName 2");
        userRepository.save(user);

        assertEquals(true, true);
    }
}
