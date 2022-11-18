package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11723_집합_비트 {
    static int M, N;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = 0;

        M = Integer.parseInt(in.readLine());
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine());
            String op = st.nextToken();
            int num = 0;
            if(st.hasMoreTokens())
                num = Integer.parseInt(st.nextToken());


            switch(op) {
                case "add" :
                    N = N | (1 << num);
                    break;
                case "remove" :
                    N = N & ~(1 << num);
                    break;
                case "check" :
                    if((N & (1 << num)) != 0)
                        sb.append(1).append("\n");
                    else
                        sb.append(0).append("\n");
                    break;
                case "toggle" :
                    N = N ^ (1 << num);
                    break;
                case "all" :
                    N = (1 << 21) - 1;
                    break;
                case "empty" :
                    N = 0;
                    break;
            }
        }

        System.out.println(sb);
    }
}
