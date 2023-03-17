package semaphore.ordering;

//입출금을 교대로 실행하도록 Ordering 하기
import synchronization_problem.criticalsectionproblem.BankAccount;

import java.util.concurrent.Semaphore;

public class BankAccountProblem2 implements BankAccount {
    int balance;
    Semaphore sem, semDeposit, semWithdraw;

    BankAccountProblem2(){
        sem = new Semaphore(1);
        semDeposit = new Semaphore(0);
        semWithdraw = new Semaphore(0);

    }
    @Override
    public void deposit(int amount){
        try{
            sem.acquire();
            int temp = balance + amount;
            System.out.println(" + ");
            balance = temp;
            sem.release();
            semWithdraw.release();
            semDeposit.acquire();
        } catch (InterruptedException e) { }
    }
    @Override
    public void withdraw(int amount){
        try{
            semWithdraw.acquire();
            sem.acquire();
        } catch (InterruptedException e) { }

        int temp = balance - amount;
        System.out.println(" - ");
        balance = temp;
        sem.release();
        semDeposit.release();
    }
    @Override
    public int getBalance(){
        return balance;
    }
}
