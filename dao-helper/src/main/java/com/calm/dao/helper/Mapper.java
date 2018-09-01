package com.calm.dao.helper;

import java.util.Map;

public interface Mapper<T> {
	T mapping(Map<String, Object> map);
}
