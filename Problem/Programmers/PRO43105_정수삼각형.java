package programmers;

/* PLUS)
 현재 위치에서 다음 행의 값을 구하는 게 아니라, 이전 행으로부터 현재 행의 최댓값을 구하도록
 이러면 triangle배열로 사용하면 됨
 di, dj 배열도 안쓰고 위, 위오른쪽 값으로부터 구하면 됨 */

public class PRO43105_정수삼각형 {
    int[] di = {1, 1};
    int[] dj = {0, 1};
    public int solution(int[][] triangle) {
        int ans = 0;
        int[][] memo = new int[triangle.length][];
        for(int i = 0 ; i < memo.length ; i++) {
            memo[i] = new int[triangle[i].length];
        }

        memo[0][0] = triangle[0][0];

        for(int i = 0 ; i < triangle.length - 1 ; i++) {
            for(int j = 0 ; j < triangle[i].length ; j++) {
                for(int d = 0 ; d < 2 ; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    memo[ni][nj] = Math.max(memo[ni][nj], memo[i][j] + triangle[ni][nj]);
                }
            }
        }

        for(int j = 0 ; j < triangle[triangle.length - 1].length ; j++) {
            ans = Math.max(ans, memo[triangle.length - 1][j]);
        }

        return ans;
    }
}
