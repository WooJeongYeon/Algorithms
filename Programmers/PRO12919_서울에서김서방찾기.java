package programmers;

import java.util.HashMap;
import java.util.Map;

public class PRO12919_서울에서김서방찾기 {
    final String FIND = "Kim";
    final String FORMAT = "김서방은 %d에 있다";
    public String solution(String[] seoul) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0 ; i < seoul.length ; i++) {
            map.put(seoul[i], i);
            if(map.containsKey(FIND)) {
                answer = String.format(FORMAT, i);
                break;
            }
        }
        return answer;
    }
}
