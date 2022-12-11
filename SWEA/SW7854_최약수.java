import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW7854_최약수 {
    static int TC, ans, N;
    static String s;
    static int[][] numArr = {{1, 2, 5, 10, 20, 25, 50}, {100, 125, 200, 250, 500}};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        for(int tc = 1 ; tc <= TC ; tc++) {
            ans = 0;
            s = in.readLine();
            N = Integer.parseInt(s);

            for(int i = 0 ; i < numArr[0].length ; i++) {
                if(N < numArr[0][i])
                    break;
                ans++;
            }

            if(s.length() >= 3) {
                boolean isEnd = false;
                int[] tempArr = numArr[1].clone();
                while(true) {
                    for(int i = 0 ; i < tempArr.length ; i++) {
                        if(N < tempArr[i]) {
                            isEnd = true;
                            break;
                        }
                        ans++;
                        tempArr[i] *= 10;
                    }
                    if(isEnd)
                        break;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }
}
