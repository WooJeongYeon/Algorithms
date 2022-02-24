package programmers;
import java.util.*;

public class PRO42890_후보키 {
    boolean[] check;
    int answer, N, M;
    public int solution(String[][] relation) {
        answer = 0;
        N = relation.length;
        M = relation[0].length;
        check = new boolean[(int)Math.pow(2, M)];
        for(int i = 1 ; i < (int)Math.pow(2, M); i++) {
            Map<String, Boolean> map = new HashMap<>();
            boolean isPossible = true;
            for(int j = 1 ; j < i ; j++) {
                if(check[j] && ((j & i) == j)) {
                    isPossible = false;
                    break;
                }
            }
            if(!isPossible) continue;
            for(int r = 0 ; r < N ; r++) {
                String s = "";
                for(int c = 0 ; c < M ; c++) {
                    if(((i >> c) & 1) == 1) s += relation[r][c];
                }
                if(map.getOrDefault(s, false)) {
                    isPossible = false;
                    break;
                }
                map.put(s, true);
            }
            if(!map.isEmpty() && isPossible) {
                answer++;
                check[i] = true;
            }
        }
        return answer;
    }
}
