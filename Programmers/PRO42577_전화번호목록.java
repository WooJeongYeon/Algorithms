
import java.util.*;
/*
 * Date : 2022.02.07
 * Level : Programmers level 2
 * URL : https://programmers.co.kr/learn/courses/30/lessons/42577
 * Method : 해시 
 */
public class PRO42577_전화번호목록 {
	class Solution {
	    public boolean solution(String[] phone_book) {
	        boolean answer = true;
	        Set<Integer> lenSet = new HashSet<>();
	        Map<String, Integer> map = new HashMap<>();
	        for(int i = 0 ; i < phone_book.length ; i++){
	            lenSet.add(phone_book[i].length());
	            map.put(phone_book[i], 1);
	        }
	        for(Integer len : lenSet) {
	            for(int i = 0 ; i < phone_book.length ; i++){
	                if(phone_book[i].length() <= len) continue;
	                String s = phone_book[i].substring(0, len);
	                if(map.containsKey(s)) {
	                    answer = false;
	                    break;
	                }
	            }
	            if(!answer) break;
	        }
	        
	        return answer;
	    }
	}
}
