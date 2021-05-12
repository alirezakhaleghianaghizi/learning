import java.util.Scanner;

public class InputProcessor {
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
