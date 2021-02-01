package com.wenlincheng.pika.common.core.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yangjie on 2016/12/8.
 */
public class PagingUtil<T> {

    public static <T> List<T> paging(List<T> list, int page, int size){

        List<T> result = new ArrayList<>();
        if(list == null) {
            return result;
        }
        int start = page == 0 ? 0 : (page - 1) * size;
        int end = start +size;
        if(start>=list.size()){
            return result;
        }else if(end > list.size()){
            return list.subList(start,list.size());
        }else {
            return list.subList(start,end);
        }
    }

    public static <T> List<T> paging_desc(List<T> list, int page, int size){
        if(list == null) {
            return new ArrayList<T>();
        }
        List<T> reversedList = new ArrayList<T>(list);
        Collections.reverse(reversedList);
        return paging(reversedList,page,size);
    }
}
