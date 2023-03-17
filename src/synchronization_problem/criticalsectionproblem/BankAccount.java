package synchronization_problem.criticalsectionproblem;

public interface BankAccount {
    void deposit(int amount);
    void withdraw(int amount);
    int getBalance();
}
