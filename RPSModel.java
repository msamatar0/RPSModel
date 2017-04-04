import java.util.Scanner;

/**
 *
 * @author msamatar0
 */
public class RPSModel{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Player p1 = new Player(in.next()), p2 = new Player(in.next());
        System.out.println("New roundRobin players: " + p1.getName() + ", " + p2.getName());
        while(in.hasNext()){
            String g1 = in.next(),
                   g2 = in.next();
            if(g1.equals("quit") || g2.equals("quit"))
                break;
            p1.setGuess(g1);
            p2.setGuess(g2);
            int result = p1.play(p2);
            String message = "You chose " + g1 + " " + p2.getName() + " " + g2;
            switch(result){
                case 1:
                    System.out.println(message + " you won");
                    break;
                case 0:
                    System.out.println(message + " you tie");
                    break;
                case -1:
                    System.out.println(message + " you loss");
                    break;
            }
            result *= -1;
            message = "You chose " + g2 + " " + p1.getName() + " " + g1;
            switch(result){
                case 1:
                    System.out.println(message + " you won");
                    break;
                case 0:
                    System.out.println(message + " you tie");
                    break;
                case -1:
                    System.out.println(message + " you loss");
                    break;
            }
        }
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.getResults(p2));
    }
}

class Player{
    private enum choice{ROCK, PAPER, SCISSORS, OTHER};
    private final String name;
    private int wins, losses, ties;
    private choice guess;
    
    public Player(String n){
        name = n;
    }
    
    public void setGuess(String g){
        switch(g.toLowerCase()){
            case "rock":
                guess = choice.ROCK;
                break;
            case "paper":
                guess = choice.PAPER;
                break;
            case "scissors":
                guess = choice.SCISSORS;
                break;
            default:
                guess = choice.OTHER;
        }
    }
    
    public int play(Player p){
        int r = getGuess() - p.getGuess();
        if(guess == choice.OTHER){
            if(p.guess == choice.OTHER)
                r = 0;
            else
                r = -1;
        }
        switch(r){
            case 1:
			case -2:
				r = 1;
                wins++;
                p.losses++;
                break;
            case -1:
            case 2:
                r = -1;
                p.wins++;
                losses++;
                break;
            case 0:
                ties++;
                p.ties++;
                break;
        }
        return r;
    }
    
    public int getGuess(){
        switch(guess){
            case ROCK:
                return 0;
            case PAPER:
                return 1;
            case SCISSORS:
                return 2;
        }
        return -1;
    }

    public String getName(){
        return name;
    }
    
    public String getResults(Player p){
        String first, second, score;
        if(wins > p.wins){
            first = getName();
            second = p.getName();
            score = "" + wins + " to " + losses;
        }
        else if(p.wins > wins){
            first = p.getName();
            second = getName();
            score = "" + p.wins + " to " + p.losses;
        }
        else
            return getName() + " and " + p.getName() + " tied";
        return first + " defeated " + second + " " + score;
    }
    
    @Override
    public String toString(){
        return name + " " + wins + '-' + losses + '-' + ties;
    }
}