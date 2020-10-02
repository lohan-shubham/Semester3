class Special extends Customer implements  Reward{
    int my_reward;
    public Special(String name, String address, float wallet, String category) {
        super(name, address, wallet, category);
    }

    @Override
    public int calcReward(float bill) {
        int reward = 0;
        for (int i = 0; i < getRestaurants().size(); i++) {
            String cat = getRestaurants().get(i).getCategory();
            if (cat.equals("")) {
                reward = (int) (bill / 100) * 5;
                my_reward += reward;
            } else if (cat.equals("Fast food")) {
                reward = (int) (bill / 150) * 10;
                my_reward += reward;
            } else {
                reward = (int) (bill / 200) * 25;
                my_reward += reward;
            }

        }
        return reward;
    }

}