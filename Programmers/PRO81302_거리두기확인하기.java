import java.util.ArrayList;
/*
 * Date : 2022.01.25
 * Level : Programmers level 2
 * Difficulty : 중하 
 * URL : https://programmers.co.kr/learn/courses/30/lessons/81302
 * Select1 : BFS VS 순열(선택)
 * Thinking : 두 가지 선택해서 거리 비교하는 걸로
 * Method : 순열(2Pn)
 * Error1 : partition 사이에 있을 때, false로 해버림
 * Error2 : 거리 2이고 대각선일 때, 두 가지 경우에 대해 체크해줘야 함
 * Result : if문이 많아져서 헷갈렸다. 더 깔끔하게 하려면 BFS 써야할 듯  
 */
public class PRO81302_거리두기확인하기 {
	final int N = 5;
    final int PERSON = 'P';
    final int EMPTY = 'O';
    final int PARTITION = 'X';
    public int[] solution(String[][] places) {
        int total = places.length;
        int[] answer = new int[total];
        ArrayList<int[]> person;
        for(int n = 0 ; n < total ; n++) {				// TC n개번 실행
            boolean isPossible = true;
            person = new ArrayList<>();
            for(int i = 0 ; i < N ; i++) { 				// 사람 위치를 리스트에 추가
                for(int j = 0 ; j < N ; j++) {
                    if(places[n][i].charAt(j) == PERSON) {
                        person.add(new int[]{i, j});
                    }
                }
            }
            for(int i = 0 ; i < person.size() - 1 ; i++) {		// 2Pn
                for(int j = i + 1 ; j < person.size() ; j++) {
                    int[] p1 = person.get(i);
                    int[] p2 = person.get(j);
                    int dist = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
                    if(dist == 1) {		// 거리가 1이면 그냥 불가
                        isPossible = false;
                        break;
                    }
                    if(dist == 2) {		// 거리 2이면
                        int ni = (p1[0] + p2[0]) / 2;
                        int nj = (p1[1] + p2[1]) / 2;
                        if(((p1[0] + p2[0]) & 1) == 1) {		// 더한게 홀수인 경우는 두 사람이 대각선으로 놓인 것
                        	// 대각선 두 경우를 체크
                            if(places[n][ni].charAt(nj) == PERSON && (places[n][ni + 1].charAt(nj) == EMPTY || places[n][ni].charAt(nj + 1) == EMPTY)) {
                                isPossible = false;
                                break;
                            }
                            else if(places[n][ni].charAt(nj) != PERSON && (places[n][ni].charAt(nj) == EMPTY || places[n][ni + 1].charAt(nj + 1) == EMPTY)) {
                                isPossible = false;
                                break;
                            }
                        } else if(((p1[0] + p2[0]) & 1) == 0 && places[n][ni].charAt(nj) == EMPTY) {  // 더한게 짝수면 두 사람이 나란히 놓인 것
                            isPossible = false;
                            break;
                        }
                    }
                }
                if(!isPossible) break;
            }
            
            if(isPossible) answer[n] = 1;
            else answer[n] = 0;
        }
        
        return answer;
    }
}
