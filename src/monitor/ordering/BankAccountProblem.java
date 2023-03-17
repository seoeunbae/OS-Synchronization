package monitor.ordering;

import synchronization_problem.criticalsectionproblem.BankAccount;

class BankAccountDepositFirst implements BankAccount {
    int balance;

    @Override
    public synchronized void deposit(int amt) {
        int temp = balance + amt;
        System.out.print("+");
        balance = temp;
        notify();
    }
    @Override
    public synchronized void withdraw(int amt) {
        while (balance <= 0)
            try {
                wait();
            } catch (InterruptedException e) {}
        int temp = balance - amt;
        System.out.print("-");
        balance = temp;
    }
    @Override
    public int getBalance() {
        return balance;
    }
}

class BankAccountWithDrawFirst implements BankAccount {
    int balance;
    @Override
    public synchronized void deposit(int amt) {
        while (balance == 0)
            try {
                wait();
            } catch (InterruptedException e) {}
        int temp = balance + amt;
        System.out.print("+");
        balance = temp;
    }
    @Override
    public synchronized void withdraw(int amt) {
        int temp = balance - amt;
        System.out.print("-");
        balance = temp;
        notify();
    }
    @Override
    public int getBalance() {
        return balance;
    }
}

class BankAccountRotation implements BankAccount {
    int balance;
    boolean p_turn = true;

    @Override
    public synchronized void deposit(int amt) {
        int temp = balance + amt;
        System.out.print("+");
        balance = temp;
        notify();
        p_turn = false;
        try {
            wait();
        } catch (InterruptedException e) {}
    }

    @Override
    public synchronized void withdraw(int amt) {
        while (p_turn)
            try {
                wait();
            } catch (InterruptedException e) {}
        int temp = balance - amt;
        System.out.print("-");
        balance = temp;
        notify();
        p_turn = true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}