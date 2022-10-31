package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SW1240_단순2진암호코드 {
    static int TC, N, M, ans;
    static String code;
    static Map<String, Integer> numMap;
    static String[] numBitArr = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
    static int[] pwArr;
    static final int CODE_LEN = 8;
    static final int PW_LEN = 7;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        setNumMap();
        TC = Integer.parseInt(in.readLine());

        for(int tc = 1 ; tc <= TC ; tc++) {
            st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ans = 0;
            pwArr = new int[CODE_LEN];

            boolean isFind = false;
            for(int i = 0 ; i < N ; i++) {
                String line = in.readLine();
                if(isFind)
                    continue;
                for(int j = M - 1 ; j >= 0 ; j--) {
                    if(line.charAt(j) == '1') {
                        code = line.substring(j - 55, j + 1);
                        isFind = true;
                        break;
                    }
                }
            }

            for(int i = 0 ; i < CODE_LEN ; i++) {
                pwArr[i] = numMap.get(code.substring(i * PW_LEN, (i + 1) * PW_LEN));
                ans += pwArr[i];
            }

            int oddSum = pwArr[0] + pwArr[2] + pwArr[4] + pwArr[6];
            int evenSum = ans - oddSum;
            if((oddSum * 3 + evenSum) % 10 != 0)
                ans = 0;

            sb.append("#" + tc + " " + ans + "\n");
        }

        System.out.println(sb);
    }
    private static void setNumMap() {
        numMap = new HashMap<>();
        for(int i = 0 ; i < numBitArr.length ; i++) {
            numMap.put(numBitArr[i], i);
        }
    }
}
