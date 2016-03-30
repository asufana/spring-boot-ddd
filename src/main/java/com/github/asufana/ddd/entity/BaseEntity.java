package com.github.asufana.ddd.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity<T extends BaseEntity<?>> {
    
    @Id
    @GeneratedValue
    protected Long id;
    
    public Long id() {
        return id;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        return sameIdentityAs((T) other);
    }
    
    protected boolean sameIdentityAs(final T other) {
        if (id == null) {
            return false;
        }
        return other != null && id().equals(other.id);
    }
    
    @Override
    public int hashCode() {
        return id != null
                ? id.hashCode()
                : 0;
    }
}
