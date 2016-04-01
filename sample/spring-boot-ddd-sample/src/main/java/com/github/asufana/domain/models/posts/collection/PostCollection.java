package com.github.asufana.domain.models.posts.collection;

import com.github.asufana.ddd.collection.AbstractCollection;
import com.github.asufana.domain.models.posts.Post;

import java.util.List;

public class PostCollection extends AbstractCollection<PostCollection, Post> {
    
    public PostCollection(List<Post> list) {
        super(list);
    }
    
}
