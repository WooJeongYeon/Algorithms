package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Date : 2022.06.18
 * Level : BaekJoon Gold 3
 * Difficulty : 중하
 * Why : 9466. 텀 프로젝트 그거랑 비슷하다
 * URL : https://www.acmicpc.net/problem/16724
 * Thinking : 구역을 나누기(숫자로 표시하기)
 * Method : DFS느낌...
 * Plus1 : Disjoint로도 풀 수 있음
 */

public class BJ16724_피리부는사나이 {
    static int N, M, ans;
    static char[][] board;
    static int[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][];
        visited = new int[N][M];
        for(int i = 0 ; i < N ; i++) {
            board[i] = in.readLine().toCharArray();
        }


        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < M ; j++) {
                int num = i * N + j + 1;
                if(visited[i][j] != 0)
                    continue;
                int ni = i, nj = j;
                boolean isPossible = false;
                while(true) {
                    visited[ni][nj] = num;
                    int d = changeIdx(board[ni][nj]);
                    ni += di[d];
                    nj += dj[d];
                    if(visited[ni][nj] == num) {
                        isPossible = true;
                        break;
                    } else if(visited[ni][nj] != 0) {
                        break;
                    }
                }

                if(isPossible)
                    ans++;
            }
        }

        System.out.println(ans);
    }

    static int changeIdx(char c) {
        switch(c) {
            case 'U' :
                return 0;
            case 'D' :
                return 1;
            case 'L' :
                return 2;
            case 'R' :
                return 3;
        }
        return -1;
    }
}
