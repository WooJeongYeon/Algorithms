#include <stdio.h>
#include <math.h>
#define SIZE 1000001

bool compArr[SIZE] = {false};
int T, N;
int max = sqrt(SIZE);

int main() {
    scanf("%d", &T);
    compArr[1] = compArr[0] = true;

    for(int i = 2 ; i <= max ; i++) {
        if(compArr[i])
            continue;
        for(int j = i * i ; j < SIZE ; j += i) {
            compArr[j] = true;
        }
    }

    for(int tc = 0 ; tc < T ; tc++) {
        scanf("%d", &N);
        int i = 3;
        int cnt = !compArr[N - 2] ? 1 : 0;
        while(i <= (N - i)) {
            if(!compArr[i] && !compArr[N - i])
                cnt++;
            i += 2;
        }
        printf("%d\n", cnt);
    }
}