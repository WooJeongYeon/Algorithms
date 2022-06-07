package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2022.06.07
 * Level : BaekJoon Silver 4
 * Method : Greedy
 */
public class BJ13305_주유소 {
    static long ans;
    static int N, minPrice;
    static int[] distArr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        distArr = new int[N - 1];
        minPrice = Integer.MAX_VALUE;
        st = new StringTokenizer(in.readLine());
        for(int i = 0 ; i < N - 1 ; i++) {
            distArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(in.readLine());
        for(int i = 0 ; i < N - 1 ; i++) {
            int price = Integer.parseInt(st.nextToken());
            minPrice = Math.min(minPrice, price);
            ans += (long)minPrice * distArr[i];
        }

        System.out.println(ans);
    }
}
