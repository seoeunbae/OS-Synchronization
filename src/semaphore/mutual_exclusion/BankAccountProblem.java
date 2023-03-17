package semaphore.mutual_exclusion;

import synchronization_problem.criticalsectionproblem.BankAccount;

import java.util.concurrent.Semaphore;

public class BankAccountProblem {
    class BankAccountImpl implements BankAccount {
        int balance;

        Semaphore sem;
        BankAccountImpl(){
            sem = new Semaphore(1);
            //value값을 1로 초기화
        }
        @Override
        public void deposit(int amount){
            try{
                sem.acquire(); //임계구역에 들어가기를 요창한다.
            } catch (InterruptedException e) { }
            /* 임계 구역 */
            int temp = balance + amount;
            System.out.println(" + ");
            balance = temp;

            sem.release(); //임계구역에서 나간다.
        }
        @Override
        public void withdraw(int amount){
            try{
                sem.acquire();
            } catch (InterruptedException e) { }
            /* 임계 구역 */
            int temp = balance - amount;
            System.out.println(" - ");
            balance = temp;

            sem.release();
        }
        @Override
        public int getBalance(){
            return balance;
        }
    }
}
