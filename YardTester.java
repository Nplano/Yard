import java.util.Random;

public class YardTester implements Runnable {

	private final Yard yard;
	private final Random rand;
	private final int numRepeats;

	YardTester (int repeats) {
		yard = new Yard();
		rand = new Random();
		numRepeats = repeats;
	}

	public void run() {
        try {


            // Repeatedly+randomly act as one of Alice's or Bob's pets
            // Feel free to modify this to whatever will make your tests easier
            for (int i=0; i < numRepeats; i++) {
                int num= Math.abs(rand.nextInt())%2;
                //System.out.println("alice 1");
                if( num==0){
                    //System.out.println("alice 1");

                    yard.enterAlicePet();
                    yard.enterAlicePet();
                    yard.enterAlicePet();
                    //System.out.println("alice " + yard.alicePets +" bob " + yard.bobPets);
                    assert(yard.bobPets == 0);
        // Assert there are no Bob pets around
                    yard.leaveAlicePet();
                    yard.leaveAlicePet();
                    yard.leaveAlicePet();

                    //System.out.println("alice 2");

                }
                else{
                //    System.out.println("bob 1");

                    yard.enterBobPet();
                    yard.enterBobPet();
                    yard.enterBobPet();
                //    System.out.println("alicdfse " + yard.alicePets +" bob " + yard.bobPets);
                    assert(yard.alicePets == 0);
                    //System.out.println("point 4"); // Assert there are no Alice pets around
                    yard.leaveBobPet();
                    yard.leaveBobPet();
                    yard.leaveBobPet();
                    //System.out.println("bob 2");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
}
