package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SW1221_GNS {
    static int TC, N;
    static Map<String, Integer> numMap;
    static int[] countArr;
    static String[] numStrArr = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        numMap = new HashMap<>();
        for(int i = 0 ; i < numStrArr.length ; i++) {
            numMap.put(numStrArr[i], i);
        }

        TC = Integer.parseInt(in.readLine());
        for(int tc = 1 ; tc <= TC ; tc++) {
            st = new StringTokenizer(in.readLine());
            st.nextToken();
            countArr = new int[numStrArr.length];
            N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(in.readLine());
            for(int i = 0 ; i < N ; i++) {
                countArr[numMap.get(st.nextToken())]++;
            }

            sb.append("#").append(tc).append("\n");
            for(int i = 0 ; i < numStrArr.length ; i++) {
                for(int j = 0 ; j < countArr[i] ; j++) {
                    sb.append(numStrArr[i]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
