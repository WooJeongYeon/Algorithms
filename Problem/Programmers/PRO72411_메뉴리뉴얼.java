/*
 * Date : 2022.02.01
 * Level : Programmers level 2
 * Why : 중하
 * Time : 1h
 * URL : https://programmers.co.kr/learn/courses/30/lessons/72411
 * Method : 조합
 * Error1 : 가장 많이 주문된 단품메뉴 조합 찾는거!
 * Result : 쉬운듯 어려운듯
 * Plus1 : string 정렬시키는 방법 -> char[]로 바꿔서 Arrays.sort로 정렬시킨 후 new String()으로 다시 바꿔줌
 * Plus2 : 너무 코드 더러워짐 -> course 길이마다 최댓값 구하면서 진행하도록
 */
import java.util.*;

public class PRO72411_메뉴리뉴얼 {
    Map<String, Integer> map;
    ArrayList<String> result;
    int max;
    public String[] solution(String[] orders, int[] course) {
        result = new ArrayList<>();
        for(int i = 0 ; i < course.length ; i++) {
            max = -1;
            map = new HashMap<>();
            for(int j = 0 ; j < orders.length ; j++) {
                subset(0, "", orders[j], course[i]);
            }
            if(max >= 2) {
                for(String key : map.keySet()) {
                    if(map.get(key) == max) {
                        result.add(key);
                    }
                } 
            }
        }
        Collections.sort(result);
        String[] answer = new String[result.size()];
        int idx = 0;
        for(String s : result) {
            answer[idx++] = s;
        }
        return answer;
    }
    void subset(int idx, String s, String order, int len) {
        if(s.length() == len) {
            char[] cArr = s.toCharArray();
            Arrays.sort(cArr);
            s = new String(cArr);
            if(map.get(s) == null) map.put(s, 1);
            else map.put(s, map.get(s) + 1);
            max = Math.max(max, map.get(s));
            return;
        }
        if(idx == order.length()) return;
        subset(idx + 1, s + order.charAt(idx), order, len);
        subset(idx + 1, s, order, len);
    }
}
