public class Bomb {
    private static String[] types={"common","rare","super"};
    private String type;
    private int price;
    private int damage;
    private int row;
    private int col;

    public Bomb(int row, int col) {
        this.row = row;
        this.col = col;
        this.type=types[0];
        this.price=25;
        this.damage=25;
    }

    public Bomb(String type, int row, int col) {
        this.type = type;
        this.row = row;
        this.col = col;
        if(type.equalsIgnoreCase("common")){
            this.price=25;
            this.damage=25;
        }
        else if(type.equalsIgnoreCase("rare")){
            this.price=75;
            this.damage=75;
        }
        else if(type.equalsIgnoreCase("super")){
            this.price=150;
            this.damage=150;
        }
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


}
