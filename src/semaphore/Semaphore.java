package semaphore;

import java.util.LinkedList;
import java.util.Queue;

public class Semaphore {
    static Queue<Thread> queue = new LinkedList<>();

    int value;
    Semaphore(int value){

    }

    void acquire(Thread T) throws InterruptedException {
        value--;
        if(value < 0){
            //add this proceess/thread to list
            queue.add(T);
            //block
            T.wait();

        }
    }

    void release(Thread T){
        value++;
        if(value <= 0){
            //remove a process P from list
            queue.remove(T);
            //wakeup P
            T.start(); //=> 새로 호출스택을 생성한 담애 run 실행됨
                      //cf) T.run() => 기존의 공간에서 메소드만 실행됨
        }
    }

}
