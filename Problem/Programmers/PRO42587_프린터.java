/*
 * Date : 2021.12.07
 * Level : Programmers level 2
 * Difficulty : 중...
 * URL : https://programmers.co.kr/learn/courses/30/lessons/42587
 * Select1 : 리스트 VS 큐 VS 배열(선택)
 * Select2 : 눴다뺐다할까 VS 인덱스만 이동(선택)
 * Method : 큐(..?) 
 * Result : 쉬울줄알고 바로 풀었다가 땡... 다시 생각해서 풀었음ㅠ 왤케 문제들이 어렵지. 진짜ㅠㅠㅠ
 * Plus1 : 큐로 안풀고 매 for문마다 내림차순으로 없어지게? 하는걸로도 푸네. 아직 이해 다 못했다ㅠㅠ
 */
public class PRO42587_프린터 {
	public int solution(int[] priorities, int location) {
        int answer = 0;
        int size = priorities.length;
        int idx = 0;
        while(true) {
            if(priorities[idx] == 0) {
                idx = (idx + 1) % size;
                continue;
            }
            int n = priorities[idx];
            int nowIdx = (idx + 1) % size;
            boolean isPossible = true;
            while(nowIdx != idx) {
                if(n < priorities[nowIdx]) {
                    isPossible = false;
                    break;
                }
                nowIdx = (nowIdx + 1) % size;
            }
            if(isPossible) {
                answer++;
                if(location == idx) break;
                priorities[idx] = 0;
            } 
            idx = (idx + 1) % size;
        }
        return answer;
    }
}
