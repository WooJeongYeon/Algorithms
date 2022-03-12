package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 숫자가 2자리 이상인걸 생각 안함
// 인덱스 나가거나, start = end + 1인 경우는 배열이 비었을 때임! => 1씩 간격을 더 줘서 break 걸어줘야 함
// 추가 - 숫자가 여러자리여도 split 안하고 변환할 수 있네...
// 추가 - 인덱스 나가는거 신경쓸 필요 없구나. start > end로 걸러져
public class BJ5430_AC {
    static int TC;
    static StringBuilder result;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(in.readLine());
        result = new StringBuilder();
        for(int tc = 0 ; tc < TC ; tc++) {
            String commands = in.readLine();
            int N = Integer.parseInt(in.readLine());
            String s = in.readLine();
            Boolean isPossible = true;
            s = s.substring(1, s.length() - 1);

            int startIdx = 0, endIdx;
            int dir = 0;
            int[] numberArr = new int[s.split(",").length];
            if(s.length() > 0) {
                int idx = 0;
                for (String numString : s.split(",")) {
                    numberArr[idx++] = Integer.parseInt(numString);
                }
                endIdx = numberArr.length - 1;
            }
            else endIdx = -1;

            for(int i = 0 ; i < commands.length() ; i++) {

                char command = commands.charAt(i);
                if(command == 'R') {
                    dir = 1 - dir;
                } else {
                    if(startIdx > endIdx) {
                        isPossible = false;
                        break;
                    }
                    if(dir == 0) startIdx++;
                    else endIdx--;
                }
            }
            if(isPossible) {
                StringBuilder ans = new StringBuilder();
                if(dir == 0) {
                    for(int i = startIdx ; i <= endIdx ; i++) {
                        ans.append(numberArr[i]);
                        if(i == endIdx) break;
                        ans.append(",");
                    }
                }
                else {
                    for(int i = endIdx ; i >= startIdx ; i--) {
                        ans.append(numberArr[i]);
                        if(i == startIdx) break;
                        ans.append(",");
                    }
                }

                result.append("[" + ans + "]\n");
            } else {
                result.append("error\n");
            }
        }
        System.out.println(result);
    }
}
