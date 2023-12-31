import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class PRO64062_징검다리건너기 {
	// 시간 줄이려면 어떻게 해야하지 -> dp랑 섞여야할거같은데
	// 자기보다 작은 수까지 합쳐서 연달아 몇개 있는지를 저장하면 되지 않을까?
	// stack으로 매번 ans를 구하면 됨
	// 계속 에러 - 연속으로 숫자 들어간 경우도 저장해줘야 함. 다른 애들이 연속된값 알기 위해
	// + 빼서 계산할 때 같은숫자 빼고 isEmpty이면 x를 0으로 해야 함
	class Solution {
	    public int solution(int[] stones, int k) {
	        int ans = Integer.MAX_VALUE;	
	        Stack<int[]> stack = new Stack<>();  // 내림차순으로 저장되도록
	        for(int i = 0 ; i < stones.length ; i++) {
	            //if(!stack.isEmpty() && stones[i] == stack.peek()[1]) continue;  // 같은수는 저장될 필요 없으니까(아님 저장해야함)
	            while(!stack.isEmpty() && stones[i] > stack.peek()[1]) {
	                int[] arr = stack.pop();
	                int x = arr[0];
	                while(!stack.isEmpty() && arr[1] == stack.peek()[1]) {
	                    x = stack.pop()[0] + 1;
	                }
	                if(!stack.isEmpty()) x = stack.peek()[0] + 1;
	                else x = 0;
	                if(i - x >= k) {
	                    ans = Math.min(ans, arr[1]);
	                }
	            }
	            stack.add(new int[]{i, stones[i]});
	        }
	        while(!stack.isEmpty()) {
	            int[] arr = stack.pop();
	            int x = arr[0];
	            while(!stack.isEmpty() && arr[1] == stack.peek()[1]) {
	                x = stack.pop()[0] + 1;
	            }
	            if(!stack.isEmpty()) x = stack.peek()[0] + 1;
	            else x = 0;
	            if(stones.length - x >= k) {
	                ans = Math.min(ans, arr[1]);
	            }
	        }
	        
	        return ans;
	    }
	}
    public int solution1(int[] stones, int k) {
        int ans = 0, cnt = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2) {
                return arr1[1] - arr2[1];
            }
        });
        for(int i = 0 ; i < stones.length ; i++) {
            pq.offer(new int[]{i, stones[i]});
        }
        boolean isEnd = false;
        while(!pq.isEmpty()) {
            ans = pq.peek()[1];
            int idx = pq.poll()[0];
            if(++cnt >= k) {
                boolean a = false;
                int len = 0;
                // 쪼개서 하는게 아니라 다 봐야 함
                int startIdx = idx - k + 1 >= 0 ? idx - k + 1 : 0;
                int endIdx = idx + k <= stones.length ? idx + k : stones.length;
                for(int i = startIdx ; i < endIdx ; i++) {
                    if(stones[i] <= ans) { 
                        len++;
                    } else {
                        len = 0;
                    }
                    if(len == k) {
                        a = true;
                        break;
                    }
                }
                if(a) {
                    isEnd = true;
                    break;
                }
            }
            if(isEnd) break;
        }
        return ans;
    }
}
