package com.github.asufana.domain.models.threads;

import com.github.asufana.App;
import com.github.asufana.ddd.test.AbstractTest;
import com.github.asufana.domain.T;
import com.github.asufana.domain.models.threads.repo.ThreadRepo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SpringApplicationConfiguration(classes = App.class)
public class ThreadTest extends AbstractTest {

    @Autowired
    ThreadRepo repo;

    @Override
    public void before() {
        assertThat(repo.findAll().size(), is(0));
    }

    public Thread create() {
        Thread thread = new Thread(T.title01);
        thread.save();

        //生成されること
        assertThat(thread, is(notNullValue()));
        return thread;
    }

    @Test
    @Rollback
    public void testConstructor() {
        Thread thread = create();

        //同一であること
        assertThat(thread, is(repo.findById(thread.id())));
    }

    @Test(expected = RuntimeException.class)
    @Rollback
    public void testDuplicateExceptoin() {
        //生成
        create();
        //生成 -> エラーが発生すること
        create();
    }

}
