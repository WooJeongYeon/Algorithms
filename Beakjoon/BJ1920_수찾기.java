
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1920_수찾기 {
	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(in.readLine());
		for(int i = 0 ; i < N ; i++) {
			 arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		st = new StringTokenizer(in.readLine(), " ");
		for(int i = 0 ; i < M ; i++) {
			int n = Integer.parseInt(st.nextToken());
			int result = Arrays.binarySearch(arr, n);
			if(result < 0) result = 0;
			else result = 1;
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}
}

/*public class BJ1920_수찾기 {
	static int N, M;
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

        Set<String> strings = new HashSet<>();
        
        N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(in.readLine());
		for(int i = 0 ; i < N ; i++) {
			strings.add(st.nextToken());
		}
		st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < M; i++) {
            if(strings.contains(st.nextToken())){
                sb.append(1 + "\n");
            }else {
            	sb.append(0 + "\n");
            }
        }
        System.out.println(sb);
    }
}*/