/*
 * Date : 2021.12.04
 * Level : Programmers level 1
 * URL : https://programmers.co.kr/learn/courses/30/lessons/12912
 */
public class PRO12912_두정수사이의합 {
	class Solution {
	    public long solution(int a, int b) {
	        long answer = 0;
	        long max = Math.max(a, b);
	        long min = Math.min(a, b);
	        answer = (max + min) * ((max - min + 1) / 2);
	        if((max - min + 1) % 2 == 1) {
	            answer += (max + min) / 2;
	        }
	        return answer;
	    }
	}
}
