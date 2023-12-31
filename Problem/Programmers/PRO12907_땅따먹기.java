/*
 * Date : 2022.01.20
 * Level : Programmers level 2
 * Difficulty : ��
 * Why : Ǫ�� ����� �𸣰ھ ��Ʈ �� ǰ�ФФ�
 * URL : https://programmers.co.kr/learn/courses/30/lessons/12913?language=java
 * Thinking : ������ ��� ���� �࿡�� ū�� �����Ϸ� ��
 * Method : DP
 * Help : DP�� 1���� �迭�� �Ϸ��� �ߴµ� 2���� �迭�� Ǯ��ߵǴ���ФФФ�
 * Result : DP �ʹ� �����Ф� ���α׷��ӽ� ����̤Ф�
 */
public class PRO12907_�����Ա� {
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
