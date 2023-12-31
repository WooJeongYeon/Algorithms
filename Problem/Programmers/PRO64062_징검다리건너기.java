import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class PRO64062_¡�˴ٸ��ǳʱ� {
	// �ð� ���̷��� ��� �ؾ����� -> dp�� �������ҰŰ�����
	// �ڱ⺸�� ���� ������ ���ļ� ���޾� � �ִ����� �����ϸ� ���� ������?
	// stack���� �Ź� ans�� ���ϸ� ��
	// ��� ���� - �������� ���� �� ��쵵 ��������� ��. �ٸ� �ֵ��� ���ӵȰ� �˱� ����
	// + ���� ����� �� �������� ���� isEmpty�̸� x�� 0���� �ؾ� ��
	class Solution {
	    public int solution(int[] stones, int k) {
	        int ans = Integer.MAX_VALUE;	
	        Stack<int[]> stack = new Stack<>();  // ������������ ����ǵ���
	        for(int i = 0 ; i < stones.length ; i++) {
	            //if(!stack.isEmpty() && stones[i] == stack.peek()[1]) continue;  // �������� ����� �ʿ� �����ϱ�(�ƴ� �����ؾ���)
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
                // �ɰ��� �ϴ°� �ƴ϶� �� ���� ��
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
