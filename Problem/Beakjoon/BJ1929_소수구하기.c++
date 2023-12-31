#include <stdio.h>

int N, M;
bool isComp[1000001] = {false};  // composite number - 합성수

int main() {
    for(int i = 2 ; i <= 1000 ; i++) {
        if(isComp[i])
            continue;
        for(int j = i * i; j <= 1000000 ; j += i) {
            isComp[j] = true;
        }
    }
    isComp[1] = true;
    scanf("%d %d", &N, &M);

    for(int i = N ; i <= M ; i++) {
        if(!isComp[i])
            printf("%d\n", i);
    }
}