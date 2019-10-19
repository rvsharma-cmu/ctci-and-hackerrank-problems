package com.rvsharma.Hackerrank;

import java.util.*;

public class SortByFrequency {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int _arr_size = Integer.parseInt(in.nextLine().trim());
        int[] _arr = new int[_arr_size];
        for(int _arr_i = 0; _arr_i < _arr_size; _arr_i++) {
            _arr[_arr_i] = Integer.parseInt(in.nextLine().trim());
        }

        customSort(_arr);
    }

    static void customSort(int[] arr) {
        Map<Integer, Integer> m = new HashMap<>();
        for(int i : arr){
            if(m.containsKey(i)){
                int temp = m.get(i) + 1;
                m.put(i, temp);
            } else {
                m.put(i, 1);
            }
        }

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(m.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                int val1 = o1.getValue();
                int val2 = o2.getValue();

                if(val1 > val2)
                    return 1;
                else if(val2 > val1)
                    return -1;
                else
                    return o1.getKey().compareTo(o2.getKey());
            }
        });

        LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();

        for (Map.Entry<Integer, Integer> map : entries) {
            sortedMap.put(map.getKey(), map.getValue());
        }

        for(int eachKey : sortedMap.keySet()){
            for(int i = 0; i<m.get(eachKey);i++){
                System.out.println(eachKey);
            }
        }
    }
}
