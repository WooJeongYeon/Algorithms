# 해싱(Hashing)

Hash Function을 사용해 고정된 크기의 값으로 변환하는 작업이다. Hash Function을 사용해 특정 해시값을 알아내고 그 값을 인덱스로 변환해 key와 value값을 bucket에 저장한다.

같은 해시값을 가지게 되는 경우, **충돌(Collusion)**이 발생할 수 있다.

Java에서 HashTable과 HashMap은 같은 역할을 가지며, HashTable은 Multi-Thread 환경에서 사용될 수 있다.

### HashTable의 시간복잡도

탐색/삽입/삭제 - O(1)

충돌이 발생할 경우, 탐색/삽입에 대해 O(K)의 시간 소요

### 충돌 해결

1. Chaining : 충돌 발생 시, LinkedList로 연결해서 데이터를 저장
   
   ![hash4](https://github.com/WooJeongYeon/Algorithms/assets/92253127/4c9d0546-63a9-4bbc-99c3-2760fb25bdce)

2. Open Address
   
   - 선형탐사(Linear Probing) : 순차적으로 다음 빈 공간에 데이터를 저장
   
   - 제곱탐사(Quadratic Probing)
   
   - 이중해싱(Double Hashing)

### 예시 코드(C++)

```c++
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
```

### 기타

Hash Index 구하는 방식 - **djb2**

```c++
unsigned long djb2(const char* str) {
    unsigned long hash = 5381; // 소수가 좋음
    int c;
    while(c = *str++) {
        hash = (((hash << 5) + hash) + c) % SIZE;
    }
    return hash;
}
```



### 참고

[이론1](https://baeharam.netlify.app/posts/data%20structure/hash-table)

[이론2]([[Lecture 4] Hash Function / 해시 함수](https://devjourney7.tistory.com/118))

[djb2 함수](https://zoosso.tistory.com/948)

[c++ 구현](https://twpower.github.io/139-hash-table-implementation-in-cpp)


