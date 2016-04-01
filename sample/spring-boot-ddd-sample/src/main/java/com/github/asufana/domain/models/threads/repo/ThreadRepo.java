package com.github.asufana.domain.models.threads.repo;

import com.github.asufana.ddd.repo.AbstractRepository;
import com.github.asufana.domain.models.threads.Thread;
import com.github.asufana.domain.models.threads.vo.ThreadTitle;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepo extends AbstractRepository<Thread> {

    Thread findByTitle(ThreadTitle title);

}
