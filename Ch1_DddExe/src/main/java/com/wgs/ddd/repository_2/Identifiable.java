package com.wgs.ddd.repository_2;

public interface Identifiable<ID extends Identifier> {

    ID getId();
}
