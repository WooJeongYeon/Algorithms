// djb2 - Hash Index 구하는 방식
// 문제점 1 - main에서 Node 생성해도 지역변수로 생성되서 하나를 계속 사용하네ㅠㅠ
/* 문제점 2 - 계속 이상한 값 나와서 보니까 put 함수 내에서 Node 생성 시 stack 메모리에 임시적으로 생겼다가 함수 종료 시에 객체가 스택에서 제거되면서 
이를 참조하던 포인터가 문제 생긴거 -> 잘못된 메모리 접근을 하게 되버린ㅠㅠ(댕글링 포인터...? 이건 근데 heap에서 이미 해제된 데이터 참조 시 쓰는거같은데) 
해결 -> (1) 동적 할당하거나 / (2) 전역으로 생성
*/
// 문제점 3 - c++은 char 배열 마지막에 \0 들어가므로 max size보다 +1 로 선언해야 함
// 이진법으로 풀 수 있대
// 비슷하게 푼 풀이 참고 - https://www.acmicpc.net/source/52465570
// 굳이 포인터로 만들 필요 없었던듯... 참조값 넘겨줘도 됬을텐데
#include <stdio.h>
#include <string.h>
#define SIZE 10000000
#define LEN 21

struct Node {
    char key[LEN];
    char val[LEN];
    Node* next = NULL;
};

int N, M;
char key[LEN], val[LEN];
Node* hashTable[SIZE] = {NULL};
Node nodeArr[100000];   // 실제 노드들(동적할당 안하려고)

unsigned long djb2(const char* str) {
    unsigned long hash = 5381; // 소수가 좋음
    int c;
    while(c = *str++) {
        hash = (((hash << 5) + hash) + c) % SIZE;
    }
    return hash;
}

void put(Node* node) {
    int idx = djb2(node->key);

    if(hashTable[idx] == NULL) {
        hashTable[idx] = node;
    } else {
        Node* now = hashTable[idx];
        hashTable[idx] = node;
        node->next = now;
    }
}

char* find(char* key) {
    int idx = djb2(key);
    Node* now = hashTable[idx];
    while(now != NULL) {
        if(strcmp(now->key, key) == 0) {
            return now->val;
        }
        now = now->next;
    }
}

int main() {
    scanf("%d %d\n", &N, &M);
    for(int i = 0 ; i < N ; i++) {
        scanf("%s %s", key, val);
        // Node node; // Node* node = new Node() -> 동적 할당
        // printf("%d", &node); // 계속 같은 값 나옴..ㅜㅜ
        strcpy(nodeArr[i].key, key);
        strcpy(nodeArr[i].val, val);
        put(&nodeArr[i]);
    }
    for(int i = 0 ; i < M ; i++) {
        scanf("%s", key);
        printf("%s\n", find(key));
    }

    return 0;
}