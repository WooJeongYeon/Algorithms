/*
 * Date : 2022.01.07
 * Level : Programmers level 2
 * Difficulty : 중하
 * Time : 30m 
 * URL : https://programmers.co.kr/learn/courses/30/lessons/1835
 * Select1 : 조건들을 어떻게 설정할까.. 각 자리에 알파벳을 놔볼까? 하다가 8개인걸 보고 순열로 풀어야 하구나 깨닳음
 * Select2 : 맞는지 확인하는걸 또 어떻게할까, <나 >면 for문 돌려서 한 알파벳 기준으로 - or +해서 있는지 확인해볼까 하다가
 * 			그냥 sub 구해서 해당 범위 내에 있는지만 확인해주면 되구나 생각함
 * Thinking : 순열로 순서 만듬(8!)
 * 			- 조건이 일치하면 answer++
 * Method : 순열
 * Result : 잘못 생각할뻔. 순열이라는걸 알아차려서 다행이다
 */

public class PRO1835_단체사진찍기 {
	class Solution {
	    int SIZE = 8, answer = 0, N;
	    boolean[] visited;
	    String[] conditions;
	    String s = "ACFJMNRT";
	    public int solution(int n, String[] data) {
	        visited = new boolean[SIZE];
	        N = n;
	        conditions = data;
	        perm(0, "");
	        
	        return answer;
	    }
	    public void perm(int idx, String order) {
	        if(idx == SIZE) {
	            if(isValid(order)) {
	                answer++;
	            }
	            return;
	        }
	        for(int i = 0 ; i < SIZE ; i++) {
	            if(visited[i]) continue;
	            visited[i] = true;
	            perm(idx + 1, order + s.charAt(i));
	            visited[i] = false;
	        }
	    }
	    public boolean isValid(String order) {
	        for(int i = 0 ; i < N ; i++) {
	            char a = conditions[i].charAt(0);
	            char b = conditions[i].charAt(2);
	            char op = conditions[i].charAt(3);
	            int num = (conditions[i].charAt(4) - '0') + 1;
	            int start = 1, end = 7;
	            int sub = Math.abs(order.indexOf(a) - order.indexOf(b));
	            switch(op) {
	                case '=' :
	                    start = end = num;
	                    break;
	                case '<' :
	                    end = num - 1;
	                    break;
	                case '>' :
	                    start = num + 1;
	                    break;
	            }
	            if(sub < start || sub > end) return false;
	        }
	        return true;
	    }
	}
}
