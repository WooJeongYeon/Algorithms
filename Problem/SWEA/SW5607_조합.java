import java.util.Scanner;
/*
 * Date : 2021.09.29
 * Level : SWEA
 * Method : 페르마의정리..?
 */
public class SW5607_조합 {
	static final long NUM = 1234567891L;
	static int TC, N, R;
	static long ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		TC = sc.nextInt();
		for(int tc = 1 ; tc <= TC ; tc++) {
			N = sc.nextInt();
			R = sc.nextInt();
			ans = nCr(N, R, NUM);
			
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	static long nCr(int n, int r, long p) {
		if(r == 0) return 1L;
		long[] fac = new long[n + 1];
		fac[0] = 1;
		
		for(int i = 1 ; i <= n ; i++) 
			fac[i] = fac[i - 1] * i % p;
		return (fac[n] * power(fac[r], p - 2, p) % p * power(fac[n - r], p - 2, p) % p) % p;
	}
	static long power(long x, long y, long p) {
		long res = 1L;
		x = x % p;
		while(y > 0) {
			if(y % 2 == 1)
				res = (res * x) % p;
			y = y >> 1;
			x = (x * x) % p;
		}
		return res;
	}
}