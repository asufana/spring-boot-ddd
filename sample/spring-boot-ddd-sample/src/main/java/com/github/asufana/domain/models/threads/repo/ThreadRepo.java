package com.github.asufana.domain.models.threads.repo;

import org.springframework.stereotype.Repository;

import com.github.asufana.ddd.repo.AbstractRepository;
import com.github.asufana.domain.models.threads.Thread;
import com.github.asufana.domain.models.threads.vo.ThreadTitle;

@Repository
public interface ThreadRepo extends AbstractRepository<Thread> {

    Thread findByTitle(ThreadTitle title);

}
