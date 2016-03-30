package com.github.asufana.ddd.vo;

import java.lang.reflect.*;

import org.apache.commons.lang3.builder.*;

@SuppressWarnings("rawtypes")
public abstract class AbstractVo implements Comparable {
    
    @Override
    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, other);
    }
    
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
    @Override
    public int compareTo(final Object other) {
        if (other == null) {
            return -1;
        }
        
        //Other value check
        Object otherValue = null;
        try {
            otherValue = getValue(other);
        }
        catch (final Exception e) {
            return 0;
        }
        if (otherValue == null) {
            return -1;
        }
        
        //This value check
        Object thisValue = null;
        try {
            thisValue = getValue(this);
        }
        catch (final Exception e) {
            return 0;
        }
        if (thisValue == null) {
            return 1;
        }
        
        return thisValue != null && otherValue != null
                ? thisValue.toString().compareTo(otherValue.toString())
                : 0;
    }
    
    //Valueフィールド値を取得
    private Object getValue(final Object instance) throws Exception {
        final Field valueField = instance.getClass().getDeclaredField("value");
        valueField.setAccessible(true);
        final Object value = valueField.get(instance);
        return value;
    }
}
