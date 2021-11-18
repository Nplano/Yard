


In this readme I will provide evidence that my yard implementation
satisfies mutual exclusion as well as is starvation free with a
fair scheduler. I will also discuss issues that may occur with an
adversarial scheduler.



my test-

mytest is a loop that each thread enters and it loops numRepeats times.
on each iteraation a random number either a 0 or a 1 is generated. And if
it is 0  enterAlicePet is called 3 times then an asset statement that
makes sure bobPets==0. It then calls leaveAlicePet 3 times. If the
random number is 1  enterBobPet is called 3 times then an asset statement that
makes sure alicepets==0. It then calls leaveBobPet 3 times. So basically
each thread has a 50 50 chance of trying to add 3 alice pets then
remove them or add 3 bobPets and remove them. And if any of the assert statements
fail we know that mutual exclusion fails. And if it does not finish
we know it is not starvation free. Mine both finishes and has no errors.


mutual Exclusion-

I know that my yard protocol satisfies mutual exclusion for the follow reason.
Lets assume that it does not satisfy mutual exclusion that would
mean that the number of bobPets>0 and the number of alicepets>0. Since
before each function enterAlicePet, enterBobPet, leaveAlicePet , leaveBobPet
the same lock is locked then unlocked at the end for all, thus only one thread
may enter a function at a time. Okay so now if enterBobPet or
enterAlicePet is called a lock is locked and then each function passes over
a while loop that basically says if the other owner has more
than 0 pets in the yard we must wait for a condition that tells us to
wake up. That condition is signaled when leaveAlicePet and leaveBobPet
are called. These functions get the lock, then decrement the number of
their pets and then checks if there are 0 of their own pets. If there
is 0 these functions will signal all the waiting pets of the other owner
basically saying that there are no of my pets left you guys can come in
if you can get the lock after I unlock it. So now that those waiting
wake up they try to get the lock if they get the lock and
there is still 0 of the other pets based on while( bobPets>0) for example
alice in this case will let her pet in. SO for the number of bobPets>0 and
the number of alicepets>0 there must be a mistake as they can only increment
when the other number is 0.


starvation free-

This is starvation free aka any pet that wants to enter will enter
if other pets are acting accordingly. So if one pet wants to enter
it will either get in or it will have to wait on the condition that
the other owner signals that their are no more of their pets. We must
guarantee that the owner that is waiting will go next, and not
that the owner that just left with the last pet will go again. This could
result in the owner waiting to starve and never let its pets in. So we
take turns. When an owner signals to the other owner that they
are leaving with their last pet they look to see if the owner i s
waiting. If the other owner is waiting a flag is set such that
the new owner will go. If the other owner is not waiting
the yard is free for both owners to enter the yard. This results
in all threads being able to enter regardless of how many threads there
are. Using a turn based system if another is waiting this will result
in a starvation free protocol. This assumes that the scheduler is fair


adversarial scheduler-

With an adversarial scheduler this protocol is not starvation free.
If a thread calls enterAlicePet but before it calls  leaveAlicePet it
is descheduled and never scheduled again. Now the number of alicepets
will never reach 0. Thus all the threads that have called enterBobPet
will starve as they are waiting for a signal that will only be called
when the number of alices pets are 0. Thus the threads that have
called enterBobPet will never finish and starve.
