package com.joken.survey2015;

import java.util.ArrayList;

public class CollectionUtils {

	/**
	 * ArrayListを指定したサイズ毎に分割します。
	 *
	 * @param origin 分割元のArrayList
	 * @param size ArrayListの分割単位
	 * @return サイズ毎に分割されたArrayArrayList。但し、ArrayListがnullまたは空の場合、もしくはsizeが0以下の場合は空のArrayListを返す。
	 */
	public static <T> ArrayList<ArrayList<T>> devide(ArrayList<T> origin, int size) {
		if (origin == null || origin.isEmpty() || size <= 0) {
			return new ArrayList<ArrayList<T>>(0);
		}

		int block = origin.size() / size + (origin.size() % size > 0 ? 1 : 0 );

		ArrayList<ArrayList<T>> devidedList = new ArrayList<ArrayList<T>>(block);
		for (int i = 0; i < block; i ++) {
			int start = i * size;
			int end = Math.min(start + size, origin.size());
			devidedList.add(new ArrayList<T>(origin.subList(start, end)));
		}
		return devidedList;
	}
}
