import java.util.*;

abstract class Player {
    private final int playerID;  //Player no
    private boolean status;  // to check whether a player is alive or not
    private int HP;   // Health Points of Player

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

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}

class Commoner extends Player {

    public Commoner(int playerID, boolean status, int HP) {
        super(playerID, status, HP);
    }

}

class Mafia extends Player implements Cloneable {

    public Mafia(int playerID, boolean status, int HP) {
        super(playerID, status, HP);
    }

    public Mafia clone() {
        try { // deep copy
            Mafia copy = (Mafia) super.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null; // won't ever happen
        }
    }
}

class Detective extends Player {

    public Detective(int playerID, boolean status, int HP) {
        super(playerID, status, HP);
    }

}

class Healer extends Player {

    public Healer(int playerID, boolean status, int HP) {
        super(playerID, status, HP);
    }

}


public class Main {
    static HashMap<Integer, Commoner> commonerHashMap = new HashMap<>();
    static HashMap<Integer, Mafia> mafiaHashMap = new HashMap<>();
    static HashMap<Integer, Detective> detectiveHashMap = new HashMap<>();
    static HashMap<Integer, Healer> healerHashMap = new HashMap<>();
    static Player[] TotalPlayers;
    static int total_players;
    static Player user;
    static Random random = new Random();
    static int mafiaAlive;
    static int detectiveAlive;
    static int healerAlive;
    static int commonerAlive;

    public static int noofMafias() {
        mafiaAlive = Math.floorDiv(total_players, 5);
        return mafiaAlive;
    }

    public static int noofDetectives() {
        detectiveAlive = Math.floorDiv(total_players, 5);
        return detectiveAlive;
    }

    public static int noofHealers() {
        healerAlive = Math.max(1, Math.floorDiv(total_players, 10));
        return healerAlive;
    }

    public static int noofCommoners() {
        commonerAlive = total_players - mafiaAlive - detectiveAlive - healerAlive;
        return commonerAlive;
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
                noofCommoners();
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
        for (Player player : TotalPlayers) {
            System.out.println(player.getPlayerID() + "\t" + player.getHP() + "\t" + player.getClass() + "\t" + player.isStatus());
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
        if (roleChosen == 5) {
            createUser(random.nextInt(4) + 1);
        }

        System.out.println();
    }

    private static boolean GameOver() {

        return (mafiaAlive == (commonerAlive + detectiveAlive + healerAlive) || mafiaAlive == 0);

    }

    public static int totalAlivePlayers() {
        return mafiaAlive + commonerAlive + healerAlive + detectiveAlive;
    }

    private static void playerRemaining() {

        System.out.print(totalAlivePlayers() + " players are remaining: ");
        for (Player player : TotalPlayers) {
            if (player.isStatus()) {
                System.out.print("Player" + player.getPlayerID() + ", ");
            }
        }
        System.out.println("are alive.");
    }

    private static int chooseTarget() {
        int target = random.nextInt(total_players);
        while (TotalPlayers[target].getClass() == Mafia.class) {
            target = random.nextInt(total_players);
        }
//        System.out.println("Mafias have chosen target");
        return target + 1;
    }

    public static void killTarget(int target) {
        int mafiaHP = 0;
        ArrayList<Mafia> aliveMafia = new ArrayList<>();
        for (Mafia mafia : mafiaHashMap.values()) {
            mafiaHP += mafia.getHP();
            if (mafia.getHP() > 0) {
                aliveMafia.add(mafia);
            }
        }
        //if target HP is greater than all mafia
        if (TotalPlayers[target - 1].getHP() > mafiaHP) {
            for (Mafia mafia : mafiaHashMap.values()) {
                mafia.setHP(0);
            }
            TotalPlayers[target - 1].setHP(TotalPlayers[target - 1].getHP() - mafiaHP);
        } else {
            killDirectly(target);
            Collections.sort(aliveMafia, new Comparator<Mafia>() {
                @Override
                public int compare(Mafia o1, Mafia o2) {
                    return Integer.compare(o1.getHP(), o2.getHP());
                }
            });
            System.out.println();
            int carry = 0;
            int size = aliveMafia.size();
            for (int i = 0; i < aliveMafia.size(); i++) {
                if (size == 1) {
                    aliveMafia.get(i).setHP(aliveMafia.get(i).getHP() - TotalPlayers[target - 1].getHP());
                } else if (TotalPlayers[target - 1].getHP() / size > aliveMafia.get(i).getHP() + carry) {
                    aliveMafia.get(i).setHP(0);
                    if (aliveMafia.get(i).getHP() > 0)
                        carry += TotalPlayers[target - 1].getHP() / aliveMafia.get(i).getHP();
                    size--;
                } else {
                    aliveMafia.get(i).setHP(aliveMafia.get(i).getHP() - TotalPlayers[target - 1].getHP() / size);
                }
            }

        }

    }

    private static int healSomeone() {
        if (healerAlive < 1) {
            System.out.println("All healer is died");
            return -1;
        }
        int healRandom = random.nextInt(total_players);
        while (!TotalPlayers[healRandom].isStatus()) {
            healRandom = random.nextInt(total_players);
        }
        return healRandom + 1;
    }

    private static int doVote(int vote) {

        HashMap<Integer, Integer> voteData = new HashMap<>();
        int votingResult = random.nextInt(total_players);
        for (Player player : TotalPlayers) {
            if (player.isStatus() && player.getPlayerID() != user.getPlayerID()) {
                while (!TotalPlayers[votingResult].isStatus() || TotalPlayers[votingResult].getClass() == player.getClass()) {
                    votingResult = random.nextInt(total_players);
                }
                voteData.put(TotalPlayers[votingResult].getPlayerID(), voteData.getOrDefault(TotalPlayers[votingResult].getPlayerID(), 0) + 1);
            }
        }
        voteData.put(TotalPlayers[vote - 1].getPlayerID(), voteData.getOrDefault(TotalPlayers[vote - 1].getPlayerID(), 0) + 1);


        return votingResult(voteData);

    }

    public static int votingResult(HashMap<Integer, Integer> voteData) {
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(voteData.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int counter = 0;
        int voteout_key = -1;
        int voteout_value = -1;
        HashMap<Integer, Integer> temp = new HashMap<>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
            if (counter == 0) {
                voteout_key = aa.getKey();
                voteout_value = aa.getValue();
                counter++;
                continue;
            }
            if (counter > 0 && voteout_value == aa.getValue()) {
                voteout_key = -1;
                voteout_value = -1;
            }
        }
        return voteout_key;
    }

    public static void declareWinner() {
        if (commonerAlive + detectiveAlive + healerAlive == mafiaAlive) {
            System.out.println("Mafia wins");
            return;
        }
        if (mafiaAlive == 0) {
            System.out.println("Mafia loose");
            return;
        }

    }

    private static void DeadorAlive(int targetPlayer, int healedPlayer) {
        Scanner input = new Scanner(System.in);
        if (targetPlayer == healedPlayer && TotalPlayers[targetPlayer - 1].getClass() != Mafia.class) {
            System.out.println("no one is dead");
        } else {
            if (!TotalPlayers[targetPlayer - 1].isStatus())
                System.out.println("Player" + TotalPlayers[targetPlayer - 1].getPlayerID() + " has dead");
            else
                System.out.println("no Mafia is dead");

        }
        int vote = -1;  // user is dead
        int votingResult = -1;
        if (user.isStatus()) {  //user is alive
            while (votingResult < 1) {
                while (true) {
                    try {
                        System.out.print("Select a person to vote out: ");
                        vote = input.nextInt();
                        input.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("incorrect input, try again");
                        input.nextLine();
                        continue;
                    }
                    if (vote < 0 || vote > total_players) {
                        System.out.println("Invalid input!!, try again");
                        continue;
                    } else if (TotalPlayers[vote - 1].isStatus() == false) {  //if test player is dead
                        System.out.println("Can't test a dead person");
                        continue;
                    } else if (TotalPlayers[vote - 1].getClass() == user.getClass()) {
                        System.out.println("Can't test people of same community");
                    } else {
                        break;
                    }
                }
                votingResult = doVote(vote);
                if (votingResult < 1) {
                    System.out.println("No one is voted out!!");
                    System.out.println("Please do vote again");
                    continue;
                } else {
                    break;
                }
            }

            System.out.println("\nPlayer" + votingResult + " has been voted out.");
            killDirectly(votingResult); // directly kill the person

        } else {
            while (vote == -1) {
                vote = random.nextInt(total_players);
                if (vote == -1) {
                    continue;
                } else {
                    if (TotalPlayers[vote].getClass() == user.getClass())
                        continue;
                    else
                        break;
                }
            }
            votingResult = doVote(vote);
            while (votingResult == -1)
                votingResult = doVote(vote);
            System.out.println("\nPlayer" + votingResult + " has been voted out.");
            killDirectly(votingResult); // directly kill the person
        }


    }

    private static void killDirectly(int votingResult) {
        TotalPlayers[votingResult - 1].setStatus(false);

        if (TotalPlayers[votingResult - 1].getClass() == Mafia.class) {
            mafiaAlive--;
//            System.out.println("mafia died");
            return;
        }
        if (TotalPlayers[votingResult - 1].getClass() == Detective.class) {
            detectiveAlive--;
//            System.out.println("detective died");
            return;
        }
        if (TotalPlayers[votingResult - 1].getClass() == Healer.class) {
//            System.out.println("healer died");
            healerAlive--;
            return;
        }
        if (TotalPlayers[votingResult - 1].getClass() == Commoner.class) {
            commonerAlive--;
            return;
        }

    }

    private static int testSomeone() {
        int testRandom = random.nextInt(total_players);
        while (!TotalPlayers[testRandom].isStatus() && TotalPlayers[testRandom].getClass() != user.getClass()) {
            testRandom = random.nextInt(total_players);
        }
        return testRandom + 1;
    }

    private static void heal(int healedPlayer) {

        if (healedPlayer != -1)
            TotalPlayers[healedPlayer - 1].setHP(TotalPlayers[healedPlayer - 1].getHP() + 500);//healed the player chosen by healers
    }

    private static void detectiveTest(int testPlayer) { //return true if mafia is caught otherwise false
        if (TotalPlayers[testPlayer - 1].getClass() != Mafia.class) {  //if detective test is negative i.e. Mafia is not caught by detective
            System.out.println("Player" + testPlayer + " is not a Mafia");
//            System.out.println("Mafia is not caught by detective");

        } else { //if test person is mafia
            System.out.println("Player" + testPlayer + " is Mafia");
//            System.out.println("Player" + testPlayer + " has been killed");
            killDirectly(testPlayer);  //mafia has been killed
        }
    }

    private static void playasMafia() {
        Scanner input = new Scanner(System.in);
        int healedPlayer = healSomeone(); //healer chose player to heal
        int testPlayer = testSomeone();  //detective test for Mafia
        if (user.isStatus()) {//if user is alive then input is taken
            int targetPlayer = 0;
            while (true) {
                try {
                    System.out.print("Choose a target: ");
                    targetPlayer = input.nextInt();
                    input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("incorrect input, try again");
                    input.nextLine();
                    continue;
                }
                if (targetPlayer < 0 || targetPlayer > total_players) {
                    System.out.println("Invalid input!!, try again");
                    continue;
                } else if (TotalPlayers[targetPlayer - 1].isStatus() == false) {  //if test player is dead
                    System.out.println("Can't test a dead person");
                    continue;
                } else if (TotalPlayers[targetPlayer - 1].getClass() == user.getClass()) {
                    System.out.println("Can't test people of same community");
                } else {
                    break;
                }
            }
            killTarget(targetPlayer);
            System.out.println("Detectives have chosen a player to test");
            System.out.println("Healers have chosen someone to heal.");
            //but detective can test for mafia
            System.out.println("-------------End of Action---------------------");
            heal(healedPlayer);
            detectiveTest(testPlayer); //check Mafia has been caught or not
            DeadorAlive(targetPlayer, healedPlayer);


        } else { //user is dead
            int targetPlayer = chooseTarget();   //Mafia is choosing target;

            System.out.println("player chose to heal: " + healedPlayer);
            System.out.println("-------------End of Action---------------------");
            killTarget(targetPlayer);
            heal(healedPlayer);
            detectiveTest(testPlayer);
            DeadorAlive(targetPlayer, healedPlayer);  //check whether a target person is dead or alive


        }

    }

    private static void playasDetective() {
        Scanner input = new Scanner(System.in);
        System.out.println("Mafia have chosen their target");
        int targetPlayer = chooseTarget();   //Mafia is choosing target;
        int healedPlayer = healSomeone(); //healer chose player to heal
        killTarget(targetPlayer);
        heal(healedPlayer);
        if (user.isStatus()) {//if user is alive then input is taken
            int testplayer;
            while (true) {
                try {
                    System.out.print("Choose a player to test: ");
                    testplayer = input.nextInt();
                    input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("incorrect input, try again");
                    input.nextLine();
                    continue;
                }
                if (testplayer < 0 || testplayer > total_players) {
                    System.out.println("Invalid input!!, try again");
                    continue;
                } else if (TotalPlayers[testplayer - 1].isStatus() == false) {  //if test player is dead
                    System.out.println("Can't test a dead person");
                    continue;
                } else if (TotalPlayers[testplayer - 1].getClass() == user.getClass()) {
                    System.out.println("Can't test people of same community");
                } else {
                    break;
                }
            }
            System.out.println("-------------End of Action---------------------");
            detectiveTest(testplayer);
            DeadorAlive(targetPlayer, healedPlayer);  //check whether a target person is dead or alive
        } else { //user is dead
            detectiveTest(testSomeone());
            System.out.println("-------------End of Action---------------------");
            DeadorAlive(targetPlayer, healedPlayer);  //check whether a target person is dead or alive

        }

    }

    private static void playasHealer() {
        Scanner input = new Scanner(System.in);
        int targetPlayer = chooseTarget();   //Mafia is choosing target;
        if (user.isStatus()) {//if user is alive then input is taken

            int healedPlayer;
            while (true) {
                try {
                    System.out.print("Choose a player to heal: ");
                    healedPlayer = input.nextInt();
                    input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("incorrect input, try again");
                    input.nextLine();
                    continue;
                }
                if (healedPlayer < 0 || healedPlayer > total_players) {
                    System.out.println("Invalid input!!, try again");
                    continue;
                } else if (targetPlayer != healedPlayer && !TotalPlayers[targetPlayer - 1].isStatus()) {  //if test player is dead
                    System.out.println("Can't test a dead person");
                    continue;
                } else {
                    break;
                }
            }
            killTarget(targetPlayer);
            heal(healedPlayer);
            int testPlayer = testSomeone(); //detective test for Mafia
            System.out.println("----------End of Action-----------");
            detectiveTest(testPlayer);
            DeadorAlive(targetPlayer, healedPlayer);
        } else { //user is dead
            int healedPlayer = healSomeone(); //healer chose player to heal
            killTarget(targetPlayer);
            heal(healedPlayer);
            detectiveTest(testSomeone());
            System.out.println("-------------End of Action---------------------");
            DeadorAlive(targetPlayer, healedPlayer);  //check whether a target person is dead or alive

        }

    }

    private static void playasCommoner() {
        Scanner input = new Scanner(System.in);
        int targetPlayer = chooseTarget();   //Mafia is choosing target;
        int healedPlayer = healSomeone(); //healer chose player to heal
        killTarget(targetPlayer);
        heal(healedPlayer);
        if (user.isStatus()) {//if user is alive then input is taken
            System.out.println("Mafia is chosen their target");
            System.out.println("Healers have chosen someone to heal.");
            System.out.println("-------------End of Action---------------------");
            detectiveTest(testSomeone());
            DeadorAlive(targetPlayer, healedPlayer);  //check whether a target person is dead or alive
        } else { //user is dead
            detectiveTest(testSomeone());
            System.out.println("-------------End of Action---------------------");
            DeadorAlive(targetPlayer, healedPlayer);  //check whether a target person is dead or alive
        }

    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Mafia");
        while (true) {
            System.out.print("Enter Numbers of Players: ");
            try {
                total_players = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("incorrect input, try again");
                input.nextLine();
                continue;
            }
            if (total_players < 6) {
                System.out.println("Enter players no greater than or equal to 6");
            } else {
                break;
            }
        }
        TotalPlayers = new Player[total_players];
        makeRole();
        System.out.println("Choose a Character\n" +
                "1) Mafia\n" +
                "2) Detective\n" +
                "3) Healer\n" +
                "4) Commoner\n" +
                "5) Assign Randomly");
        int roleChosen;
        while (true) {
            try {
                roleChosen = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("incorrect input, try again");
                input.nextLine();
                continue;
            }

            if (roleChosen < 1 || roleChosen > 5) {
                System.out.println("Invalid input!!, try again");
            } else {
                break;
            }
        }
        createUser(roleChosen);
        int round = 1;
        while (!GameOver()) {
            System.out.println("Round " + round + ":");
            playerRemaining();
            if (user.getClass() == Detective.class) {  //if user is detective
                playasDetective();
            }
            if (user.getClass() == Mafia.class) {
                playasMafia();
            }
            if (user.getClass() == Commoner.class) {
                playasCommoner();
            }
            if (user.getClass() == Healer.class) {
                playasHealer();
            }
            System.out.println("-------End of Round " + round + "-----------");
            round++;
        }
        declareWinner();

    }
}

