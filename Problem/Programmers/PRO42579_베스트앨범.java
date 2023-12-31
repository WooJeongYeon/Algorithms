import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
/*
 * Date : 2021.12.13
 * Level : Programmers level 3
 * Difficulty : 중
 * URL : https://programmers.co.kr/learn/courses/30/lessons/42579
 * Select1 : 정렬할 애들 다 클래스로 만들어 Comparable 구현해 정렬되게 -> PQ 사용
 * Thinking : String이 index가 되도록 해서 저장하게... -> Map
 * Method : Map 사용
 * Error1 : 거의 반은 틀리는거여... 코드는 진짜 뭐가 틀렸는지 모르겠고
 * Help : PQ는 빼면서 오름차순/내림차순으로 빼는거임. 내부적으로 완전히 정렬되어 있지는 않음
 * 		-> 이거 for문으로 순차적으로 접근하면 크기순으로 접근이 안됨 -> 하나씩 빼면서 접근해야 함!  
 * Result : 자료구조 잘 써먹어야 겠다...
 * 			- PQ 사용할 때 for문으로 순차적으로 접근하지 말기!!! 테케 엄청 찾아보다가 알았다ㅠㅠ
 */
public class PRO42579_베스트앨범 {
	class Solution {
	    public int[] solution(String[] genres, int[] plays) {
	        LinkedList<Integer> result = new LinkedList<>();
	        Map<String, PriorityQueue<Music>> map = new HashMap<>();
	        PriorityQueue<Genre> genreList = new PriorityQueue<>();
	        for(int i = 0 ; i < genres.length ; i++){
	            PriorityQueue<Music> musics = map.get(genres[i]);
	            if(musics == null) {
	                musics = new PriorityQueue<>();
	                map.put(genres[i], musics);
	            }
	            musics.add(new Music(i, plays[i]));
	        }
	        
	        for(String genre : map.keySet()) {
	            PriorityQueue<Music> musics = map.get(genre);
	            int sum = 0;
	            for(Music m : musics) {
	                sum += m.plays;
	            }
	            genreList.offer(new Genre(genre, sum));
	        }
	        
	        while(!genreList.isEmpty()) {
	            Genre genre = genreList.poll();
	            PriorityQueue<Music> musics = map.get(genre.name);
	            //System.out.println(genre.totalPlays);
	            result.add(musics.poll().idx);
	            if(!musics.isEmpty()) {
	                result.add(musics.poll().idx);
	            }
	        }
	        
	        
	        int[] answer = new int[result.size()];
	        int i = 0;
	        for(Integer idx : result) {
	            answer[i++] = idx;
	        }
	        return answer;
	    }
	    public class Genre implements Comparable<Genre>{
	        String name;
	        int totalPlays;
	        public Genre(String name, int totalPlays) {
	            this.name = name;
	            this.totalPlays = totalPlays;
	        }
	        public int compareTo(Genre o) {
	            return o.totalPlays - this.totalPlays;
	        }
	    }
	    public class Music implements Comparable<Music>{
	        int idx, plays;
	        public Music(int idx, int plays) {
	            this.idx = idx;
	            this.plays = plays;
	        }
	        public int compareTo(Music o) {
	            if(this.plays > o.plays) return -1;
	            else if(this.plays < o.plays) return 1;
	            else return this.idx - o.idx;
	        }
	    }
	}
}
