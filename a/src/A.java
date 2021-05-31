public enum A {
    CLOTH(100,4,6),
    COOKIE(80,4,6),
    EGG(15,1,4),
    FEATHER(20,1,4),
    FLOUR(40,2,5),
    GRASS(0,1,-1),
    ICECREAM(120,4,6),
    MILK(25,1,4),
    SEPARATEDMILK(60,2,5),
    SILK(50,2,5),
    WATER(0,1,-1),
    BEAR(300,15,5),
    LION(400,15,5),
    TIGER(500,15,5)
    ;
    int cost;
    int capacity;
    int timeOfRemaining;
    A(int cost, int capacity,int timeOfRemaining) {
        this.capacity=capacity;
        this.cost=cost;
        this.timeOfRemaining=timeOfRemaining;
    }

    public int getTimeOfRemaining() {
        return timeOfRemaining;
    }

    public int getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }
}
