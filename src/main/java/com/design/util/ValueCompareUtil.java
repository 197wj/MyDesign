package com.design.util;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 工具类：将map按照value值进行排序
 * @author 王静
 *
 */
public class ValueCompareUtil implements Comparator<Map.Entry<String, Double>> {

	@Override
	public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {

		// o2-o1：从大到小进行排序
		return (o2.getValue()-o1.getValue())>=0?1:-1;
	}

}
