package com.github.asufana.domain.models.posts.repo;

import com.github.asufana.ddd.repo.AbstractRepository;
import com.github.asufana.domain.models.posts.Post;
import com.github.asufana.domain.models.posts.vo.PostTitle;
import com.github.asufana.domain.models.threads.Thread;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends AbstractRepository<Post> {

    Post findByTitle(PostTitle title);

    @Query("select p from Post p where thread=:thread")
    List<Post> findBy(@Param("thread") Thread thread);

}
