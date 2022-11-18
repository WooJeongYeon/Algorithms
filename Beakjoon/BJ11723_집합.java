package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11723_집합 {
    static int M;
    static boolean[] arr, allArr, emptyArr;
    static final int N = 20;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        arr = new boolean[N + 1];
        allArr = new boolean[N + 1];
        Arrays.fill(allArr, true);
        emptyArr = new boolean[N + 1];

        M = Integer.parseInt(in.readLine());
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine());
            String op = st.nextToken();
            int num;

            switch(op) {
                case "add" :
                    num = Integer.parseInt(st.nextToken());
                    arr[num] = true;
                    break;
                case "remove" :
                    num = Integer.parseInt(st.nextToken());
                    arr[num] = false;
                    break;
                case "check" :
                    num = Integer.parseInt(st.nextToken());
                    if(arr[num])
                        sb.append(1).append("\n");
                    else
                        sb.append(0).append("\n");
                    break;
                case "toggle" :
                    num = Integer.parseInt(st.nextToken());
                    arr[num] = !arr[num];
                    break;
                case "all" :
                    arr = allArr.clone();
                    break;
                case "empty" :
                    arr = emptyArr.clone();
                    break;
            }
        }

        System.out.println(sb);
    }
}
