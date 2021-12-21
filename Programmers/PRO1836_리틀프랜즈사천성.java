
/*
 * Date : 2021.12.22
 * Level : Programmers level 3
 * Difficulty : 중상
 * Why : 다 풀고 테케는 맞는데 왜 안되는지 못찾아서 질문 참고함
 * URL : https://programmers.co.kr/learn/courses/30/lessons/1836#
 * Select1 : 가장 먼저 나오는 게 답!
 * Select2 : 같은 행인애, 같은 열인애 체크하는거 행, 열 모두 다를 때 재활용 되서 메소드를 뺌
 * Thinking : 알파벳 크기만큼 TileGroup 배열을 생성해 알파벳 위치들 저장 
 * Method : 재귀
 * Error1 : IDLE 없이 하니까 다양한 에러가 나긴했었다..
 * Error2 : 틀렸다 나옴 -> 모든 알파벳을 없앤 경우만 답으로 저장해줘야 함! -> 이걸 체크해줌
 * Help : 도저히 모르겠어서 질문 참고해서 나랑 딱 맞는 상황을 찾았다ㅠㅠㅠ
 * Result : 풀만할 줄 알았는데 왤케 어렵지ㅠㅠㅠ 레벨 3짜리들 너무 어렵다. 좀더 열심히 해야돼ㅠㅠㅠ 
 */
public class PRO1836_리틀프랜즈사천성 {
	class Solution {
	    String answer;
	    TileGroup[] alphaTile;
	    char[][] map;
	    int size, alphaCnt;
	    public String solution(int m, int n, String[] board) {
	        map = new char[m][n];
	        size = 'z' - 'a' + 1;
	        answer = "";
	        alphaTile = new TileGroup[size];
	        for(int i = 0 ; i < m ; i++) {
	            map[i] = board[i].toCharArray();
	        }
	        int[] alphaIdx = new int[size];
	        for(int i = 0 ; i < m ; i++) {
	            for(int j = 0 ; j < n ; j++) {			// 알파벳 위치들 저장해놓음
	                if(map[i][j] == '.' || map[i][j] == '*') continue;
	                if(alphaIdx[map[i][j] - 'A'] == 0) {
	                    alphaCnt++;
	                    alphaTile[map[i][j] - 'A'] = new TileGroup(i, j);
	                    alphaIdx[map[i][j] - 'A']++;
	                } else {
	                    alphaTile[map[i][j] - 'A'].ei = i;
	                    alphaTile[map[i][j] - 'A'].ej = j;
	                }
	            }
	        }
	        game(0, "");
	        if(answer.equals("")) answer = "IMPOSSIBLE";
	        
	        
	        return answer;
	    }
	    boolean game(int idx, String s) {
	        while(idx < size) {				// 없는 알파벳의 경우, 인덱스 패스해주고 있는 알파벳을 찾음
	            if(alphaTile[idx] != null) break;
	            idx++;
	        }
	        if(idx >= size) {				// 마지막에 도달했고
	            if(s.length() == alphaCnt) {	// 모든 알파벳을 없앴다면(이걸 안해줬었음)
	                answer = s;					// 답을 찾고 true 리턴
	                return true;
	            }
	            else return false;
	        }
	        //System.out.println(idx + " " + s);
	        
	        TileGroup now = alphaTile[idx];
	        if(isDelete(idx)) {			// 삭제가 가능하면
	            alphaTile[idx] = null;
	            map[now.si][now.sj] = '.';
	            map[now.ei][now.ej] = '.';
	            
	            if(game(0, s + (char)('A' + idx))) return true;	// 알파벳 A부터 다시 탐색 -> 알파벳순으로 찾기 위해
	    
	            alphaTile[idx] = now;
	            map[now.si][now.sj] = (char)(idx + 'A');
	            map[now.ei][now.ej] = (char)(idx + 'A');
	        }	
	        else {						// 삭제 불가능하면 다음 인덱스로
	            if(game(idx + 1, s)) return true;
	        }
	        return false;
	    }
	    boolean isDelete(int idx) {			// 삭제할 수 있는지
	        TileGroup now = alphaTile[idx];
	        if(now.si == now.ei) {			// 1) 같은 행에 있는 경우
	            return isSameI(now.sj, now.ej, now.si);
	        } else if(now.sj == now.ej) {	// 2) 같은 열에 있는 경우
	            return isSameJ(now.si, now.ei, now.sj);
	        } else {						// 3) 행과 열이 다른 경우
	            if(isSameJ(now.si, now.ei, now.sj) && isSameI(now.sj, now.ej, now.ei) 
	               && map[now.ei][now.sj] == '.') return true;
	            if(isSameJ(now.si, now.ei, now.ej) && isSameI(now.sj, now.ej, now.si) 
	                && map[now.si][now.ej] == '.') return true;
	            return false;
	        }
	    }
	    boolean isSameJ(int si, int ei, int nj) {		// j가 같을 때 그 사이 가능한지
	        int now = Math.min(si, ei) + 1;
	        int end = Math.max(si, ei);
	        for(; now < end ; now++) {
	            if(map[now][nj] != '.') return false;
	        }
	        return true;
	    }
	    boolean isSameI(int sj, int ej, int ni) {		// i가 같을 때 그 사이 가능한지
	        int now = Math.min(sj, ej) + 1;
	        int end = Math.max(sj, ej);
	        for(; now < end ; now++) {
	            if(map[ni][now] != '.') return false;
	        }
	        return true;
	    }
	    class TileGroup {					// 타일 2개 묶음
	        int si, sj, ei, ej;
	        public TileGroup(int si, int sj) {
	            this.si = si;
	            this.sj = sj;
	        }
	    }
	}
}
