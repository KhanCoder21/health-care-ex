package com.arsad.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* Created by Arsad on 2023-03-18 22:18 */
public interface CollectionUtils {

    static Map<Long, String> convertListToMapTwoIndex(List<Object[]> list) {
        return list.stream().collect(Collectors.toMap(ob -> Long.valueOf(ob[0].toString()), ob -> ob[1].toString()));
    }

    static Map<Long, String> convertListToMapThirdIndex(List<Object[]> list) {
        return list.stream().collect(Collectors.toMap(ob -> Long.valueOf(ob[0].toString()), ob -> ob[1].toString() + " " + ob[2].toString()));
    }
}
