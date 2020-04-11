package com.rvsharma.Hackerrank.AmazonSDE;// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.List;
import java.util.*;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class CriticalConnections
{
    static int time = 0;
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    List<Integer> criticalRouters(int numRouters, int numLinks,
                                  ArrayList<ArrayList<Integer>> links)
    {
        // WRITE YOUR CODE HERE

        if(links.size() == 0 || links.get(0).size() == 0 || numRouters < 2 ||
                numLinks < 2)
            return new ArrayList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();

        time = 0;
        for(int i = 1; i <= numRouters; i++){
            map.put(i, new HashSet<>());
        }
        for(ArrayList<Integer> list : links){
            System.out.println(list);
            Set<Integer> set1 = map.get(list.get(0));
            set1.add(list.get(1));
            map.get(list.get(1)).add(list.get(0));
        }

        Set<Integer> set = new HashSet<>();
        int[] low = new int[numRouters+1];
        int[] ids = new int[numRouters+1];
        int[] parent = new int[numRouters+1];

        Arrays.fill(ids, -1);
        Arrays.fill(parent, -1);

        for(int i = 1; i <= numRouters; i++){
            if(ids[i] == -1){
                dfs(map, low, ids, parent, i, set);
            }
        }
        return new ArrayList<>(set);
    }

    void dfs(Map<Integer, Set<Integer>> map, int[] low, int[] ids, int[] parent, int cur, Set<Integer> res){
        int children = 0;
        ids[cur] = ++time;
        low[cur] = ++time;
        for(int nei : map.get(cur)){
            if(ids[nei] == -1){
                children++;
                parent[nei] = cur;
                dfs(map, low, ids, parent, nei, res);
                low[cur] = Math.min(low[cur], low[nei]);
                if((parent[cur] == -1 && children > 1) || (parent[cur] != -1 && low[nei] >= ids[cur])){
                    res.add(cur);
                }
            }else if(nei != parent[cur]){
                low[cur] = Math.min(low[cur], ids[nei]);
            }
        }
    }

    // METHOD SIGNATURE ENDS
}