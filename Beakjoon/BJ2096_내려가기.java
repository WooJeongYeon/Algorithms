package baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// DP
// max랑 min 변수 헷갈림

public class BJ2096_내려가기 {
    static int max, min, N;
    static int[][][] memo;
    static int[] dj = {-1, 0, 1};
    static final int MAX = 0;
    static final int MIN = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        memo = new int[2][N][3];
        min = Integer.MAX_VALUE;
        st = new StringTokenizer(in.readLine());
        memo[MAX][0][0] = memo[MIN][0][0] = Integer.parseInt(st.nextToken());
        memo[MAX][0][1] = memo[MIN][0][1] = Integer.parseInt(st.nextToken());
        memo[MAX][0][2] = memo[MIN][0][2] = Integer.parseInt(st.nextToken());
        for(int i = 1 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 0 ; j < 3 ; j++) {
                int num = Integer.parseInt(st.nextToken());
                memo[MIN][i][j] = Integer.MAX_VALUE;
                for(int k = 0 ; k < 3 ; k++) {
                    int nj = j + dj[k];
                    if(nj < 0 || nj >= 3)
                        continue;
                    memo[MAX][i][j] = Math.max(memo[MAX][i][j], memo[MAX][i - 1][nj]);
                    memo[MIN][i][j] = Math.min(memo[MIN][i][j], memo[MIN][i - 1][nj]);
                }
                memo[MAX][i][j] += num;
                memo[MIN][i][j] += num;
            }
        }

        for(int i = 0 ; i < 3 ; i++) {
            max = Math.max(max, memo[MAX][N - 1][i]);
            min = Math.min(min, memo[MIN][N - 1][i]);
        }

        System.out.println(max + " " + min);
    }
}
