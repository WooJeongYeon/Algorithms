package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 질문 봄... - 최대 비용 * 최대 도시개수보다 크게 MAX_VALUE를 설정해줘야 함! -> A도시에서 X도시까지 가는 데 저만큼의 비용이 들 수 있으므로
public class BJ11404_플로이드 {
    static int N, M;
    static int[][] lenArr;
    static final int MAX_VALUE = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        lenArr = new int[N + 1][N + 1];
        for(int i = 1 ; i <= N ; i++) {
            Arrays.fill(lenArr[i], MAX_VALUE);
        }
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            lenArr[start][end] = Math.min(lenArr[start][end], value);
        }
        floyd();
        for(int i = 1 ; i <= N ; i++) {
            for(int j = 1 ; j <= N ; j++) {
                sb.append((lenArr[i][j] == MAX_VALUE ? 0 : lenArr[i][j]) + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void floyd() {
        for(int k = 1 ; k <= N ; k++) {
            for(int i = 1 ; i <= N ; i++) {
                if(k == i) continue;
                for(int j = 1 ; j <= N ; j++) {
                    if(k == j || i == j) continue;
                    lenArr[i][j] = Math.min(lenArr[i][j], lenArr[i][k] + lenArr[k][j]);
                }
            }
        }
    }
}
