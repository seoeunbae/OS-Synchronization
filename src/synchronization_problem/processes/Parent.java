package synchronization_problem.processes;

import synchronization_problem.criticalsectionproblem.BankAccount;
//입급 프로세스
public class Parent extends Thread{
    BankAccount b;

    public Parent(BankAccount b){
        this.b = b;
    }

    public void run(){
        for(int i=0 ; i<100 ; i++){
            b.deposit(1000);
        }
    }
}
