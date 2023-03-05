/*
 * Study of the Monty Hall game
 * Rules: Three door selection moves to find one door with a prize
 * 1). Contestant selects one of three doors
 * 2). Host reveals one non-prize door from the remaining two doors
 * 3). Contestant Stays with their initial selection, or Switches to the remaining door
 * Contestant's final door selection is then revealed.
 */
import java.util.*;

public class MontyHall {
    public static void main(String[] args) throws Exception {
      Random      rng = new Random(); // Random Number Generator
      Integer    runs = 1000;
      Integer[] stats = {0, 0};
      
      for(int game = 0; game < runs; game++ ){
        /* Create set of three doors, one of which has the prize */
        List<Boolean> doors = new ArrayList<>( Arrays.asList(true, false, false) );
        Collections.shuffle(doors);

        /* First move, player selects random door */
        int   random_door = rng.nextInt( doors.size() );
        boolean is_winner = doors.remove( random_door );

        /* Second move, two doors remain; host reveals/removes a false door */
        random_door = rng.nextInt( doors.size() );
        if( doors.get(random_door) ){ // this door has prize
          random_door = (random_door + 1) % 2; // select the other door
        } // else this is a false door
        doors.remove(random_door);

        // Now there's only one door left in doors
        // Player can stick with is_winner or update is_winner to the remaining door
        if(is_winner){
          stats[0]++;
        }
        if(doors.get(0)){
          stats[1]++;
        }
      } // game loop

      // output results
      System.out.println(
        String.format("%s games played\nStay:%s, Switch:%s",
          runs, stats[0], stats[1]
        )
      );

    } // main
} // MontyHall
