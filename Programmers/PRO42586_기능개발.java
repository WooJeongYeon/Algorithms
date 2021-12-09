import java.util.ArrayList;
/*
 * Date : 2021.12.09
 * Level : Programmers level 2
 * URL : https://programmers.co.kr/learn/courses/30/lessons/42586
 * Method : 스택/반복
 * Result : ArrayList를 한번에 array로 바꾸고싶다
 */
public class PRO42586_기능개발 {
	public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        int cnt = 1;
        int max = (100 - progresses[0] + speeds[0] - 1) / speeds[0];
        for(int i = 1 ; i < progresses.length ; i++) {
            int endTime = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
            if(endTime <= max) {
                cnt++;
            } else {
                list.add(cnt);    
                max = endTime;
                cnt = 1;
            }
        }
        // ArrayList to Array
        if(cnt > 0) list.add(cnt);
        int[] answer = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
