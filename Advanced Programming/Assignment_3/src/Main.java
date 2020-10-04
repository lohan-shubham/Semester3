import java.util.*;

abstract class Player implements Comparator<Player> {
    private final int playerID;  //Player no
    private boolean status;  // to check whether a player is alive or not
    private float HP;   // Health Points of Player

    public Player(int playerID, boolean status, int HP) {
        this.playerID = playerID;
        this.status = status;
        this.HP = HP;
    }


    public int getPlayerID() {
        return playerID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getHP() {
        return HP;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

}

class Commoner extends Player {

    public Commoner(int playerID, boolean status, int HP) {
        super(playerID, status, HP);
    }


    @Override
    public int compare(Player o1, Player o2) {
        return Float.compare(o1.getHP(), o2.getHP());
    }
}

class Mafia extends Player {

    public Mafia(int playerID, boolean status, int HP) {
        super(playerID, status, HP);
    }

    @Override
    public int compare(Player o1, Player o2) {
        return Float.compare(o1.getHP(), o2.getHP());
    }
}

class Detective extends Player {

    public Detective(int playerID, boolean status, int HP) {
        super(playerID, status, HP);
    }

    @Override
    public int compare(Player o1, Player o2) {
        return Float.compare(o1.getHP(), o2.getHP());
    }
}

class Healer extends Player {

    public Healer(int playerID, boolean status, int HP) {
        super(playerID, status, HP);
    }

    @Override
    public int compare(Player o1, Player o2) {
        return Float.compare(o1.getHP(), o2.getHP());
    }
}

public class Main {
    static HashMap<Integer, Commoner> commonerHashMap = new HashMap<>();
    static HashMap<Integer, Mafia> mafiaHashMap = new HashMap<>();
    static HashMap<Integer, Detective> detectiveHashMap = new HashMap<>();
    static HashMap<Integer, Healer> healerHashMap = new HashMap<>();
    static Player[] TotalPlayers;
    static int total_players;
    static int totalAlivePlayers;
    static Player user;
    static Random random = new Random();
    static int mafiaAlive;
    static int detectiveAlve;
    static int healerAlive;
    static int commonerAlive;

    public static int noofMafias() {
        mafiaAlive = Math.floorDiv(total_players, 5);
        return mafiaAlive;
    }

    public static int noofDetectives() {
        detectiveAlve = Math.floorDiv(total_players, 5);
        return detectiveAlve;
    }

    public static int noofHealers() {
        healerAlive = Math.max(1, Math.floorDiv(total_players, 10));
        return healerAlive;
    }

    public static int noofCommoners() {
        return total_players - (noofDetectives() + noofHealers() + noofMafias());
    }

    public static void makeRole() {
        //now im making first n//5 mafias then n//5 detectives then n//10 healers and remaining commoners
        for (int i = 0; i < total_players; i++) {
            if (i < noofMafias()) {
                TotalPlayers[i] = new Mafia(i + 1, true, 2500);
            } else if (i < noofMafias() + noofDetectives()) {
                TotalPlayers[i] = new Detective(i + 1, true, 800);
            } else if (i < noofMafias() + noofDetectives() + noofHealers()) {
                TotalPlayers[i] = new Healer(i + 1, true, 800);
            } else {
                TotalPlayers[i] = new Commoner(i + 1, true, 1000);
            }
        }
        addDetailstoMap();
    }

    private static void addDetailstoMap() {
        for (int i = 0; i < total_players; i++) {
            if (TotalPlayers[i].getClass() == Mafia.class) {
                Mafia temp = (Mafia) TotalPlayers[i];
                mafiaHashMap.put(temp.getPlayerID(), temp);
            }
            if (TotalPlayers[i].getClass() == Detective.class) {
                Detective temp = (Detective) TotalPlayers[i];
                detectiveHashMap.put(temp.getPlayerID(), temp);
            }
            if (TotalPlayers[i].getClass() == Healer.class) {
                Healer temp = (Healer) TotalPlayers[i];
                healerHashMap.put(temp.getPlayerID(), temp);
            }
            if (TotalPlayers[i].getClass() == Commoner.class) {
                Commoner temp = (Commoner) TotalPlayers[i];
                commonerHashMap.put(temp.getPlayerID(), temp);
            }
        }
    }

    public static void printDetails() {
        for (Mafia mafia : mafiaHashMap.values()) {
            System.out.println(mafia.getPlayerID() + "\t" + mafia.getHP() + "\t" + mafia.getClass());
        }
        for (Detective detective : detectiveHashMap.values()) {
            System.out.println(detective.getPlayerID() + "\t" + detective.getHP() + "\t" + detective.getClass());
        }
        for (Healer healer : healerHashMap.values()) {
            System.out.println(healer.getPlayerID() + "\t" + healer.getHP() + "\t" + healer.getClass());
        }
        for (Commoner commoner : commonerHashMap.values()) {
            System.out.println(commoner.getPlayerID() + "\t" + commoner.getHP() + "\t" + commoner.getClass());
        }
    }

    private static void createUser(int roleChosen) {
        boolean flag_rolechosen = false;
        if (roleChosen == 1) {
            for (Mafia mafia : mafiaHashMap.values()) {
                if (!flag_rolechosen) {
                    flag_rolechosen = true;
                    user = mafia;
                    System.out.println("You are Player" + user.getPlayerID());
                    System.out.print("You are a Mafia.Other mafias are: ");
                    continue;
                }
                System.out.print("[Player" + mafia.getPlayerID() + "], ");
            }
        }
        if (roleChosen == 2) {
            for (Detective detective : detectiveHashMap.values()) {
                if (!flag_rolechosen) {
                    flag_rolechosen = true;
                    user = detective;
                    System.out.println("You are Player" + user.getPlayerID());
                    System.out.print("You are a Detective.Other detectives are: ");
                    continue;
                }
                System.out.print("[Player" + detective.getPlayerID() + "], ");
            }
        }
        if (roleChosen == 3) {
            for (Healer healer : healerHashMap.values()) {
                if (!flag_rolechosen) {
                    flag_rolechosen = true;
                    user = healer;
                    System.out.println("You are Player" + user.getPlayerID());
                    System.out.print("You are a healer.Other healer are: ");
                    continue;
                }
                System.out.print("[Player" + healer.getPlayerID() + "], ");
            }
        }
        if (roleChosen == 4) {
            for (Commoner commoner : commonerHashMap.values()) {
                if (!flag_rolechosen) {
                    flag_rolechosen = true;
                    user = commoner;
                    System.out.println("You are Player" + user.getPlayerID());
                    System.out.print("You are a Commoner.Other commoners are: ");
                    continue;
                }
                System.out.print("[Player" + commoner.getPlayerID() + "], ");
            }
        }

        System.out.println();
    }

    private static boolean GameOver() {
        int mafiaAlive = 0;
        int otherAlive = 0;
        for (Mafia mafia : mafiaHashMap.values()) {
            if (mafia.isStatus())
                mafiaAlive++;
        }
        for (Detective detective : detectiveHashMap.values()) {
            if (detective.isStatus())
                otherAlive++;
        }
        for (Healer healer : healerHashMap.values()) {
            if (healer.isStatus())
                otherAlive++;
        }
        for (Commoner commoner : commonerHashMap.values()) {
            if (commoner.isStatus())
                otherAlive++;
        }
        return otherAlive == mafiaAlive || mafiaAlive == 0;

    }

    private static void playerRemaining() {
        System.out.print(totalAlivePlayers + " players are remaining: ");
        for (Player player : TotalPlayers) {
            if (player.isStatus()) {
                System.out.print("Player" + player.getPlayerID() + ", ");
            }
        }
        System.out.println("are alive.");
    }

    private static int chooseTarget() {

        int target = random.nextInt(total_players);
        while (!TotalPlayers[target].isStatus()) {
            target = random.nextInt(total_players);
        }
        System.out.println("Mafias have chosen target");
//        killTarget(target);  //implementation left
        return target + 1;
    }

    private static void killTarget(int target) {
        //this function still lack in algorithm
        float targetHP = TotalPlayers[target].getHP();
        float damageCost = targetHP / mafiaAlive;
        float carry = 0f;
        boolean flag = true;
        for (Mafia mafia : mafiaHashMap.values()) {
            if (mafia.isStatus()) {
                if (mafia.getHP() >= damageCost) {
                    float newHP = mafia.getHP() - damageCost;
                    mafiaHashMap.get(mafia.getPlayerID()).setHP(newHP);
                } else {
                    carry += damageCost - mafia.getHP();
                    mafiaHashMap.get(mafia.getPlayerID()).setHP(0);
                }
            }
        }
        while (flag || carry != 0) {
            flag = false;

            for (Mafia mafia : mafiaHashMap.values()) {
                if (mafia.isStatus()) {
                    if (mafia.getHP() >= damageCost) {
                        float newHP = mafia.getHP() - damageCost;
                        mafiaHashMap.get(mafia.getPlayerID()).setHP(newHP);
                    } else {
                        carry += damageCost - mafia.getHP();
                        mafiaHashMap.get(mafia.getPlayerID()).setHP(0);
                    }
                }
            }
        }
    }

    private static int healSomeone() {

        //now using a random player to heal
        //later use minheap
        int healRandom = random.nextInt(total_players);
        while (!TotalPlayers[healRandom].isStatus()) {
            healRandom = random.nextInt(total_players);
        }
        return healRandom + 1;
    }

    private static int votingResult(int vote) {
        //assume collective voting
        int votingResult = random.nextInt(total_players);
        while (!TotalPlayers[votingResult].isStatus()) {
            votingResult = random.nextInt(total_players);
        }
        return votingResult + 1;
    }

    public static void declareWinner() {
        int mafiaAlive = 0;
        for (Mafia mafia : mafiaHashMap.values()) {
            if (mafia.isStatus()) {
                mafiaAlive++;
            }
        }
        if (totalAlivePlayers - mafiaAlive == mafiaAlive) {
            System.out.println("Mafia wins");
            return;
        }
        if (mafiaAlive == 0) {
            System.out.println("Mafia loose");
            return;
        }

    }

    private static void DeadorAlive(int target, int healedPlayer) {
        Scanner input = new Scanner(System.in);
        if (target != healedPlayer) {
            System.out.println("Player" + target + " is died");
            System.out.print("Select a person to vote out: ");
            int vote = input.nextInt();
            int votingResult = votingResult(vote);
            System.out.println("\nPlayer" + votingResult + " has been voted out.");
            Kill(votingResult); //
            TotalPlayers[votingResult - 1].setHP(TotalPlayers[votingResult - 1].getHP() + 500);//healed the player chosen by healers
        } else {
            System.out.println("No one is died");
            TotalPlayers[target - 1].setHP(500);
        }
    }

    private static void Kill(int votingResult) {
        TotalPlayers[votingResult - 1].setStatus(false);
        totalAlivePlayers--;
        if (TotalPlayers[votingResult - 1].getClass() == Mafia.class) {
            mafiaAlive--;
        }
        if (TotalPlayers[votingResult - 1].getClass() == Detective.class) {
            detectiveAlve--;
        }
        if (TotalPlayers[votingResult - 1].getClass() == Healer.class) {
            healerAlive--;
        }
        if (TotalPlayers[votingResult - 1].getClass() == Commoner.class) {
            mafiaAlive--;
        }
    }

    public static void main(String[] args) {
//        TotalPlayers[0] = new Commoner(3,true,1000);
//        System.out.println(TotalPlayers[0].getClass());
//        Commoner temp= (Commoner)TotalPlayers[0];
//        CommonerHashMap.put(temp.getPlayerID(),temp);
//        CommonerHashMap.get(3).setHP(1500);
//        CommonerHashMap.get(3).setPlayerID(10);
//        Commoner temp2= (Commoner) TotalPlayers[0];
//        System.out.println(temp2.getPlayerID()+" "+ temp2.getHP());

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Mafia");
        System.out.print("Enter Numbers of Players: ");
        total_players = input.nextInt();
        totalAlivePlayers = total_players;
        TotalPlayers = new Player[total_players];
        makeRole();
//        printDetails(); // need to be changed
        System.out.println("Choose a Character\n" +
                "1) Mafia\n" +
                "2) Detective\n" +
                "3) Healer\n" +
                "4) Commoner\n" +
                "5) Assign Randomly");
        int roleChosen = input.nextInt();
        createUser(roleChosen);
//        System.out.println(user.getHP());
//        user.setHP(0);
//        System.out.println(user.getHP() + "\t" + mafiaHashMap.get(user.getPlayerID()).getHP() + "\t" + TotalPlayers[user.getPlayerID() - 1].getHP());

//        for(int i=0;i<total_players;i++){
//            System.out.println((i+1)+"\t"+TotalPlayers[i].getPlayerID());
//        }
        int round = 1;
        while (!GameOver()) {
            System.out.println("Round " + round + ":");
            playerRemaining();
            if (user.getClass() == Detective.class) {  //if user is detective
                int target = chooseTarget();
                System.out.print("Choose a player to test: ");
                int testplayer = 0;
                try {
                    testplayer = input.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("Please enter a integer");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please choose a correct player");
                }
                if (TotalPlayers[testplayer - 1].getClass() != Mafia.class) {
                    System.out.println("Player" + testplayer + " is not a Mafia");
                    int healedPlayer = healSomeone();
                    System.out.println("player chose to heal: " + healedPlayer);
                    System.out.println("-------------End of Action---------------------");
                    DeadorAlive(target, healedPlayer);  //check whether a target person is dead or alive
                } else { //if test person is mafia
                    System.out.println("Player" + testplayer + " is Mafia");
                    int healedPlayer = healSomeone();
                    TotalPlayers[testplayer - 1].setStatus(false); //kill mafia
                    TotalPlayers[healedPlayer - 1].setHP(TotalPlayers[healedPlayer - 1].getHP() + 500);//healed the player choosen by healers
                }
            }
            round++;
        }
        declareWinner();

    }


}
