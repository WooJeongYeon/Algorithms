import java.util.Comparator;
import java.util.PriorityQueue;
/*
 * Date : 2022.01.05
 * Level : Programmers level 3
 * Difficulty : 중
 * URL : https://programmers.co.kr/learn/courses/30/lessons/42627
 * Select1 : 
 * Thinking : 가능한 짧은 길이 순으로 Disk를 처리하면 될것같다 
 * 		- PQ를 두개 만들어서 하나는 시간으로, 하나는 길이로 정렬하자
 * 		- 시간순으로 처리
 * Method : Greedy, PriorityQueue, Comparator
 * Result : 생각을 틈틈히 하면서 풀었는데 바로 되네..
 * Plus1 : 시간이 안된 대기큐는 안만들고 jobs를 정렬했어도 됬겠네
 */
public class PRO42627_디스크컨트롤러 {
	class Solution {
	    public int solution(int[][] jobs) {
	        int answer = 0;
	        int time = 0;
	        int N = jobs.length;
	        PriorityQueue<Disk> waitPQ = new PriorityQueue<>(new Comparator<Disk>() {		// 시간이 안된 대기큐
	            public int compare(Disk d1, Disk d2) {
	                return d1.time - d2.time;
	            }
	        });
	        PriorityQueue<Disk> taskPQ = new PriorityQueue<>(new Comparator<Disk>() {		// 가능한 대기큐
	            public int compare(Disk d1, Disk d2) {
	                return d1.len - d2.len;
	            }
	        });
	        for(int i = 0 ; i < N ; i++) {
	            waitPQ.offer(new Disk(jobs[i][0], jobs[i][1]));   
	        }
	        while(!waitPQ.isEmpty() || !taskPQ.isEmpty()) {			// Disk를 모두 처리할 때까지 반복
	            while(!waitPQ.isEmpty() && time >= waitPQ.peek().time) {		// 현재 시간에서 넣을 수 있는 Disk를 가능한 대기큐에 넣음
	                taskPQ.offer(waitPQ.poll());
	            }
	            if(taskPQ.isEmpty()) {			// 처리할 수 있는 Disk가 없으면 시간을 갱신
	                time = waitPQ.peek().time;
	                continue;
	            }
	            Disk now = taskPQ.poll();		
	            answer += (time - now.time) + now.len;
	            time += now.len;
	        }
	        
	        return answer / N;
	    }
	    public class Disk {
	        int time, len;
	        public Disk(int time, int len){
	            this.time = time;
	            this.len = len;
	        }
	    }
	}
}
