package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 배열 두개(visited, result) 대신 배열 1개(result)로 감 - 현재까지의 순서(번호) 저장하게(질문에서 봄ㅠㅠ)
// plus - 한번 쭉 가면서 표시하고(0 아닌 값들), 순환이면 빼주게... 두번 반복문 돌아서 하네 대단하다

public class BJ9466_텀프로젝트 {
    static int T, N, ans;
    static int[] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(in.readLine());
        for(int tc = 0 ; tc < T ; tc++) {
            N = Integer.parseInt(in.readLine());
            arr = new int[N + 1];
            result = new int[N + 1];
            ans = N;
            st = new StringTokenizer(in.readLine(), " ");
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int idx = 1;
            for(int i = 1 ; i <= N ; i++) {
                if(result[i] > 0) continue;
                int now = i;
                int startIdx = idx;
                while(true) {
                    if(result[now] > startIdx) {
                        ans -= (idx - result[now]);
                        break;
                    }
                    if(result[now] != 0 && result[now] < startIdx)  break;

                    result[now] = idx++;
                    now = arr[now];
                }

            }

            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }

}
