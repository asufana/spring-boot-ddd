package com.github.asufana.domain.models.posts;

import com.github.asufana.App;
import com.github.asufana.ddd.test.AbstractTest;
import com.github.asufana.domain.T;
import com.github.asufana.domain.models.posts.repo.PostRepo;
import com.github.asufana.domain.models.threads.Thread;
import com.github.asufana.domain.models.threads.ThreadTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SpringApplicationConfiguration(classes = App.class)
public class PostTest extends AbstractTest {

    @Autowired
    PostRepo repo;

    @Override
    public void before() {
        assertThat(repo.findAll().size(), is(0));
    }

    public Post create() {
        //Thread生成
        Thread thread = new ThreadTest().create();
        //Post生成
        Post post = new Post(thread, T.postTitle).save();

        //生成されること
        assertThat(post, is(notNullValue()));
        //関連付けられていること
        assertThat(post.thread(), is(thread));
        assertThat(thread.posts().get(0), is(post));
        return post;
    }

    @Test
    @Rollback
    public void testConstructor() {
        create();
    }

}
