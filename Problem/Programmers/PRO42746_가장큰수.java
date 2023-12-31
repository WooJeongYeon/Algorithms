package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PRO42746_가장큰수 {
    public String solution(int[] numbers) {
        return getResult(numbers);
    }

    String getResult(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int cnt1 = 1, cnt2 = 1;
            int newO1 = o1, newO2 = o2;
            while(newO1 >= 10) {
                newO1 /= 10;
                cnt1++;
            }
            while(newO2 >= 10) {
                newO2 /= 10;
                cnt2++;
            }
            int n1 = o1 * (int)Math.pow(10, cnt2) + o2;
            int n2 = o2 * (int)Math.pow(10, cnt1) + o1;
            return n2 - n1;
        });
        for(int i = 0 ; i < numbers.length ; i++) {
            pq.offer(numbers[i]);
        }

        if(pq.peek() == 0)
            return "0";

        for(int i = 0 ; i < numbers.length ; i++) {
            sb.append(pq.poll());
        }

        return sb.toString();
    }
}
