package com.wgs.ddd.repository_2;

import lombok.Getter;
import org.assertj.core.util.diff.DiffUtils;
import org.springframework.util.ReflectionUtils;

import java.util.HashMap;
import java.util.Map;

public class DbContext<T extends Aggregate<ID>, ID extends Identifier>  {

    @Getter
    private Class<? extends T> aggregateClass;

    private Map<ID, T> aggregateMap = new HashMap<>();

    public DbContext(Class<? extends T> aggregateClass) {
        this.aggregateClass = aggregateClass;
    }

    public void attach(T aggregate) {
        if (aggregate.getId() != null) {
            if (!aggregateMap.containsKey(aggregate.getId())) {
                this.merge(aggregate);
            }
        }
    }

    public void detach(T aggregate) {
        if (aggregate.getId() != null) {
            aggregateMap.remove(aggregate.getId());
        }
    }

    public EntityDiff detectChanges(T aggregate) {
        if (aggregate.getId() == null) {
            //return EntityDiff.EMPTY;
            return null;
        }
        T snapshot = aggregateMap.get(aggregate.getId());
        if (snapshot == null) {
            attach(aggregate);
        }
        //return DiffUtils.diff(snapshot, aggregate);
        return null;
    }

    public T find(ID id) {
        return aggregateMap.get(id);
    }

    public void merge(T aggregate) {
        if (aggregate.getId() != null) {
            //T snapshot = SnapshotUtils.snapshot(aggregate);
            //aggregateMap.put(aggregate.getId(), snapshot);
        }
    }

    public void setId(T aggregate, ID id) {
        //ReflectionUtils.writeField("id", aggregate, id);
    }
}
