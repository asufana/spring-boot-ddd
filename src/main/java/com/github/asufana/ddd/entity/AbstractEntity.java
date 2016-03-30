package com.github.asufana.ddd.entity;

import java.time.*;

import javax.persistence.*;

import com.github.asufana.ddd.repo.*;

@MappedSuperclass
public abstract class AbstractEntity extends BaseEntity<AbstractEntity> {
    
    public static final Integer ENTITY_ENABLE = 0;
    public static final Integer ENTITY_DISABLE = 1;
    
    /** 楽観ロックフラグ */
    @Version
    protected Long version;
    
    public Long version() {
        return version;
    }
    
    /** 生成日時 */
    @Column(name = "created_date", nullable = false)
    protected LocalDateTime createdDate;
    
    public LocalDateTime createdDate() {
        return createdDate;
    }
    
    /** 更新日時 */
    @Column(name = "updated_date", nullable = false)
    protected LocalDateTime updatedDate;
    
    public LocalDateTime updatedDate() {
        return updatedDate;
    }
    
    /** 無効フラグ */
    @Column(name = "is_disable", nullable = false)
    protected Integer isDisable = ENTITY_ENABLE;
    
    /** エンティティか無効化されているか */
    public boolean isDisable() {
        return isDisable == ENTITY_DISABLE;
    }
    
    /** エンティティ有効化 */
    public void toEnable() {
        isDisable = ENTITY_ENABLE;
    }
    
    /** エンティティ無効化 */
    public void toDisable() {
        isDisable = ENTITY_DISABLE;
    }
    
    @PrePersist
    /** 生成時処理 */
    protected void prePersist() {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
        //エンティティ評価
        isSatisfied();
    }
    
    @PreUpdate
    /** 更新時処理 */
    protected void preUpdate() {
        updatedDate = LocalDateTime.now();
        //エンティティ評価
        isSatisfied();
    }
    
    @PreRemove
    protected void preRemove() {}
    
    @PostPersist
    protected void postPersist() {}
    
    @PostUpdate
    protected void postUpdate() {}
    
    @PostRemove
    protected void postRemove() {}
    
    @PostLoad
    protected void postLoad() {}
    
    /** エンティティが仕様を満たしているかどうか */
    public abstract void isSatisfied();
    
    /** リポジトリ取得 */
    public abstract <T extends AbstractRepository<T>> T repo();
    
}
