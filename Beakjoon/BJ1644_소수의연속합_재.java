package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

// 예전에는 arr을 len 구해서 하고 이번에는 list로 만든다음 arr로 바꾼거. 이거 빼고는 예전코드랑 다 같네

public class BJ1644_소수의연속합_재 {
    static int N, ans;
    static boolean[] notDecimal;
    static LinkedList<Integer> list;
    static Integer[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        if(N != 1) {
            notDecimal = new boolean[N + 1];
            list = new LinkedList<>();
            for(int i = 2 ; i <= N ; i++) {
                for(int j = i + i ; j <= N ; j += i) {
                    notDecimal[j] = true;
                }
            }
            for(int i = 2 ; i <= N ; i++) {
                if(!notDecimal[i])
                    list.offer(i);
            }
            arr = list.stream().toArray(Integer[]::new);

            int le = 0, ri = 0;
            int sum = arr[0];
            while (le < arr.length) {
                if (sum <= N) {
                    if (sum == N)
                        ans++;
                    if (ri == arr.length - 1)
                        break;
                    sum += arr[++ri];
                } else {
                    sum -= arr[le++];
                }
            }
        }

        System.out.println(ans);
    }
}
