package CommomAlgorithm.Other;

import java.util.*;

public class temp {
    public static void main(String[] args) {
        List<Integer> tempList = new ArrayList<>();
        Set<Integer> tempSet;
        Queue<Integer> tempQueue;
        Stack<Integer> tempStack;
        Map<Integer, Integer> tempMap = new HashMap<>();
        tempMap.put(1, 1);
        tempMap.put(2, 2);
        System.out.println(tempMap);
        Collection<Integer> tempCollection = tempList;
        tempList.add(1);
        tempList.add(2);
        System.out.println(tempCollection);
        String s = "abc";

    }

}
