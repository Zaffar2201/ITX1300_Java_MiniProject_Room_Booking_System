/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room_booking.system;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.*;

public class Room_BookingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int index = 0;
        float price;
        String type, roomId;
        boolean condition = true;
        int optionAns;

        // user_Authentication();
        Room[] roomArray = new Room[10];
        roomArray[0] = new Room("SB01", "SB", 'N', 100, 01, 1);
        roomArray[1] = new Room("SB02", "SB", 'N', 100, 02, 7);
        roomArray[2] = new Room("DB01", "DB", 'Y', 150, 0, 0);
        roomArray[3] = new Room("DB02", "DB", 'N', 150, 03, 5);
        roomArray[4] = new Room("DB03", "DB", 'Y', 150, 0, 0);
        roomArray[5] = new Room("SP01", "SP", 'Y', 300, 0, 0);
        roomArray[6] = new Room("SP02", "SP", 'Y', 300, 0, 0);
        roomArray[7] = new Room("SP03", "SP", 'N', 300, 05, 12);
        roomArray[8] = new Room();
        roomArray[9] = new Room();

        //displayRooms(roomArray);  
        // displayAvailable(roomArray);   
        while (condition) {

            optionAns = Integer.parseInt(JOptionPane.showInputDialog("Please enter your choice:\n1) Display all rooms\n2) Display rooms available\n3) Display Room on loaned\n4) Add a Room\n5) Delete a room"));

            switch (optionAns) {

                case 1:
                    displayRooms(roomArray);
                    break;

                case 2:
                    displayAvailable(roomArray);
                    break;

                case 3:
                    outer:
                    displayOnLoanes(roomArray);
                    break;

                case 4:
                    index = returnIndex(roomArray);
                    switch (index) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:

                            type = (JOptionPane.showInputDialog("Please enter type of room desired").toUpperCase());

                            while (!(type.equals("SB") || type.equals("DB") || type.equals("SP"))) {

                                JOptionPane.showMessageDialog(null, "Invalid room type", "ERROR", JOptionPane.ERROR_MESSAGE);
                                type = (JOptionPane.showInputDialog("Please re-enter type of room desired[SB-DB-SP}").toUpperCase());

                            }
                            do {
                                try {
                                    price = Float.valueOf(JOptionPane.showInputDialog("Please enter price of the room "));
                                    break;
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, "Invalid price", "ERROR", JOptionPane.ERROR_MESSAGE);

                                }
                            } while (true);
                            
                            while (price <= 0) {
                                JOptionPane.showMessageDialog(null, "Invalid price!", "ERROR", JOptionPane.ERROR_MESSAGE);
                                price = Float.valueOf(JOptionPane.showInputDialog("Please enter price of the room "));
                            }

                            roomArray[index].setType(type);
                            roomArray[index].setRoomId(type + "0" + (maxRoom(roomArray, type) + 1));
                            roomArray[index].setAvailability('Y');
                            roomArray[index].setCusId(0);
                            roomArray[index].setDaysBooked(0);
                            roomArray[index].setPrice(price);

                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Maximun number of rooms reached", "Overflow Error", JOptionPane.ERROR_MESSAGE);

                            break;

                    }
                    condition = true;
                    break;

                case 5:

                    roomId = (JOptionPane.showInputDialog("Please enter roomId").toUpperCase());
                    if (roomId.length() > 4 || roomId.length() < 4) {
                        JOptionPane.showMessageDialog(null, "Invalid roomId", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        deleteRoom(roomArray, roomId);
                    }

                    break;

            }

        }

    }//end main

    public static void user_Authentication() {
        String password, filepassword;
        int attempt = 1;

        Scanner input = new Scanner(System.in);
        Files z = new Files();

        z.openFile("C:\\Users\\HP\\Desktop\\Candos\\password.txt");
        filepassword = z.passwordReadFile();
        z.closeFile();

        System.out.print("Attempt 1\nPlease enter password : ");
        password = input.nextLine();

        if (password.equals(filepassword)) {
            System.out.println("Welcome Master!!!");
        }

        while (!(password.equals(filepassword))) {
            System.out.println();
            attempt++;
            System.out.print("Attempt " + attempt + "\nPlease re-enter password : ");
            password = input.nextLine();

            if ((attempt == 3) && (!(password.equals(filepassword)))) {

                System.out.println("Sorry!!!!");
                System.exit(0);
            }

            if (password.equals(filepassword)) {
                System.out.println("Welcome Master!!!");
            }

        }

    }

    public static void displayRooms(Room[] x) {

        System.out.println("RoomID Type   Available Price CusID Days Booked");

        for (int i = 0; i < 10; i++) {

            switch (x[i].getType()) {

                case "null":
                    System.out.println(x[i].getRoomId() + "   " + x[i].getType() + "   " + x[i].getAvailability() + "         " + x[i].getPrice() + "    " + x[i].getCusId() + "      " + x[i].getDays_Booked());
                    break;

                default:
                    System.out.println(x[i].getRoomId() + "   " + x[i].getType() + "     " + x[i].getAvailability() + "         " + x[i].getPrice() + "  " + x[i].getCusId() + "      " + x[i].getDays_Booked());
                    break;
            }

        }

    }

    public static void displayAvailable(Room[] y) {
        System.out.println("RoomID Type Price");
        for (int i = 0; i < 10; i++) {

            if (y[i].getAvailability() == 'Y' && (!(y[i].getRoomId().equals("null")))) {
                System.out.println(y[i].getRoomId() + "   " + y[i].getType() + "   " + y[i].getPrice());

            }

        }

    }

    public static void displayOnLoanes(Room[] z) {
        System.out.println("RoomID Type CusId");
        for (int i = 0; i < 10; i++) {

            if (z[i].getAvailability() == 'N' && (!(z[i].getRoomId().equals("null")))) {
                System.out.println(z[i].getRoomId() + "   " + z[i].getType() + "   " + z[i].getCusId());

            }

        }

    }

    public static int returnIndex(Room[] x) {
        int index = 0;
        for (int i = 0; i < 10; i++) {
            if (x[i].getRoomId().equals("null")) {
                index = i;
                break;
            }

        }

        return index;

    }

    public static int maxRoom(Room[] x, String type) {
        int max = 0;
        for (int i = 0; i < 10; i++) {
            if ((x[i].getRoomId().substring(0, 2)).equals(type)) {

                if (Integer.parseInt(x[i].getRoomId().substring(2, 4)) > max) {
                    max = Integer.parseInt(x[i].getRoomId().substring(2, 4));

                }

            }

        }

        return max;
    }
    
    public static void deleteRoom(Room[] x, String roomId) {
        boolean found = false;
        for (int i = 0; i < 10; i++) {
            if (x[i].getRoomId().equalsIgnoreCase(roomId)) {
                found = true;
                x[i].setType("null");
                x[i].setRoomId("null");
                x[i].setAvailability('N');
                x[i].setCusId(0);
                x[i].setDaysBooked(0);
                x[i].setPrice(0);

            }

        }

        if(!found){
           JOptionPane.showMessageDialog(null, "Invalid roomId", "ERROR", JOptionPane.ERROR_MESSAGE);
        
        }
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}//end class
