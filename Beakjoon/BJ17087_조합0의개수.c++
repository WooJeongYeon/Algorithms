#include <stdio.h>
#define SIZE2 31
#define SIZE5 14

int sqrtArr2[SIZE2];
int sqrtArr5[SIZE5];

int main() {
    int N, M, L;
    int cntA2 = 0, cntA5 = 0;

    sqrtArr2[1] = 2;
    for(int i = 2 ; i < SIZE2 ; i++) {
        sqrtArr2[i] = sqrtArr2[i - 1] * 2;
    }
    
    sqrtArr5[1] = 5;
    for(int i = 2 ; i < SIZE5 ; i++) {
        sqrtArr5[i] = sqrtArr5[i - 1] * 5;
    }

    scanf("%d %d", &N, &M);
    L = N - M;

    for(int i = 1 ; i < SIZE2 ; i++) {
        cntA2 += N / sqrtArr2[i];
        cntA2 -= (M / sqrtArr2[i] + L / sqrtArr2[i]);
    }

    for(int i = 1 ; i < SIZE5 ; i++) {
        cntA5 += N / sqrtArr5[i];
        cntA5 -= (M / sqrtArr5[i] + L / sqrtArr5[i]);
    }
    printf("%d\n", cntA5);

    return 0;
}