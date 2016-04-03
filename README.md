# Abstract DDD classes for Spring boot.

## Entity

```java
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
```

## Value Object

```java
@Embeddable
@Getter
@Accessors(fluent = true)
@ToString
public class PostTitle extends AbstractVo {

    @Column(name = "post_title", length = 255, nullable = false)
    private String value;

    public PostTitle(String value) {
        this.value = value;
    }

    //for hibernate
    private PostTitle(){}
}
```

## Repository

```java
@Repository
public interface PostRepo extends AbstractRepository<Post> {
    Post findByTitle(PostTitle title);

    @Query("select p from Post p where thread=:thread")
    List<Post> findBy(@Param("thread") Thread thread);
}
```

## Collection

```java
public class PostCollection extends AbstractCollection<PostCollection, Post> {
    public PostCollection(List<Post> list) {
        super(list);
    }
}
```

