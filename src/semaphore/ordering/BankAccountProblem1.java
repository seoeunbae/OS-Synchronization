package semaphore.ordering;

import java.util.concurrent.Semaphore;
//입금-> 출금 순으로 실행되도록 Ordering 하기
public class BankAccountProblem1 {
    class BankAccount{
        int balance;

        Semaphore sem, semOrder;

        BankAccount() {
            sem = new Semaphore(1);
            semOrder = new Semaphore(0); //Ordering을 위한 세마포

        }
        //입금 프로세스
        void deposit(int amount){
            try{
                sem.acquire();
            } catch (InterruptedException e) { }
            int temp = balance + amount;
            System.out.println(" + ");
            balance = temp;

            sem.release();
            semOrder.release(); // block된 출금 프로세스가 있다면 깨워준다.
        }

        void withdraw(int amount){
            try{
                semOrder.acquire(); // 출금을 먼저하려고 하면 block한다.
                sem.acquire();
            } catch (InterruptedException e){
                int temp = balance - amount;
                System.out.println(" - ");
                balance = temp;
                sem.release();
            }
        }

        int getBalance() {
            return balance;
        }
    }
}
