package com.calm.dao.helper.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author dingqihui
 */
//@Embeddable
public interface AbstractTreeEntity<I extends Serializable, T extends BaseEntity<I>> extends BaseEntity<I> {

	T getParent();

	void setParent(T parent);

	List<T> getChildren() ;

	void setChildren(List<T> children);
}
