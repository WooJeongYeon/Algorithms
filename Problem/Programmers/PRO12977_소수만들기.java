/*
 * Date : 2021.11.10
 * Level : Programmers Level 1
 * Difficulty : 하하
 * Time : 10분?
 * URL : https://programmers.co.kr/learn/courses/30/lessons/12977
 * Method : 조합, 소수
 * Result : 아까 조합문제 풀었는데 또 연습하게 되네 신기
 */
public class PRO12977_소수만들기 {
	class Solution {
	    final int DEPTH = 3;
	    int sum, answer;
	    public int solution(int[] nums) {
	        answer = 0;
	        comb(0, 0, nums);
	        return answer;
	    }
	    void comb(int idx, int cnt, int[] nums) {
	        if(cnt == DEPTH) {
	            if(isDecimal(sum)) {
	                answer++;
	            }
	            return;
	        }
	        if(idx == nums.length) {
	            return;
	        }
	        sum += nums[idx];
	        comb(idx + 1, cnt + 1, nums);
	        sum -= nums[idx];
	        comb(idx + 1, cnt, nums);
	    }
	    boolean isDecimal(int n) {
	        for(int i = 2 ; i < n ; i++) {
	            if(n % i == 0) return false;
	        }
	        return true;
	    } 
	}
}
