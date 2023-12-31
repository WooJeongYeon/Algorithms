class Solution {
	public int solution(int num) {
		int answer = 0;
		long n = num;
		while(n != 1L) {
			if(answer == 500) {
				answer = -1;
				break;
			}
			if(n % 2 == 0) n /= 2L;
			else n = n * 3 + 1;
			answer++;
		}
		return answer;
	}
}