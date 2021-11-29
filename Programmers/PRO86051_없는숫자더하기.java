
/*
 * Date : 2021.11.29
 * Level : Programmers level 1
 * URL : https://programmers.co.kr/learn/courses/30/lessons/86051
 */
public class PRO86051_없는숫자더하기 {
	class Solution {
	    public int solution(int[] numbers) {
	        int answer = 45;
	        for(int i = 0 ; i < numbers.length ; i++) {
	            answer -= numbers[i];
	        }
	        return answer;
	    }
	}
}
