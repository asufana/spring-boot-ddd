package com.github.asufana.ddd.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class AbstractTest {
    
    @Before
    public void init() {
        before();
    }
    
    public abstract void before();
    
}
