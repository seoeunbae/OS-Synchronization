package synchronization_problem.processes;

import synchronization_problem.criticalsectionproblem.BankAccount;
//출금 프로세스
public class Child extends Thread{
    BankAccount b;

    public Child(BankAccount b){
        this.b = b;
    }

    public void run(){
        for(int i=0 ; i<100 ; i++){
            b.withdraw(1000);
        }
    }
}
