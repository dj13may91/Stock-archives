package com.stock.archives;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
//        System.out.println(fourthBit(32));
//        missingWords("I am using HackerRank to improve programming", "am HackerRank to improve");
//        List<Integer> l = new ArrayList<>();
//        l.add(1); l.add(2); l.add(3); l.add(4); l.add(1);
//        kSub(3, l);
//        List<Integer> outputArray = new ArrayList<>();
//        customSort(outputArray);
        System.out.println(solve(new long[]{3, 4, 5}, 3));
    }

    static int solve(long[] A, int N) {
        // Write your code here
        long charge = 0;
        Set<Long> set = new HashSet<>();
        for (long a : A) {
            set.add(a);
        }
        double eachElementSubsetCount = Math.pow(2, set.size() - 1);
        for (long el : set) {
            if (el >= eachElementSubsetCount) {
                charge = charge + el;
            }
        }
        return (int) charge;
//        while(totalSets-- > 0 ){
//            StringBuilder binary = new StringBuilder(Integer.toBinaryString((int) totalSets));
//            while(binary.length() < uniqueSets){
//                binary.reverse().append("0").reverse();
//            }
//            System.out.println(binary + "  ");
//            char subsetIndexMapper[] = binary.toString().toCharArray();
//            List<Long> subset = new ArrayList<>();
//            for(int i=0; i<uniqueSets; i++){
//                if(subsetIndexMapper[i] == '1')
//                    map.put(A[i], map.get(A[i]) + 1);
//            }
//            System.out.println(map);
//        }
    }

    public static void customSort(List<Integer> arr) {
        Map<Integer, Integer> map = new TreeMap<>();
        Comparator<Integer> valueComparator = new Comparator<Integer>() {
            public int compare(Integer k1, Integer k2) {
                int compare = map.get(k2).compareTo(map.get(k1));
                if (compare == 0)
                    return 1;
                else
                    return compare;
            }
        };
        Integer[] array = {4, 4, 2, 2, 2, 2, 3, 3, 1, 1, 6, 7, 5};
        Collections.addAll(arr, array);
//        arr.addAll(Arrays.asList(array));
        arr.stream().forEach((item) -> {
            if (map.get(item) == null) {
                map.put(item, 1);
            } else {
                map.put(item, map.get(item) + 1);
            }
        });
        map.keySet();

        Map<Integer, Integer> sortedMap = new TreeMap<>(valueComparator);
        sortedMap.putAll(map);
        sortedMap.forEach((k, v) -> {
            for (int i = 0; i < v; i++) {
                System.out.print(k + " ");
            }
        });
    }

    public static List<String> missingWords(String s, String t) {
        // Write your code here
        List<String> sentence = new LinkedList<>();
        for (String word : s.split(" ")) {
            sentence.add(word);
        }
        for (String word : t.split(" ")) {
            sentence.remove(sentence.indexOf(word));
        }
        System.out.println(sentence);
        return sentence;

    }

    public static int fourthBit(int num) {
        // Write your code here
        char arr[] = Integer.toBinaryString(num).toCharArray();
        return arr[arr.length - 4] - 48;
    }

    public static long kSub(int k, List<Integer> nums) {
        // Write your code here
        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = nums.size(); j > i; j--) {
                if ((nums.subList(i, j).parallelStream().mapToInt(Integer::intValue).sum()) % k == 0) {
                    count++;
                }
            }
        }
        System.out.println(count);

        return count;
    }
}
