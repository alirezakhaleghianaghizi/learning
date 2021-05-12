import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        InputProcessor inputProcessor = new InputProcessor(manager);
        inputProcessor.run();
    }
}/*
class Manager {
    private String playerName;
    private int playerCoins;
    protected ArrayList<Zombie> zombies;
    private ArrayList<Bomb> bombs;
    private int[][] map;
    protected   int maxRow ;
    protected   int maxColumn ;
    private final int MAX_ZOMBIES=20;
    private int zombiesCounter;
    private int step ;
    private int level;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public Manager() {
        this.zombies=new ArrayList<>();
        this.bombs=new ArrayList<>();
        this.playerCoins=500;
    }

    public void setMap(int maxColumn,int maxRow){
        this.map=new int [maxRow+1][maxColumn+1];
    }

    public void addBomb(Bomb bomb){


        if(bomb.getType().equalsIgnoreCase("common")&&playerCoins>25){
            this.map[bomb.getRow()][bomb.getCol()]=2;
            this.bombs.add(bomb);
            playerCoins-=25;
        }
        else if(bomb.getType().equalsIgnoreCase("rare")&&playerCoins>75){
            this.map[bomb.getRow()][bomb.getCol()]=3;
            this.bombs.add(bomb);
            playerCoins-=75;
        }
        else if(bomb.getType().equalsIgnoreCase("super")&&playerCoins>150) {
            this.map[bomb.getRow()][bomb.getCol()]=4;
            this.bombs.add(bomb);
            playerCoins-=150;
        }
        else{
            System.err.println("you need mor mony");
        }
    }

    public void chekZombi(int col,int row){
        boolean isThere=false;
        for (int i = 0; i < zombies.size(); i++) {
            if(zombies.get(i).getCol()==col&&zombies.get(i).getRow()==row){
                System.out.printf("zombies health: %d , zombies row : %d , zombies col : %d",zombies.get(i).getHealth(),zombies.get(i).getCol(),zombies.get(i).getRow());
                isThere=true;
                break;
            }
        }
        if(isThere==false){
            System.err.println("there is no zombie there");
        }
    }

    private int randoming(int bound){
        Random rand = new Random();
        return rand.nextInt(bound);
    }

    public void request(){
        int random=randoming(10);
        if(random<=5){
            playerCoins+=50;
        }
    }

    public void addzombie(){
        int random=randoming(10)+1;
        int sathOfGame=0;
        if(level==1)sathOfGame=6;
        else if(level==2)sathOfGame=8;

        if(random<=sathOfGame&&zombiesCounter<20&&level!=3){
            zombies.add(new Zombie(random=randoming(maxRow)+1, maxColumn));
            zombiesCounter++;
            this.map[random][maxColumn]=1;
        }
        else{

            for (int i = 0; i <3; i++) {
                random=randoming(10)+1;
                if(i==0)   sathOfGame=2;
                if(i==1)   sathOfGame=3;
                if(i==2)   sathOfGame=5;
                if(random<=sathOfGame&&zombiesCounter<20){
                    zombies.add(new Zombie(random=randoming(maxRow)+1, maxColumn));
                    zombiesCounter++;
                    this.map[random][maxColumn]=1;
                }
            }
        }
    }

    public int getZombiesCounter() {
        return zombiesCounter;
    }

    public int getStep() {
        return step;
    }

    public int getPlayerCoins() {
        return playerCoins;
    }

    public int getMAX_ZOMBIES() {
        return MAX_ZOMBIES;
    }

    public void setZombiesCounter(int zombiesCounter) {
        this.zombiesCounter = zombiesCounter;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void goForward(){
        for (int i = 0; i < zombies.size(); i++) {
            map[zombies.get(i).getRow()][zombies.get(i).getCol()]=0;
            zombies.get(i).setCol();
            if(map[zombies.get(i).getRow()][zombies.get(i).getCol()]==2||map[zombies.get(i).getRow()][zombies.get(i).getCol()]==3||map[zombies.get(i).getRow()][zombies.get(i).getCol()]==4){
                checkTheMap(zombies.get(i).getCol(),zombies.get(i).getRow(),i);
                if(zombies.get(i).getHealth()==0){
                    map[zombies.get(i).getRow()][zombies.get(i).getCol()]=0;
                    zombies.remove(i);
                    i--;
                }else map[zombies.get(i).getRow()][zombies.get(i).getCol()]=1;
            }
            else {
                if(zombies.get(i).getHealth()<0){
                    map[zombies.get(i).getRow()][zombies.get(i).getCol()]=0;
                    zombies.remove(i);
                    i--;
                }else map[zombies.get(i).getRow()][zombies.get(i).getCol()]=1;
            }
        }
    }

    public void checkTheMap(int col,int row,int zombinum){
        for (int j = 0; j < bombs.size(); j++) {
            if(row==bombs.get(j).getRow()&&col==bombs.get(j).getCol()){
                zombies.get(zombinum).tackDamage(bombs.get(j).getDamage());
                bombs.remove(j);
                j--;
                deletMap(row,col);
            }
        }
    }


    private void deletMap(int i,int j){
        map[i][j]=0;
    }

    public void printMap(){
        for (int i = 1; i <= maxRow; i++) {
            System.out.printf("%d :",i);
            for (int j = 1; j <= maxColumn; j++) {
                printHouse(i,j);
            }
            System.out.printf(": %d\n",i);
        }
    }

    public void printHouse(int i,int j){
        if(map[i][j]==1) System.out.print("<");
        else if(map[i][j]==2) System.out.print("c");
        else if(map[i][j]==3) System.out.print("r");
        else if(map[i][j]==4) System.out.print("s");
        else System.out.print(" ");
    }
}
class InputProcessor {
    private Manager manager;
    private Scanner scanner = new Scanner(System.in);
    boolean exit=false;

    public InputProcessor(Manager manager) {
        this.manager = manager;
    }

    public void run(){
        String get;
        manager.setStep(0);
        manager.setZombiesCounter(0);
        System.out.println("ENTER LEVEL OF GAME");
        manager.setLevel(Integer.parseInt(scanner.nextLine()));
        System.out.println("ENTER yuor name");
        manager.setPlayerName(scanner.nextLine());
        System.out.println("ENTER ROW OF GAME ");
        manager.maxRow =Integer.parseInt(scanner.nextLine());
        System.out.println("ENTER COLUMN OF GAME ");
        manager.maxColumn =Integer.parseInt(scanner.nextLine());
        manager.setMap(manager.maxColumn,manager.maxRow);
        while (!(get =scanner.nextLine()).equalsIgnoreCase("exit")&&exit==false){
            if(manager.getZombiesCounter()==20&&manager.zombies.size()==0){
                System.out.println("YOU WIN THE GAME");
                exit=true;
                break;
            }
            manager.addzombie();
            if(get.startsWith("put bomb")){
                putingBomb(get.split("\\s"));
            }
            else if(get.startsWith("check zombie")){
                checkingZombi(get.split("\\s"));
            }
            else if(get.startsWith("request coin")){
                manager.request();
            }
            else{
                System.err.println("invalid request");
            }
            manager.setStep(manager.getStep()+1);
            System.out.println("Enter your command : "+get);
            manager.printMap();
            System.out.printf("Step %d, you have %d coins\nRemaining zombies = %d\n",manager.getStep(),manager.getPlayerCoins(),20-manager.getZombiesCounter());
            for (int i = 0; i < manager.zombies.size(); i++) {
                if(manager.zombies.get(i).getCol()<=0){
                    System.out.println("YOU LOAS THE GAME");
                    exit=true;
                    break;
                }
            }
            if(exit==false)
                manager.goForward();
        }
    }

    private void putingBomb(String []dastoors){
        if(dastoors.length==5){
            Bomb bomb=new Bomb(dastoors[4],Integer.parseInt(dastoors[2]),Integer.parseInt(dastoors[3]));
            this.manager.addBomb(bomb);
        }
        else {
            Bomb bomb=new Bomb(Integer.parseInt(dastoors[2]),Integer.parseInt(dastoors[3]));
            this.manager.addBomb(bomb);
        }
    }

    private void checkingZombi(String []dastoors){
        manager.chekZombi(Integer.parseInt(dastoors[3]),Integer.parseInt(dastoors[2]));
    }
}
class Bomb {
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
class Zombie {
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

}*/
