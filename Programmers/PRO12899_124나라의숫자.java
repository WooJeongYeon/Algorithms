import java.util.*;
class Solution {
    public String solution(int n) {
        int[] changeNum = {1, 2, 4};
        StringBuilder sb = new StringBuilder();
        int len = 1;
        int result = 9;
        while(true) {
            if(n <= (result - 3) / 2) {
                n = n - ((result / 3) - 3) / 2 - 1;
                break;
            }
            len++;
            result *= 3;
        }
        while(n > 0){
            sb.insert(0, changeNum[n % 3]);
            n /= 3;
        }
        while(sb.length() < len) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }
}