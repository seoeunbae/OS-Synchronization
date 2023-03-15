package semaphore;

import java.util.concurrent.Semaphore;

public class BankAccountProblem {
    class BankAccount{
        int balance;

        Semaphore sem;
        BankAccount(){
            sem = new Semaphore(1);
            //value값을 1로 초기화
        }

        void deposit(int amount){
            try{
                sem.acquire(); //임계구역에 들어가기를 요창한다.
            } catch (InterruptedException e) { }
            /* 임계 구역 */
            int temp = balance + amount;
            System.out.println(" + ");
            balance = temp;

            sem.release(); //임계구역에서 나간다.
        }

        void withdraw(int amount){
            try{
                sem.acquire();
            } catch (InterruptedException e) { }
            /* 임계 구역 */
            int temp = balance - amount;
            System.out.println(" - ");
            balance = temp;

            sem.release();
        }

        int getBalance(){
            return balance;
        }
    }
}
