# Process/Thread Synchronization
- 프로세스/쓰레드 동기화 도구인 Semaphore, Monitors, Misc를 JAVA로 실습합니다.

<br>

# Introduction

os의 기능 중 하나인 프로세스 관리 부서에는
- CPU서비스를 받을 프로세스를 스케쥴링 하는 CPU스케줄링 기능
- 프로세스와 쓰레드간의 일관성을 지켜주는 **프로세스 동기화** 기능

이 있다.

<br>


> ##### [프로세스(쓰레드) 동기화 목적]
>
> 1. 원하는 결과값을 도출하도록 임계구역 문제를 해결한다.
> 2. 프로세스의 실행 순서를 원하는대로 제어한다.
> 3. Busy wait 등과 같은 비효율성을 제거한다.



<br>

# Problem

<br>
   
멀티 프로세스/쓰레드 프로그램의 동기화 문제에는 **임계구역 문제**가 있다.

   <br>

## 임계구역 (Critical Section)

-> 다중 쓰레드로 구성된 시스템에서... 쓰레드가 **공통 변수**를 변화시키는 구역

<br>

## 임계 구역 문제

공통변수를 업데이트할때, **시간지연**을 준 경우,
동시업데이트가 발생하면서 예상치 못한 변수값이 된다.

한줄로 구현된 간단한 자바코드도 실제로 로우 레벨로 갈수록 여러 줄로 구현되고,
실제 상황 처럼 시간지연이 있는 경우,
여러 쓰레드가 하나의 공유자원을 사용하는 프로그램은 망가지게 된다.

<br>

## 임계 구역 해결 조건

- **Mutual exclusion(상호배타)**: 오직 한 쓰레드만이 진입 가능하다. 한 쓰레드가 임계구역에서 수행 중인 상태에서는 다른 쓰레드는 절대 이 구역에 접근할 수 없다.
- **Progress(진행)**: 한 임계구역에 접근하는 쓰레드를 결정하는 것은 유한 시간 이내에 이루어져야한다.
- **Bounded waiting(유한대기)**: 임계구역으로 진입하기 위해 대기하는 모든 쓰레드는 유한 시간 이내에 해당 임계구역으로 진입할 수 있어야 한다.

<br>

* * *

# Overview

<br>

##### [동기화 문제 해결을 위한 방법 3가지]

 - Semaphore
 - Monitor
 - Misc

<br>

* * *

# Main Subject

## 1. Semaphore 
- 대표적인 동기화 도구
- 한국어뜻 : 수신호

 <br>

> ##### [세마포어의 두가지 동작]
> P (test) : acquire()
>
> - P 동작 전개
> 1. 세마포어의 Integer 변수의 값 -1 ;
> 2. Integer 변수의 값이 음수 이면 -> 대기큐에 **현재** 프로세스를 넣고 **블락**한다.
> 
> V (increment) : release()
>
> - V 동작 전개
> 1. 세마포어의 Integer 변수의 값 +1 ;
> 2. Integer 변수의 값 0이거나 음수인 경우  ->  대기큐에서 **하나의 P 프로세스**를 커내서 **릴리즈**한다.

<br>

- ##### 세마포어 스트럭쳐

```java
class Semaphore {
  int value;      // number of permits
  Semaphore(int value) {
    // ...
  }
  void acquire() {
    value--;
    if (value < 0) {
      // add this process/thread to list
      // block
    }
  }
  void release() {
    value++;
    if (value <= 0) {
      // remove a process P from list
      // wakeup P
    }
  }
}
```

- 세마포어의 예저 코드는 Semaphore패키지에서 확인할 수 있습니다.


### 세마포어의 사용목적

1. Mutual Exclusion (임계구역 문제 해결)

    <br>

    Integer value 를 공유하면서 해당 변수값의 변동을 이용해서 block / release 를 함으로써 임계구역문제를 해결한다.

    <br>

   <img width="1072" alt="image" src="https://user-images.githubusercontent.com/71380240/216970243-01474be8-5604-49bb-b4dd-4a1cd834af9b.png">

<br>

2. Ordering (프로세스의 실행순서를 제어)
    
    <br>

    아래는 cpu 스케쥴링 알고리즘과 관계없이 무조건 s1 이 먼저 실행되도록 할 수 있다.

    <br>    
   
    <img width="1044" alt="image" src="https://user-images.githubusercontent.com/71380240/216970474-910dbae2-e2e2-489b-8183-797b64656476.png">

<br>

## 2. Monitor


## 3. Misc


