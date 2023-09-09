// import java.io.*;
import java.util.Scanner;



class EscapeRoom {
    static int[] trackables = {0, 0, 0, 0, 0, 0, 0}; //1 - key, 2 - totem, 3 - basement lights, 4 - generator power, 5 - gate electricity, 6 - injured, 7 - trap closed
    static int[] escapes = {0, 0, 0}; //1 - mineshaft escape, 2 - gate escape, 3 - well escape
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        intro();
    }

    public static void intro()
    {
        System.out.println("You find yourself tied up in a basement");
        System.out.println("-------------------------------");
        System.out.println("(1) Untie yourself");
        System.out.println("(2) Do nothing");
        System.out.println("-------------------------------");
        //store user's next move
        String move = s.nextLine();
        if (move.equals("1")) {
            basement();
        } else {
            System.out.println("You die");
        }
    }

    public static void injured() {
        if (trackables[5] == 0) {
            System.out.println("You were injured!");
            // System.out.println("You have 60 seconds to escape or else you will die");
            trackables[5]++;
        }
    }

    public static void basement() {
        System.out.println("In front of you are two paths, a door and a vent.");
        System.out.println("-------------------------------");
        System.out.println("(1) Go through door");
        System.out.println("(2) Go through vent");
        System.out.println("(3) Explore basement");
        System.out.println("-------------------------------");
        String move = s.nextLine();
        if (move.equals("1")) {
            System.out.println("You go through the door");
            upstairs();
        } else if (move.equals("2")) {
            System.out.println("You crawl through the vent");
            System.out.println("The vent leads to a dark storage room. You can barely make out a generator across the room. You see a key on the workbench next to you.");
            storage();
        } else if (move.equals("3")) {
            System.out.println("At the end of the room is a large metal door with 4 colored switches");
            mineshaft();
        }
    }

    public static void storage() {
        if (trackables[0] == 0) {
            System.out.println("-------------------------------");
            System.out.println("(1) Go to generator");
            System.out.println("(2) Toggle light switch");
            System.out.println("(3) Return to basement");
            System.out.println("(4) Pick up key");
            System.out.println("-------------------------------");
        } else {
            System.out.println("-------------------------------");
            System.out.println("(1) Go to generator");
            System.out.println("(2) Toggle light switch");
            System.out.println("(3) Return to basement");
            System.out.println("-------------------------------");
        }

        String move = s.nextLine();
        if (move.equals("1")) {
            //Go to gen
            if(trackables[6] == 0) {
                if (trackables[2]==1) {
                    System.out.println("You dodge the bear trap and go to the generator");
                    generator();
                } else {
                    if (trackables[6] == 0) {
                        System.out.println("As you walk to the generator, you step in a bear trap you couldn't see!");
                        trackables[6] = 1;
                        if (trackables[5] == 1) 
                        {
                                System.out.println("You were injured again!");
                                System.out.println("You died");
                        }
                        else 
                        {
                            injured();
                            generator();
                        }
                    }
                }
            } else {
                System.out.println("You go to the generator");
                generator();
            }
        } else if (move.equals("2")) {
            //Turn on light
            if (trackables[2] == 0) {
                System.out.println("You turn on the light, and notice a bear trap between you and the generator. It looks easy to dodge.");
                trackables[2] = 1;
            } else if (trackables[2] == 1) {
                System.out.println("You turn off the lights and can't see well anymore");
                trackables[2] = 0;
            }
            storage();

        } else if (move.equals("3")) {
            //Return to basement
            System.out.println("You make your way back to the basement");
            basement();

        } else if (move.equals("4")) {
            //Pick up key
            System.out.println("You pick up and store the key");
            trackables[0] = 1;
            storage();
        }

    }

    public static void generator() {
        System.out.println("-------------------------------");
        System.out.println("(1) Toggle generator");
        System.out.println("(2) Return to storage room entrance");
        System.out.println("-------------------------------");
        String move = s.nextLine();
        
        if (move.equals("1")) {
            if (trackables[3] == 0) {
                System.out.println("You power the generator on");
                trackables[3] = 1;
            } else {
                System.out.println("You power the generator off");
                trackables[3] = 0;
            }
            generator();
        } else if (move.equals("2")) {
            System.out.println("You go back to the storage entrance");
            storage();
        }
    }

    public static void upstairs() {
        System.out.println("The door leads to a large fenced in field. Next to you is a deep well. At one end of the field is a metal gate next to a switch. At the other end is a blue car");
        System.out.println("-------------------------------");
        System.out.println("(1) Go to switch");
        System.out.println("(2) Go to gate");
        System.out.println("(3) Go to well");
        System.out.println("(4) Go to car");
        System.out.println("(5) Return to basement");
        System.out.println("-------------------------------");
        String move = s.nextLine();
        if (move.equals("1")) {
            //go to switch
            System.out.println("You walk up to the rusty switch");
            swwitch();
        } else if (move.equals("2")) {
            //go to gate
            System.out.println("You walk up to the large gate");
            gate();
        } else if (move.equals("3")) {
            //go to well
            System.out.println("You walk up to the well. It looks very deep.");
            well();
        } else if (move.equals("4")) {
            //go to car
            System.out.println("You walk up to the car. It looks like it won't run anytime soon");
            car();
        } else if (move.equals("5")) {
            basement();
        }
        
    }

    public static void swwitch() {
        System.out.println("-------------------------------");
        System.out.println("(1) Pull it");
        System.out.println("(2) Go back");
        System.out.println("-------------------------------");
        String move = s.nextLine();
        if (move.equals("1")) {
            if(trackables[3] == 0) //generator switched off
            {
                System.out.println("You pull the switch. You can't tell if anything happened");
            } 
            else if(trackables[3] == 1) //generator switched on
            {
                if(trackables[4] == 0) //gate electricity on
                {
                    System.out.println("You pull the switch. The electric humming of the gate stops");
                    trackables[4] = 1;
                }
                else if(trackables[4] == 1) //gate electricity already off
                {
                    System.out.println("You pull the switch. The electric humming of the gate starts again");
                    trackables[4] = 0;
                }
            }
            swwitch();
        } 
        else if (move.equals("2")) 
        {
            upstairs();
        }
        
    }

    public static void gate() {
        System.out.println("-------------------------------");
        System.out.println("(1) Pull it");
        System.out.println("(2) Go back");
        System.out.println("-------------------------------");
        String move = s.nextLine();
        if(move.equals("1")) 
        {
            if(trackables[4] == 0) //gate electricity on
            {
                System.out.println("You touch the gate and are electrocuted!");
                if (trackables[5] == 1) 
                {
                        System.out.println("You were injured again!");
                        System.out.println("You died");
                }
                else 
                {
                    injured();
                    gate();
                }
            }
            else if(trackables[4] == 1) //gate electricity off
            {
                System.out.println("You open the gate");
                System.out.println("You escaped!");
                escapes[1] = 1;
                endgame();
            }
        }
        else if(move.equals("2"))
        {
            upstairs();
        }

    }

    public static void well() 
    {
        System.out.println("-------------------------------");
        System.out.println("(1) Jump down");
        System.out.println("(2) Go back");
        System.out.println("-------------------------------");
        String move = s.nextLine();
        if(move.equals("1")) 
        {
            //if totem not in inventory
            System.out.println("You hold your breath and fall down the well");
            if(trackables[1] == 0)
            {
                System.out.println("You died");
            }
            else if(trackables[1] == 1)
            {
                System.out.println("The totem in your pocket emits a strange aura");
                System.out.println("You land in the water and swim away to safety");
                System.out.println("You escaped!");
                escapes[2] = 1;
                endgame();
            }
        }
        else if(move.equals("2"))
        {
            upstairs();
        }
    }

    public static void car() 
    {
        System.out.println("-------------------------------");
        System.out.println("(1) Open glovebox");
        System.out.println("(2) Open trunk");
        System.out.println("(3) Go back");
        System.out.println("-------------------------------");
        String move = s.nextLine();
        if(move.equals("1"))
        {
            System.out.println("You check the glovebox. It's empty");
            System.out.println("Inside are etched the letters YBGR");
            car();
        }
        else if(move.equals("2"))
        {
            if(trackables[1] == 1) //totem already found
            {
                System.out.println("The trunk is empty");
            }
            else //totem not found yet
            {
                System.out.println("The trunk is locked");
                if(trackables[0] == 1)
                {
                    System.out.println("You use the key to unlock the trunk");
                    System.out.println("Inside is a strange totem. It emits a mysterious aura");
                    trackables[1] = 1;
                }
            }
            car();
        }
        else if(move.equals("3"))
        {
            upstairs();
        }
    }

    public static void mineshaft()
    {
        System.out.println("-------------------------------");
        System.out.println("(1) Pull blue switch");
        System.out.println("(2) Pull red switch");
        System.out.println("(3) Pull yellow switch");
        System.out.println("(4) Pull green switch");
        System.out.println("(5) Go back");
        System.out.println("-------------------------------");
        String move = s.nextLine();
        if(move.equals("5"))
        {
            System.out.println("You walk back to the other end of the basement");
            basement();
        }
        if(move.equals("3"))
        {
            System.out.println("The light above the yellow switch turns green");
            System.out.println("-------------------------------");
            System.out.println("(1) Pull blue switch");
            System.out.println("(2) Pull red switch");
            System.out.println("(3) Pull green switch");
            System.out.println("(4) Go back");
            System.out.println("-------------------------------");
            move = s.nextLine();
            if(move.equals("4"))
            {
                System.out.println("As you walk back, the lights above all the switches turn red again");
                basement();
            }
            else if(move.equals("1"))
            {
                System.out.println("The light above the blue switch turns green");
                System.out.println("-------------------------------");
                System.out.println("(1) Pull red switch");
                System.out.println("(2) Pull green switch");
                System.out.println("(3) Go back");
                System.out.println("-------------------------------");
                move = s.nextLine();
                if(move.equals("3"))
                {
                    System.out.println("As you walk back, the lights above all the switches turn red again");
                    basement();
                }
                else if(move.equals("2"))
                {
                    System.out.println("The light above the green switch turns green");
                    System.out.println("-------------------------------");
                    System.out.println("(1) Pull red switch");
                    System.out.println("(2) Go back");
                    System.out.println("-------------------------------");
                    move = s.nextLine();
                    if(move.equals("2"))
                    {
                        System.out.println("As you walk back, the lights above all the switches turn red again");
                        basement();
                    }
                    else if(move.equals("1"))
                    {
                        System.out.println("After pulling the last switch, the light above the metal door turns green. It leads to an abandoned mineshaft");
                        System.out.println("-------------------------------");
                        System.out.println("(1) Enter mineshaft");
                        System.out.println("(2) Go back");
                        System.out.println("-------------------------------");
                        move = s.nextLine();
                        if(move.equals("1")) 
                        {
                            System.out.println("Inside the mineshaft, you find a working coal mine elevator. The rusty elevator brings you to the surface");
                            System.out.println("You escaped!");
                            escapes[0] = 1;
                            endgame();
                        }
                        else if(move.equals("2"))
                        {
                            System.out.println("As you walk away, the metal door slowly closes and the lights above the switches turn red");
                            basement();
                        }
                    }
                }
                else
                {
                    wrongSwitch();
                }
            }
            else
            {
                wrongSwitch();
            }
        }
        else
        {
            wrongSwitch();
        }

    }

    public static void wrongSwitch()
    {
        System.out.println("The lights above all the switches turn red, and you are electrocuted!");
        if (trackables[5] == 1) 
        {
            System.out.println("You were injured again!");
            System.out.println("You died");
        }
        else
        {
            injured();
            mineshaft();
        }
    }

    public static void endgame()
    {
        int sum = 0;
        for (int num : escapes)
        {
            sum += num;
        }
        System.out.println("You have found " + sum + "/3 escapes");
        System.out.println("-------------------------------");
        System.out.println("(1) Try again");
        System.out.println("(2) Quit");
        System.out.println("-------------------------------");
        String move = s.nextLine();
        if(move.equals("1"))
        {
            intro();
        }
    }
}
