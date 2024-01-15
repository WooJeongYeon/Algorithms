#include <stdio.h>
#include <string.h>
#define SIZE 1000000

struct Pocket {
    char* nameP = NULL;
    int idx = 0;
};

int N, M;
char str[21];
Pocket hash[SIZE];
char intToStr[100001][21];

unsigned long djb2(const char* str) {
    unsigned long hash = 5381;
    int c;
    while(c = *str++) {
        hash = (((hash << 5) + hash) + c) % SIZE;
    }

    return hash;
}

void push(char* str, int idx) {
    int inputIdx = djb2(str);
    while(hash[inputIdx].idx > 0) {
        inputIdx = (inputIdx + 1) % SIZE;
    }
    hash[inputIdx].nameP = str;
    hash[inputIdx].idx = idx;
}

int pop(char* str) {
    int getIdx = djb2(str);
    while(strcmp(hash[getIdx].nameP, str) != 0) {
        getIdx = (getIdx + 1) % SIZE;
    }
    return hash[getIdx].idx;
}

int strToInt(char* str) {
    int sum = 0;
    for(int i = 0 ; str[i] != '\0'; i++) {
        sum *= 10;
        sum += str[i] - '0';
    }
    return sum;
}

int main() {
    scanf("%d %d", &N, &M);
    for(int i = 1 ; i <= N ; i++) {
        scanf("%s", str);
        strcpy(intToStr[i], str);
        push(intToStr[i], i);
    }
    for(int i = 0 ; i < M ; i++) {
        scanf("%s", str);
        if(str[0] >= '0' && str[0] <= '9') {
            int now = strToInt(str);
            printf("%s\n", intToStr[now]);
        } else {
            printf("%d\n", pop(str));
        }
    }

    return 0;
}