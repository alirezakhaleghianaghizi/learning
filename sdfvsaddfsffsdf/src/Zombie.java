import java.util.Random;

public class Zombie {
    private static final  int MAX_HEALTHE=120;
    private int health;
    private int row;
    private int col;

    public Zombie(int row, int col) {
        this.row = row;
        this.col = col;
        Random rand = new Random();
        this.health=rand.nextInt(MAX_HEALTHE)+1;
    }

    public int getHealth() {
        return health;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public void tackDamage(int Damage){
        this.health=this.health-Damage;
        if(this.health<0) this.health=0;
    }

    public void setCol() {
        this.col--;
    }

}
