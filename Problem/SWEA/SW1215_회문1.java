package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW1215_회문1 {
    static int N, ans;
    static char[][] map;
    static final int SIZE = 8;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int tc = 1 ; tc <= 10 ; tc++) {
            N = Integer.parseInt(in.readLine());
            ans = 0;
            map = new char[SIZE][];
            for(int i = 0 ; i < SIZE ; i++) {
                map[i] = in.readLine().toCharArray();
            }

            ans += findCnt();

            rotate();

            ans += findCnt();

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static int findCnt() {
        int cnt = 0;
        for(int i = 0 ; i < SIZE ; i++) {
            for(int j = 0 ; j < SIZE - N + 1 ; j++) {
                int s = j, e = j + N - 1;
                if(compare(i, s, e))
                    cnt++;

            }
        }
        return cnt;
    }

    private static boolean compare(int i, int s, int e) {
        for(int k = 0 ; k < N / 2 ; k++) {
            if(map[i][s + k] != map[i][e - k])
                return false;
        }
        return true;
    }

    private static void rotate() {
        char[][] newMap = new char[SIZE][SIZE];
        for(int i = 0 ; i < SIZE ; i++) {
            for(int j = 0 ; j < SIZE ; j++) {
                newMap[j][SIZE - i - 1] = map[i][j];
            }
        }
        map = newMap;
    }
}