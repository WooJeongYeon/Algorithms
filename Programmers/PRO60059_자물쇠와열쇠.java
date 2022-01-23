/*
 * Date : 2022.01.24
 * Level : Programmers level 3
 * Difficulty : 중
 * Time : 1h 40m
 * URL : https://programmers.co.kr/learn/courses/30/lessons/60059
 * Select1 : 반복문이 엄청 많아져서 함수로 나눠서 구현
 * Thinking)
 * 		1. Key를 4번 회전
 * 			- Lock배열에서 0 ~ M + N - 2안에 속하는 요소 선택해 i, j를 감소시켜가면서 Key와 일치시켜 가능한지를 판단
 * 			- 이 때 Lock의 홈이 마스킹되는 범위를 벗어나면 다음으로 고고
 * Method : 배열 회전, 배열
 * Error1 : 회전할때 새로운 배열에 만들어야 함
 * Error2 : 런타임 에러 - 회전할 때 M을 N으로 써서 크기 다른 경우 인덱스 나가버림
 * Result : 기준점 잡는거, 어떻게 마스킹씌워서 비교해야할지 고민을 많이함
 */
import java.util.*;

public class PRO60059_자물쇠와열쇠 {
	class Solution {
	    int[][] keyMap;
	    int[][] lockMap;
	    int M, N;
	    LinkedList<int[]> lockHomeList;
	    public boolean solution(int[][] key, int[][] lock) {
	        boolean answer = false;
	        lockHomeList = new LinkedList<>();
	        keyMap = key;
	        lockMap = lock;
	        M = key.length;
	        N = lock.length;
	        for(int i = 0 ; i < N ; i++) {		// 자물쇠의 홈 위치들 저장
	            for(int j = 0 ; j < N ; j++) {
	                if(lockMap[i][j] == 0) {
	                    lockHomeList.offer(new int[]{i, j});
	                }
	            }
	        }
	        for(int n = 0 ; n < 4 ; n++) {
	            rotateKeyMap();			// 회전
	            if(getIsOpen()) {
	                answer = true;
	                break;
	            }
	        }
	        return answer;
	    }
	    void rotateKeyMap() {
	        int[][] newArr = new int[M][M];     // 1. 새로운 배열에 만들어야 함
	        for(int i = 0 ; i < M ; i++) {
	            for(int j = 0 ; j < M ; j++) {
	                newArr[M - j - 1][i] = keyMap[i][j];
	            }
	        }
	        keyMap = newArr;
	    }
	    boolean getIsOpen() {
	        for(int i = 0 ; i < M + N - 1 ; i++) {		
	            for(int j = 0 ; j < M + N - 1 ; j++) {
	                if(compare(i, j)) return true;
	            }
	        }
	        return false;
	    }
	    boolean compare(int startLockI, int startLockJ) {
	        // lock의 홈 부분이 범위에 포함되지 않으면 false
	        for(int[] point : lockHomeList) {
	            if(point[0] <= startLockI && point[0] >= (startLockI - M + 1) && point[1] <= startLockJ && point[1] >= (startLockJ - M + 1))
	                continue;
	            return false;
	        }
	        for(int i = 0 ; i < M ; i++) {		// Key와 Lock 비교범위 모두 i, j 마지막에서 감소시켜가면서 비교
	            for(int j = 0 ; j < M ; j++) {
	                //if(isOutOfIdx(M - 1 - i, M - 1 - j, M)) continue;				// Key는 범위 벗어나지 않음
	                if(isOutOfIdx(startLockI - i, startLockJ - j, N)) continue;		// Lock 범위 벗어나면 continue
	                if(keyMap[M - 1 - i][M - 1 - j] == lockMap[startLockI - i][startLockJ - j] ) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	    boolean isOutOfIdx(int i, int j, int size) {
	        return i < 0 || i >= size || j < 0 || j >= size;
	    }
//	    void print(int[][] map) {
//	        System.out.println("--------------------------");
//	        for(int i = 0 ; i < map.length ; i++) {
//	            for(int j = 0 ; j < map[0].length ; j++) {
//	                System.out.print(map[i][j] + " ");
//	            }
//	            System.out.println();
//	        }
//	    }
	}
}
