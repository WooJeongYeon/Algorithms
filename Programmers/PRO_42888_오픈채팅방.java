import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
 * Date : 2021.10.17
 * Level : Programmers Level 2
 * URL : https://programmers.co.kr/learn/courses/30/lessons/42888
 * Select1 : 아이디는 그대로여도 계속 값이 갱신될 수 있음 -> map 사용
 * Thinking) 
 * 		1. record 첫 반복 때 들어오는 애들, 바뀌는 애들인 경우 map 갱신(key는 그대로이므로 값만 변경됨)
 * 		2. 들어오거나 나가는 두 경우에 대해 map의 키(아이디)에 맞는 값(닉네임)을 더해서 answer에 셋팅
 * Method : 문자열, 맵
 * Error1 : answer에 저장하기 위한 idx 무조건 ++해주다가 런타임 에러뜸 -> 들어오거나 나갈때만 ++
 * Result : 프로그래머스 문자열 문제들 너무 싫다. 안쓰고 그냥 풀었는데 짜증난다... 왤케 이런문제들 좋아하는지. 그래도 엄청 어려운줄 알았는데 그건 아니라 다행.
 * Plus1 : 첫번째 탐색 때 result.length -> 2보다 큰 애들 map에 넣기
 * Plus2 : 두번째 탐색 때 첫 글자만 비교하기 -> E or L
 * Plus3 : 결과를 List에 넣고 마지막에 toArray로 String 배열로 바꿔주기
 */
public class PRO_42888_오픈채팅방 {
	public static void main(String[] args) {
		String[] record = new String[10];
        solution(record);
	}
    static String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        List<String> arr = new ArrayList<String>();
		for(String s : record) {
			String[] result = s.split(" ");
			if(result.length > 2) {					// Enter, Change의 경우에만
				map.put(result[1], result[2]);
			}
		}
		for(String s : record) {					// arr에 결과값 넣어줌
			String[] result = s.split(" ");
			if(result[0].charAt(0) == 'E') {
				arr.add(map.get(result[1]) + "님이 들어왔습니다.");
			} else if(result[0].charAt(0) == 'L') {
				arr.add(map.get(result[1]) + "님이 나갔습니다.");
			}
		}
        String[] answer = new String[arr.size()];	// list를 배열로 변환
        arr.toArray(answer);
        return answer;
    }
}
