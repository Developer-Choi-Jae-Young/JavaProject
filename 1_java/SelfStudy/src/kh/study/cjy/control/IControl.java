package kh.study.cjy.control;

import java.util.List;

public interface IControl<T> {
	List<T> select();
	boolean insert(T type);
	boolean delete(T type);
	default boolean update(T type) {return false;}
}
