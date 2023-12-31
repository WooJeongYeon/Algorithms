import java.util.*;

class Solution {
    class Solution {
        public int solution(int n) {
            long a = 0, b = 1, tmp;
            for(int i = 2 ; i <= n ; i++) {
                tmp = (a + b) % 1234567;
                a = b;
                b = tmp;
            }
            return (int)b;
        }
    }
}
