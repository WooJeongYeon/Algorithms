/*
 * Date : 2021.11.03
 * Level : Programmers Level 2
 * Difficulty : 쉬움
 * Time : 5분
 * URL : https://programmers.co.kr/learn/courses/30/lessons/12924
 */
public class PRO12924_숫자의표현 {
	public static void main(String[] args) {
		int n = 50;
		System.out.println(solution(n));
	}
	
	public static int solution(int n) {
        int answer = 0;
        int sum = 0;
        for(int i = 1 ; i <= n ; i++) {
            for(int j = i ; j <= n ; j++) {
                sum += j;
                if(sum == n) {
                    answer++;
                }
                if(sum >= n) {
                    break;
                }
            }
            sum = 0;
        }
        
        return answer;
    }
}
