// 문제 이해를 잘 못해서 풀이 봤다ㅠㅠ
// -2로 계속 나누기 -> 나머지가 -1이면 몫을 변화시키고 1로 변환(몫이 음수면 -1, 몫이 양수면 +1)

#include <stdio.h>

int main() {
    int N, cnt = 0;
    char result[100];
    scanf("%d", &N);

    if(N == 0)
        printf("0\n");
    else {
        while(N != 0) {
            int mod = N % -2;
            N /= -2;
            if(mod == -1) {
                N = N < 0 ? N - 1 : N + 1;
                mod = 1;
            }
            result[cnt++] = mod;
        }
        for(int i = cnt - 1 ; i >= 0 ; i--) {
            printf("%d", result[i]);
        }
        printf("\n");
    }

    return 0;
}