package com.calm.dao.helper.entity;

public interface Orderable<T extends Comparable<T>, E extends Orderable<T,E>> extends Comparable<E>{
	T getOrderIndex();
}
