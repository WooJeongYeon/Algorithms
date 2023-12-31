import java.util.HashSet;
import java.util.Set;
/*
 * Date : 2021.12.02
 * Level : Programmers level 1
 * URL : https://programmers.co.kr/learn/courses/30/lessons/1845
 */
public class PRO1845_폰켓몬 {
	class Solution {
	    public int solution(int[] nums) {
	        int answer = nums.length / 2;
	        Set<Integer> set = new HashSet<>();
	        for(int i = 0 ; i < nums.length ; i++) {
	            set.add(nums[i]);
	        }
	        if(set.size() < answer) answer = set.size();
	        
	        return answer;
	    }
	}
}
