package com.github.asufana.ddd.collection;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

public abstract class AbstractCollection<T extends AbstractCollection, S> implements Iterable<S> {
    
    private final Class<?>[] constructorParams = {List.class};
    
    protected final List<S> list;
    
    /** コンストラクタ */
    public AbstractCollection(final List<S> list) {
        if (list == null || list.size() == 0) {
            this.list = Collections.emptyList();
        }
        else {
            this.list = Collections.unmodifiableList(list);
        }
    }
    
    //http://d.hatena.ne.jp/Nagise/20131121/1385046248
    protected T toCollection(final List<S> list) {
        try {
            final Class<?> clazz = this.getClass();
            final Type type = clazz.getGenericSuperclass();
            final ParameterizedType pt = (ParameterizedType) type;
            final Type[] actualTypeArguments = pt.getActualTypeArguments();
            final Class<?> entityClass;
            if (actualTypeArguments[0] instanceof ParameterizedType) {
                entityClass = (Class<?>) ((ParameterizedType) actualTypeArguments[0]).getRawType();
            }
            else {
                entityClass = (Class<?>) actualTypeArguments[0];
            }
            final Constructor<?> constructor = entityClass.getConstructor(constructorParams);
            return (T) constructor.newInstance(list);
        }
        catch (final ReflectiveOperationException e) {
            throw new RuntimeException("Error AbstractCollection.toCollection:"
                    + e.getMessage());
        }
    }
    
    /** リスト返却 */
    public List<S> list() {
        return list;
    }
    
    /** ソート逆順 */
    public T reverse() {
        final List<S> newList = new ArrayList<S>(list);
        Collections.reverse(newList);
        return toCollection(newList);
    }
    
    /** 指定インデックスを取得 */
    public Optional<S> get(final Integer index) {
        return list().size() > index
                ? Optional.of(list().get(index))
                : Optional.empty();
    }
    
    public boolean isEmpty() {
        return list == null || list.size() == 0;
    }
    
    public Integer size() {
        return list.size();
    }
    
    public boolean contains(final S obj) {
        return list.contains(obj);
    }
    
    /** 重複削除 */
    public T unique() {
        final Set<S> set = new HashSet<S>(list);
        return toCollection(new ArrayList<S>(set));
    }
    
    /** コレクションの追加 */
    public T add(final AbstractCollection collection) {
        final List<S> newList = new ArrayList<S>(list);
        newList.addAll(collection.list);
        return toCollection(newList);
    }
    
    /** コレクションの削除 */
    public T remove(final AbstractCollection collection) {
        final List<S> newList = new ArrayList<S>(list);
        for (final S obj : list) {
            if (collection.list.contains(obj)) {
                newList.remove(obj);
            }
        }
        return toCollection(newList);
    }
    
    public Stream<S> stream() {
        return list().stream();
    }
    
    @Override
    public String toString() {
        return stream().map(obj -> obj.toString())
                       .collect(Collectors.joining(","));
    }
    
    @Override
    public Iterator<S> iterator() {
        return list().iterator();
    }
}
