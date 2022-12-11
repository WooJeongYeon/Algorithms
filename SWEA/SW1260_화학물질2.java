import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1260_화학물질2 {
    static int TC, N, matCnt, ans;
    static int[][] map, dp;
    static Matrix[] mat;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        for(int tc = 1 ; tc <= TC ; tc++) {
            ans = 0;
            matCnt = 0;
            N = Integer.parseInt(in.readLine());
            map = new int[N][N];
            mat = new Matrix[20];
            for(int i = 0 ; i < N ; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    if(map[i][j] != 0) {
                        findMat(i, j);
                    }
                }
            }

            int[][] cntArr = new int[N][2];
            for(int i = 0 ; i < matCnt ; i++) {
                cntArr[mat[i].row][0]++;
                cntArr[mat[i].row][1] = i;
            }
            for(int i = 0 ; i < matCnt ; i++) {
                if(cntArr[mat[i].col][0] != 0)
                    cntArr[mat[i].col][0]--;
            }
            int startIdx = 0;
            for(int i = 0 ; i < N ; i++) {
                if(cntArr[i][0] == 1) {
                    startIdx = cntArr[i][1];
                }
            }

            swap(0, startIdx);
            for(int i = 1 ; i < matCnt - 1 ; i++) {
                int nextRow = mat[i - 1].col;
                for(int j = i + 1 ; j < matCnt ; j++) {
                    if(mat[j].row == nextRow) {
                        swap(i, j);
                        break;
                    }
                }
            }

            dp = new int[matCnt][matCnt];

            for(int r = 1 ; r < matCnt ; r++) { // MCM을 구할 행렬 개수...1부터(행렬 2개 최소곱, 3개의 최소곱...)
                for(int i = 0 ; i < matCnt - r ; i++) {
                    int j = i + r;  // 곱할 두번째 행렬 선택
                    int min = Integer.MAX_VALUE;
                    for(int k = i ; k < j ; k++) {      // 사이에 있는 애들 중 가장 최소곱 구하도록
                        min = Math.min(min, dp[i][k] + dp[k + 1][j] + mat[i].row * mat[k].col * mat[j].col);
                    }
                    dp[i][j] = min;
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[0][matCnt - 1]).append("\n");
        }

        System.out.println(sb);
    }

    static void swap(int i, int j) {
        Matrix tmp = mat[i];
        mat[i] = mat[j];
        mat[j] = tmp;
    }

    private static void findMat(int si, int sj) {
        int rCnt = 1, cCnt = 1;

        while((si + rCnt) < N && map[si + rCnt][sj] != 0)
            rCnt++;
        while((sj + cCnt) < N && map[si][sj + cCnt] != 0)
            cCnt++;

        for(int i = 0 ; i < rCnt ; i++) {
            for(int j = 0 ; j < cCnt ; j++) {
                map[si + i][sj + j] = 0;
            }
        }

        mat[matCnt++] = new Matrix(rCnt, cCnt);
    }

    static class Matrix {
        int row;
        int col;
        public Matrix(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}