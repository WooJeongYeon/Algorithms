package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2022.02.13
 * Level : BaekJoon Sliver 5
 * Difficulty : 중
 * URL : https://www.acmicpc.net/problem/2343
 * Thinking : 블루레이 최솟값을 찾기 위해 이분탐색을 사용해 매번 검증해보자!
 * Method : Binary Search
 * Error1 : 맞은거 같은데 계속 틀렸대..ㅠㅠㅠ -> 질문에서 반례 찾았다! 블루레이 크기는 강의보다 무조건 커야 함!
 * Error1 : 불루레이 개수가 M개 이하면 무조건 나누기 가능!
 * Help : 질문에서 반례 찾아서 넣어보고 생각해봄ㅠㅠㅠ
 * Result : 반례 작작 보고 생각을 하자ㅠㅠㅠ
 * Plus1 : min값을 가장 강의 길이가 큰 애로 저장하면 while문에서 따로 체크 안해줘도 됨!
 */
public class Bj2343_기타레슨 {
    static int N, M;
    static int min = 1, mid, max, ans;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        ans = Integer.MAX_VALUE;
        st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max += arr[i];
            min = Math.max(min, arr[i]);        // 이렇게 하면 밑에서 체크 안해줘도 됨
        }
        while(min <= max) {
            mid = (min + max) / 2;
            int cnt = 0;
            int sum = 0;
            for(int i = 0 ; i < N ; i++) {
//                if(arr[i] > mid ) {         // 검사하는 길이를 넘어가는 경우, 무조건 불가능 -> 길이 늘려야 함
//                    cnt = M + 1;
//                    break;
//                }
                if(sum + arr[i] > mid) {
                    cnt++;
                    sum = arr[i];
                } else sum += arr[i];
            }
            if(sum > 0) cnt++;
            if(cnt > M) {
                min = mid + 1;
            } else {        // 불루레이 개수가 M개 이하면 무조건 나누기 가능!
                ans = Math.min(ans, mid);
                max = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
