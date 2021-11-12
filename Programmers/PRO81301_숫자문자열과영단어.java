/*
 * Date : 2021.11.12
 * Level : Programmers Level 1
 * URL : https://programmers.co.kr/learn/courses/30/lessons/81301
 */
public class PRO81301_숫자문자열과영단어 {
	class Solution {
	    String[] strToNum = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	    public int solution(String s) {
	        for(int i = 0 ; i < strToNum.length ; i++) {
	            s = s.replaceAll(strToNum[i], i + "");
	        }
	        
	        int answer = Integer.parseInt(s);
	        return answer;
	    }
	}
}
