package com.github.asufana.domain.models.posts;

import com.github.asufana.ddd.entity.AbstractEntity;
import com.github.asufana.domain.models.posts.repo.PostRepo;
import com.github.asufana.domain.models.posts.vo.PostTitle;
import com.github.asufana.domain.models.threads.Thread;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static com.github.asufana.ddd.utils.WireUtils.wired;

@Entity
@Table(name = "posts")
@Getter
@Accessors(fluent = true)
public class Post extends AbstractEntity {
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "threadId")
    private final Thread thread;
    
    private final PostTitle title;
    
    public Post(@NonNull Thread thread, @NonNull PostTitle title) {
        this.thread = thread;
        this.title = title;
        
        isSatisfied();
    }
    
    public void isSatisfied() {}
    
    public Post save() {
        repo().saveAndFlush(this);
        return this;
    }
    
    public PostRepo repo() {
        return wired(PostRepo.class);
    }
}
