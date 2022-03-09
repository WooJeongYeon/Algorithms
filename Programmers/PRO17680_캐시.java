package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// LRU 이해를 못하고 푼 듯
// 1. 같은 값이 들어온 경우, s의 캐시에 들어간 위치만 뒤쪽으로 갱신됨(list 길이는 그대로) -> list 사용해야 함
public class PRO17680_캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        if(cacheSize == 0) answer += cities.length * 5;
        else {
            for(String s : cities) {
                s = s.toUpperCase();
                // 포함되어있더라도 마지막 애로 위치 갱신해줘야 함
                if(set.contains(s)) {
                    answer += 1;
                    list.remove(s);
                    list.add(s);
                }
                if(!set.contains(s)) {
                    answer += 5;
                    if(list.size() == cacheSize) {
                        set.remove(list.remove(0));
                    }
                    list.add(s);
                    set.add(s);
                }
            }
        }
        return answer;
    }
}
