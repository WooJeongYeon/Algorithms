import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 너무 화난다ㅋㅋㅋㅋㅋㅋㅜㅠㅠ말의 위치 구해서 양쪽 소랑 비교할 때 각각의 최소 거리를 최소 거리 쌍으로 고려해야 하는데
// 두 경우의 작은 값만 최소 거리 쌍으로 고려했다...ㅠㅠㅠㅠ

public class SW8898_3차원농부 {
    static int TC, N, M, c, ans, cnt;
    static int[] cowArr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        TC = Integer.parseInt(in.readLine());
        for(int tc = 1 ; tc <= TC ; tc++) {
            ans = Integer.MAX_VALUE;
            cnt = 0;
            st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(in.readLine());
            c = Math.abs(Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken()));
            cowArr = new int[N];
            st = new StringTokenizer(in.readLine());
            for(int i = 0 ; i < N ; i++) {
                cowArr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(cowArr);

            st = new StringTokenizer(in.readLine());
            for(int i = 0 ; i < M ; i++) {
                int now = Integer.parseInt(st.nextToken());
                int pos = binarySearch(cowArr, now);

                if(0 <= pos && pos < N) {
                    int dist = Math.abs(cowArr[pos] - now);
                    if(ans > dist) {
                        cnt = 1;
                        ans = dist;
                    } else if(ans == dist) {
                        cnt++;
                    }
                }
                if(0 < pos && pos <= cowArr.length) {
                    int dist = Math.abs(cowArr[pos - 1] - now);
                    if(ans > dist) {            // 이게 계속 밖에있었어ㅠㅠㅠㅠ
                        cnt = 1;
                        ans = dist;
                    } else if(ans == dist) {
                        cnt++;
                    }
                }

            }

            sb.append("#").append(tc).append(" ").append(ans + c).append(" ").append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    private static int binarySearch(int[] arr, int n) {
        int left = 0;
        int right = arr.length - 1;
        int mid = (left + right)/2;

        while (left <= right) {
            mid = (left + right)/2;

            if (arr[mid] == n) {
                return mid;
            } else if (arr[mid] < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (arr[mid] < n)
            mid++;

        return mid;
    }
}