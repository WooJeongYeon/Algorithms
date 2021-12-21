
public class PRO1829_카카오프렌즈컬러링북 {
	class Solution {
	    int sizeOfNowArea;
	    int[] di = {-1, 1, 0, 0};
	    int[] dj = {0, 0, -1, 1};
	    boolean[][] visited;
	    public int[] solution(int m, int n, int[][] picture) {
	        int numberOfArea = 0;
	        int maxSizeOfOneArea = 0;
	        visited = new boolean[m][n];
	        for(int i = 0 ; i < m ; i++) {
	            for(int j = 0 ; j < n ; j++) {
	                if(picture[i][j] == 0 || visited[i][j]) continue;
	                numberOfArea++;
	                sizeOfNowArea = 0;
	                dfs(picture[i][j], i, j, m, n, picture);
	                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, sizeOfNowArea);
	            }
	        }
	        
	        int[] answer = new int[2];
	        answer[0] = numberOfArea;
	        answer[1] = maxSizeOfOneArea;
	        return answer;
	    }
	    void dfs(int num, int i, int j, int m, int n, int[][] picture) {
	        visited[i][j] = true;
	        sizeOfNowArea++;
	        for(int d = 0 ; d < 4 ; d++) {
	            int ni = i + di[d];
	            int nj = j + dj[d];
	            if(ni >= 0 && ni < m && nj >= 0 && nj < n && !visited[ni][nj] && picture[ni][nj] == num)
	                dfs(num, ni, nj, m, n, picture);
	        }
	    }
	}
}
