package criticalsectionproblem;

import java.io.IOException;

public class BankAccountProblem {
    public static void main(String[] args) throws InterruptedException, IOException {
        BankAccount bankAccount = new BankAccount();
        Parent parent = new Parent(bankAccount);
        Child child = new Child(bankAccount);

        parent.start();
        child.start();
        parent.join();
        child.join();

        System.out.println("balance = "+ bankAccount.getBalance());
    }
}
//계좌
class BankAccount{
    int balance;
    void deposit(int amount){
        balance = balance + amount;
    }
    void withdraw(int amount){
        balance = balance - amount;
    }
    int getBalance(){
        return balance;
    }
}

//입금 프로세스
class Parent extends Thread{
    BankAccount b;

    Parent(BankAccount b){
        this.b = b;
    }

    public void run(){
        for(int i=0 ; i<100 ; i++){
            b.deposit(1000);
        }
    }
}
//출금 프로세스
class Child extends Thread{
    BankAccount b;

    Child(BankAccount b){
        this.b = b;
    }

    public void run(){
        for(int i=0 ; i<100 ; i++){
            b.withdraw(1000);
        }
    }
}

