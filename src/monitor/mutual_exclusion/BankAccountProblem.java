package monitor.mutual_exclusion;

import synchronization_problem.criticalsectionproblem.BankAccount;
import synchronization_problem.processes.Child;
import synchronization_problem.processes.Parent;

public class BankAccountProblem {
    public static void main(String[] args) throws InterruptedException {
        BankAccountImpl b = new BankAccountImpl();
        Parent p = new Parent(b);
        Child c = new Child(b);
        p.start();
        c.start();
        p.join();
        c.join();
        System.out.println("\n balance = " + b.getBalance());
    }
}

     class BankAccountImpl implements BankAccount {
        int balance;

        @Override
        public synchronized void deposit(int amount) {
            int temp = balance + amount;
            System.out.print("+");
            balance = temp;
        }

        @Override
        public synchronized void withdraw(int amount) {
            int temp = balance + amount;
            System.out.print("+");
            balance = temp;
        }

        @Override
        public int getBalance() {
            return balance;
        }
    }

