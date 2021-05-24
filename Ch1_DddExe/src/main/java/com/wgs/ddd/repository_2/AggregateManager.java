package com.wgs.ddd.repository_2;

public interface AggregateManager<T, ID> {

    void attach(T aggregate, ID id);

    void detach(T aggregate) ;

    T find(ID id);

    EntityDiff detectChanges(T aggregate);
}
