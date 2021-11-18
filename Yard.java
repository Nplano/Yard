import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Yard {
	// You CANNOT use AtomicIntegers and the like in
	// your solution-- only Locks and Conditions (and, of course,
	// non-concurrent data types) are allowed.
	// Another thing to keep in mind: you are running a program
	// with a finite number of threads. Depending on your strategy,
	// this may be something you need to work around, but we don't
	// suggest you take advantage of it.
	public int alicePets = 0;
	public int bobPets = 0;
    ReentrantLock lock = new ReentrantLock();
    final Condition no_alicePets  =lock.newCondition();
    final Condition no_bobPets =lock.newCondition();


    boolean bobs_up =false;
    boolean alices_up =false;

    boolean bobs_waiting =false;
    boolean alices_waiting =false;




	public void enterAlicePet() throws InterruptedException {
        lock.lock();
        while( bobPets>0 || bobs_up==true){
            alices_waiting=true;
            no_bobPets.await();
        }

        alices_waiting=false;
        alices_up= true;
        alicePets= alicePets+1;
        lock.unlock();
	}

	public void leaveAlicePet() throws InterruptedException {
        lock.lock();
        alicePets= alicePets-1;
        if(alicePets==0 ){
            if(bobs_waiting==true){
                alices_up= false;
                bobs_up= true;
                
            }
            else{

                bobs_up=false;
                bobs_up=false;
                alices_up= false;
            }

            no_alicePets.signalAll();

        }
        lock.unlock();
	}

	public void enterBobPet() throws InterruptedException {
        lock.lock();
        while( alicePets>0 || alices_up==true){
            bobs_waiting=true;
            no_alicePets.await();
        }

        bobs_waiting=false;
        bobs_up= true;
        bobPets= bobPets+1;
        lock.unlock();
	}

	public void leaveBobPet() throws InterruptedException {
        lock.lock();
        bobPets= bobPets-1;
        if(bobPets==0 ){
            if(alices_waiting==true){

                bobs_up=false;
                alices_up= true;
            }
            else{

                bobs_up=false;
                alices_up= false;
            }

            no_bobPets.signalAll();

        }
        lock.unlock();
	}

}
