package com.github.asufana.domain.models.threads;

import com.github.asufana.ddd.entity.AbstractEntity;
import com.github.asufana.ddd.utils.SpringBeans;
import com.github.asufana.domain.models.threads.repo.ThreadRepo;
import com.github.asufana.domain.models.threads.vo.ThreadTitle;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@Entity
//@Table(name = "threads", uniqueConstraints = {@UniqueConstraint(columnNames = {"thread_title"})})
@Getter
@Accessors(fluent = true)
public class Thread extends AbstractEntity {

    private final ThreadTitle title;

    public Thread(ThreadTitle title) {
        this.title = title;

        isSatisfied();
    }

    public void isSatisfied() {
        //重複確認
        Thread other = repo().findByTitle(title);
        if(other != null){
            throw new RuntimeException("Entity is duplicate.");
        }
    }

    public Thread save(){
        repo().saveAndFlush(this);
        return this;
    }

    public ThreadRepo repo() {
        return SpringBeans.find(ThreadRepo.class);
    }
}
