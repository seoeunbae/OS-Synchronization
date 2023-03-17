package synchronization_problem.criticalsectionproblem;

import synchronization_problem.processes.Child;
import synchronization_problem.processes.Parent;

import java.io.IOException;

public class BankAccountProblem {
    public static void main(String[] args) throws InterruptedException, IOException {
        BankAccount bankAccount = new BankAccountImpl();
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
class BankAccountImpl implements BankAccount {
    int balance;
    @Override
    public void deposit(int amount){
        int temp = balance + amount;
        System.out.print("+");
        balance = temp;
    }
    @Override
    public void withdraw(int amount){
        int temp = balance - amount;
        System.out.print("-");
        balance = temp;
    }
    @Override
    public int getBalance(){
        return balance;
    }
}

