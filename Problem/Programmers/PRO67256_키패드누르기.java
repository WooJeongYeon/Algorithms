package programmers;

import java.util.HashMap;
import java.util.Map;

public class PRO67256_키패드누르기 {
    String[] keyPad = {"123", "456", "789", "*0#"};
    Point left, right;
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, Point> map = new HashMap<>();
        left = new Point(3, 0);
        right = new Point(3, 2);
        for(int i = 0 ; i < keyPad.length ; i++) {
            for(int j = 0 ; j < keyPad[i].length() ; j++) {
                map.put(keyPad[i].charAt(j) - '0', new Point(i, j));
            }
        }
        for(int i = 0 ; i < numbers.length ; i++) {
            Point pos = map.get(numbers[i]);
            int distL = Math.abs(pos.i - left.i) + Math.abs(pos.j - left.j);
            int distR = Math.abs(pos.i - right.i) + Math.abs(pos.j - right.j);
            if(pos.j == 0 || (pos.j == 1 && (distL < distR || (distL == distR && hand.charAt(0) == 'l')))) {
                sb.append('L');
                left = pos;
            }
            else {
                sb.append('R');
                right = pos;
            }
        }


        return sb.toString();
    }
    class Point {
        int i, j;
        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
