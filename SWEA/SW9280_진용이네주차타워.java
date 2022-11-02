package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW9280_진용이네주차타워 {
    static int TC, N, M, cnt;
    static Queue<Integer> queue;
    static double ans;
    static int[] weights;
    static int[] prices;
    static int[] posArr;
    static boolean[] parked;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        for(int tc = 1 ; tc <= TC ; tc++) {
            ans = 0;
            cnt = 0;
            st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            weights = new int[N];
            parked = new boolean[N];
            prices = new int[M + 1];
            posArr = new int[M + 1];
            queue = new LinkedList<>();

            for(int i = 0 ; i < N ; i++) {
                weights[i] = Integer.parseInt(in.readLine());
            }
            for(int i = 1 ; i <= M ; i++) {
                prices[i] = Integer.parseInt(in.readLine());
            }
            for(int i = 0 ; i < (M << 1) ; i++) {
                int nowCar = Integer.parseInt(in.readLine());
                int pos = -1;
                if(nowCar < 0) {
                    int lastCar = -nowCar;
                    pos = posArr[lastCar];
                    ans += weights[pos] * prices[lastCar];
                    if(queue.isEmpty()) {
                        parked[pos] = false;
                        cnt--;
                    } else {
                        int nextCar = queue.poll();
                        posArr[nextCar] = pos;
                    }
                }
                else if(nowCar > 0 && cnt == N) {
                    queue.offer(nowCar);
                }
                else {
                    for (int j = 0; j < N; j++) {
                        if(!parked[j]) {
                            pos = j;
                            break;
                        }
                    }
                    parked[pos] = true;
                    posArr[nowCar] = pos;
                    cnt++;
                }
            }

            sb.append("#").append(tc).append(" ").append(String.format("%.0f", ans)).append("\n");
        }

        System.out.println(sb);
    }
}
