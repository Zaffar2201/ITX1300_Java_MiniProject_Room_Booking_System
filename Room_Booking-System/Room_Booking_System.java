package room_booking_system;
import javax.swing.JOptionPane;

public class Room_Booking_System {

	public static void main(String[] args) {
		// boolean variable 
        boolean condition = true;

        // created and assigned values to Room Object
        Room[] roomArray = new Room[10];
        roomArray[0] = new Room("SB1", "SB", "Single-bed", false, 100, 1, 1);
        roomArray[1] = new Room("SB2", "SB", "Single-bed", false, 100, 2, 7);
        roomArray[2] = new Room("DB1", "DB", "Double-bed", true, 150, 0, 0);
        roomArray[3] = new Room("DB2", "DB", "Double-bed", false, 150, 3, 5);
        roomArray[4] = new Room("DB3", "DB", "Double-bed", true, 150, 0, 0);
        roomArray[5] = new Room("SP1", "SP", "Separate-bed", true, 300, 0, 0);
        roomArray[6] = new Room("SP2", "SP", "Separate-bed", true, 300, 0, 0);
        roomArray[7] = new Room("SP3", "SP", "Separate-bed", false, 300, 4, 12);
        roomArray[8] = new Room();
        roomArray[9] = new Room();

        // call below method for password checking
        userAuthentication();
        // while loop to execute as long as condition evaluates to true
        while (condition) {

            //  calls features method to return what user desires
            switch (features()) {

                case 1:
                    RoomHandler.displayAllRooms(roomArray);
                    break;

                case 2:
                    RoomHandler.displayRoomsAvailable(roomArray);
                    break;

                case 3:

                    RoomHandler.displayRentedRooms(roomArray);
                    break;

                case 4:
                    RoomHandler.addRoom(roomArray, RoomHandler.indexAvailable(roomArray));
                    break;
                case 5:

                    RoomHandler.deleteRoom(roomArray);
                    break;
                case 6:
                    RoomHandler.bookRoom(roomArray);
                    break;
                case 7:
                    RoomHandler.search(roomArray);
                    break;
                case 8:
                    RoomHandler.sortArray(roomArray);
                    break;

                case 9:
                    JOptionPane.showMessageDialog(null, "GOOD BYE");
                    condition = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;

            }

        }

    }//end main

    //  method to return features desired by the user
    //  catch exception to prevent program from crashing
    public static int features() {
        int optionChosed = 0;
        String temp = JOptionPane.showInputDialog(null, "Please enter your choice:\n1) Display all rooms\n2) Display rooms available\n3) Display Room on rent\n4) Add a Room\n"
                + "5) Delete a room\n6) Book a room\n7) Search\n8) Sorting\n9) Exit", "              ROOM BOOKING SYSTEM", JOptionPane.INFORMATION_MESSAGE);

        // check if OK button has been pressed
        // or the user entered a number
        if (temp != null) {

            try {

                optionChosed = Integer.parseInt(temp);
            } catch (NumberFormatException e) {

            }
            // if CANCEL button has been pressed
        } else {

            optionChosed = 9;

        }

        return optionChosed;
    }

    public static void userAuthentication() {

        int attempt = 1;
        String password = "Inteli7";
        String typedpassword;

        do {
            // ask user to enter password
            typedpassword = JOptionPane.showInputDialog(null, "Please enter password : ", "                          Attempt " + attempt, JOptionPane.INFORMATION_MESSAGE);

            // if user pressed CANCEL
            if (typedpassword == null) {

                JOptionPane.showMessageDialog(null, "ERROR 4O4");
                System.exit(0);
                //  after 3 wrong password entered  
            } else if (!typedpassword.equals(password) && attempt == 3) {

                JOptionPane.showMessageDialog(null, "ERROR 4O4");
                System.exit(0);
                // condition to enter into the system
            } else if (typedpassword.equals(password)) {

                JOptionPane.showMessageDialog(null, "WELCOME!!!");
                break;  // break to exit the loop
            } else {
                // message for any invalid password entered
                JOptionPane.showMessageDialog(null, "Invalid Password");

            }
            // increments attempt
            attempt++;

        } while (true);

    }

}//end class
