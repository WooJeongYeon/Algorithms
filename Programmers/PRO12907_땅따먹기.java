/*
 * Date : 2022.01.20
 * Level : Programmers level 2
 * Difficulty : 상
 * Why : 푸는 방법을 모르겠어서 힌트 얻어서 품ㅠㅠㅠ
 * URL : https://programmers.co.kr/learn/courses/30/lessons/12913?language=java
 * Thinking : 이이전 행과 이전 행에서 큰값 추출하려 함
 * Method : DP
 * Help : DP를 1차원 배열로 하려고 했는데 2차원 배열로 풀어야되더라ㅠㅠㅠㅠ
 * Result : DP 너무 어려우ㅠㅠ 프로그래머스 어려ㅜㅠㅠ
 */
public class PRO12907_땅따먹기 {
	final int size = 4;
    int solution(int[][] land) {
        int N = land.length;
        int[][] memo = new int[N][size];
        for(int i = 0 ; i < size ; i++) {
            memo[0][i] = land[0][i];
        }
        for(int i = 1 ; i < N ; i++) {
            for(int j = 0 ; j < size ; j++) {
                int max = 0;
                for(int k = 0 ; k < size ; k++) {
                    if(j == k) continue;
                    max = Math.max(memo[i - 1][k], max);
                }
                memo[i][j] = max + land[i][j];
            }
        }
        int ans = 0;
        for(int i = 0 ; i < size ; i++) {
            ans = Math.max(ans, memo[N - 1][i]);
        }
        return ans;
    }
}
