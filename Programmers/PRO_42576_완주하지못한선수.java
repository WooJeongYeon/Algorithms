import java.util.HashMap;
import java.util.Map;
/*
 * Date : 2021.11.02
 * Level : Programmers Level 1
 * Time : 15분
 * URL : https://programmers.co.kr/learn/courses/30/lessons/42576
 * Thinking : hash라고 되있길래... map 써서 해야징
 * Result : ide에서만 하고싶었는데 map 무슨 메소드있는지 잘 몰라서 여기서 메소드만 확인함 
 * Plus1 : map.put(s, map.getOrDefault(s, 0) + 1)이런식으로 null 체크 안하고도 할 수 있네(map.getOfDefault() 메소드)
 * Plus2 : 결과물 1개이면 map.keySet().iterator().next() 해서 리턴하면 되겠네 
 */
public class PRO_42576_완주하지못한선수 {
	public static void main(String[] args) {
		String[] p = {"leo", "kiki", "eden"};
		String[] c = {"eden", "kiki"};
		System.out.println(solution(p, c));
	}
	
	public static String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        for(String s : participant) {
            if(map.get(s) == null) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }
        for(String s : completion) {
            if(map.get(s) > 1) {
                map.put(s, map.get(s) - 1);
            } else {
                map.remove(s);
            }
        }
        for(String s : map.keySet()) {
            answer = s;
        }
        return answer;
    }
}
