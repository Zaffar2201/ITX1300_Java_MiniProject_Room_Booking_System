package room_booking_system;
import javax.swing.JOptionPane;
public class RoomHandler {
	// display all rooms 
    public static void displayAllRooms(Room[] roomArray) {

        System.out.println("\n\n---------------------------------------------------------------------------------------------");
        System.out.printf("|%10s |%10s |%15s |%13s |%10s |%10s |%10s", "RoomID", "Type", "Description", "Available", "Price", "CusID", "Days_Booked|\n");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------");

        // loop till end of array
        // currentRoom refers to each object's contents
        // for ex : currentRoom will be roomArray[0..9)
        for (Room currentRoom : roomArray) {

            // condition to reject rooms which have not been created yet
            if (!currentRoom.getRoomId().equals("null")) {
                currentRoom.printAll(roomArray);
            }

        }

        System.out.println("---------------------------------------------------------------------------------------------");
    }

    // display only rooms available
    public static void displayRoomsAvailable(Room[] roomArray) {

        System.out.println("\n\n---------------------------------------------");
        System.out.printf("|%7s |%7s |%15s |%10s", "RoomID", "Type", "Description", "Price|\n");
        System.out.println("---------------------------------------------");
        System.out.println("---------------------------------------------");

        for (Room currentRoom : roomArray) {

            //  condition to display only rooms where availabilty is true
            if (currentRoom.getAvailability() == true) {
                currentRoom.printAvailable(roomArray);
            }
        }
        System.out.println("---------------------------------------------");

    }

    // display rooms which have been rented
    public static void displayRentedRooms(Room[] roomArray) {

        System.out.println("\n\n---------------------------------------------");
        System.out.printf("|%7s |%7s |%15s |%10s", "RoomID", "Type", "Description", "CusID|\n");
        System.out.println("---------------------------------------------");
        System.out.println("---------------------------------------------");

        for (Room currentRoom : roomArray) {

            // condition to return rooms where availability is false and null one's are rejected
            if (currentRoom.getAvailability() == false && (!(currentRoom.getRoomId().equals("null")))) {
                currentRoom.printRented(roomArray);

            }

        }
        System.out.println("---------------------------------------------");
    }

    // method to return the first index free in roomArray
    // where a room can be added
    // if roomArray is full, will return 10
    public static int indexAvailable(Room[] roomArray) {

        // declares integer variable index and assigns 10
        int index = 10;

        // loop till end of array
        for (int i = 0; i < roomArray.length; i++) {

            // condition to retrieve the first index available
            // break to execute the loop
            if (roomArray[i].getRoomId().equals("null")) {
                index = i;
                break;
            }

        }

        return index;

    }

    public static void addRoom(Room[] roomArray, int index) {
        // local variables
        String roomtype;
        float roomprice;

        // switch statement for index
        switch (index) {

            case 10:

                // display error message if roomArray is full
                JOptionPane.showMessageDialog(null, "Maximum number of rooms reached", "Overflow Error", JOptionPane.ERROR_MESSAGE);
                break;

            default:

                // ask user to enter room type
                roomtype = (JOptionPane.showInputDialog("Please enter type of room desired"));

                // condition to check if cancel button has been pressed
                if (roomtype != null) {
                    roomtype = roomtype.toUpperCase();
                } else {

                    // if cancel button has been pressed
                    // display error message#
                    // returns back to main
                    JOptionPane.showMessageDialog(null, "Room type cannot be empty! Returning back to main", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Infinite while loop to force user to enter correct room type unless Cancel button is pressed
                while ((!(roomtype.equals("SB") || roomtype.equals("DB") || roomtype.equals("SP")))) {

                    JOptionPane.showMessageDialog(null, "Invalid room type", "ERROR", JOptionPane.ERROR_MESSAGE);

                    roomtype = (JOptionPane.showInputDialog("Please re-enter type of room desired[SB-DB-SP]"));
                    // condition to check if cancel button has been pressed
                    if (roomtype != null) {
                        roomtype = roomtype.toUpperCase();
                    } else {
                        JOptionPane.showMessageDialog(null, "Room type cannot be empty! Returning back to main", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                }

                int lastRoomId = 0;
                String description;

                switch (roomtype) {

                    case "SB":
                        description = "Single-bed";
                        break;

                    case "DB":
                        description = "Double-bed";
                        break;

                    default:
                        description = "Separate-bed";
                        break;

                }

                // loop till end of roomArray
                for (int i = 0; i < roomArray.length; i++) {

                    // condition to read only type desired by user
                    // substring(0,2) return the first two characters of a given string
                    // for substring(0,2) to "SB1" will return "SB"
                    if ((roomArray[i].getRoomId().substring(0, 2)).equals(roomtype)) {

                        // checks the id of  roomtype
                        // return the last id 
                        if (Integer.parseInt(roomArray[i].getRoomId().substring(2, 3)) > lastRoomId) {
                            lastRoomId = Integer.parseInt(roomArray[i].getRoomId().substring(2, 3));

                        }

                    }

                }

                do {
                    try {
                        // ask user to enter price of room ,
                        String temp = (JOptionPane.showInputDialog("Please enter price of the room "));

                        // condition to check if cancel button has been pressed
                        if (temp != null) {
                            roomprice = Float.valueOf(temp);
                        } else {
                            JOptionPane.showMessageDialog(null, "Room price field cannot be empty! Returning back to main", "Error", JOptionPane.ERROR_MESSAGE);
                            return;

                        }

                        // prevents invalid price
                        if (roomprice >= 0) {

                            break;
                        } else {

                            JOptionPane.showMessageDialog(null, "Invalid price!", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                        // catch exception to prevent program from crashing
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid price", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }
                    // loop until a valid room price has been entered  
                } while (true);

                // create and add the room to the array
                roomArray[index].setType(roomtype);
                roomArray[index].setDescription(description);
                roomArray[index].setRoomId(roomtype + (lastRoomId + 1));
                roomArray[index].setAvailability(true);
                roomArray[index].setPrice(roomprice);

                break;

        }

    }

    public static void deleteRoom(Room[] roomArray) {
        // declares found as boolean
        boolean found = false;

        String id = JOptionPane.showInputDialog(null, "Please enter roomId");
        // condition to check if cancel button has been pressed
        if (id != null) {

            id = id.toUpperCase();
            // loop till end of array until roomId has been fouound
            for (Room currentRoom : roomArray) {

                // checks id
                if (currentRoom.getRoomId().equalsIgnoreCase(id)) {

                    // delete the room data
                    currentRoom.setType("null");
                    currentRoom.setRoomId("null");
                    currentRoom.setAvailability(false);
                    currentRoom.setCusId(0);
                    currentRoom.setDaysBooked(0);
                    currentRoom.setPrice(0);
                    found = true;
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "RoomId field cannot be empty! Returning back to main", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // display an error message if roomId does not exist
        if (found == false) {
            JOptionPane.showMessageDialog(null, "Invalid roomId", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    public static void search(Room[] roomArray) {

        String roomToBeSearched = JOptionPane.showInputDialog("Please enter roomId : ");

        boolean found = false;
        // condition to check if cancel button has been pressed
        if (roomToBeSearched != null) {

            // loop till end of array
            for (Room currentRoom : roomArray) {
                // compares Id of room to be searched to all roomId in the array
                if (currentRoom.getRoomId().equalsIgnoreCase(roomToBeSearched)) {
                    System.out.println("\n\n---------------------------------------------------------------------------------------------");
                    System.out.printf("|%10s |%10s |%15s |%13s |%10s |%10s |%10s", "RoomID", "Type", "Description", "Available", "Price", "CusID", "Days_Booked|\n");
                    System.out.println("---------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------");
                    found = true;
                    currentRoom.printAll(roomArray);
                    System.out.println("---------------------------------------------------------------------------------------------");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "RoomId field cannot be empty! Returning back to main", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // display error message if roomId has not been found
        if (!found) {
            JOptionPane.showMessageDialog(null, "Invalid roomId", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void sortArray(Room[] roomArray) {
        // temporary variables for swapping process
        String tempRoomId, temproomType;
        boolean tempAvailability;
        float tempPrice;
        int tempCusId, tempDaysBooked;

        // outer loop check
        for (int i = 0; i < roomArray.length; i++) {

            // inner loop check
            for (int j = i + 1; j < roomArray.length; j++) {
                // checks alphabetically
                if (roomArray[i].getRoomId().compareToIgnoreCase(roomArray[j].getRoomId()) > 0) {

                    // swapping process
                    tempRoomId = roomArray[i].getRoomId();
                    temproomType = roomArray[i].getType();
                    tempAvailability = roomArray[i].getAvailability();
                    tempPrice = roomArray[i].getPrice();
                    tempCusId = roomArray[i].getCusId();
                    tempDaysBooked = roomArray[i].getDays_Booked();

                    roomArray[i].setRoomId(roomArray[j].getRoomId());
                    roomArray[i].setType(roomArray[j].getType());
                    roomArray[i].setAvailability(roomArray[j].getAvailability());
                    roomArray[i].setPrice(roomArray[j].getPrice());
                    roomArray[i].setCusId(roomArray[j].getCusId());
                    roomArray[i].setDaysBooked(roomArray[j].getDays_Booked());

                    roomArray[j].setRoomId(tempRoomId);
                    roomArray[j].setType(temproomType);
                    roomArray[j].setAvailability(tempAvailability);
                    roomArray[j].setPrice(tempPrice);
                    roomArray[j].setCusId(tempCusId);
                    roomArray[j].setDaysBooked(tempDaysBooked);

                }
            }

        }

    }

    public static void bookRoom(Room[] roomArray) {

        String roomToBeSearched = JOptionPane.showInputDialog("Please enter roomId : ");

        boolean roomIdFound = false;
        boolean cusIdFound = false;
        int days = 0;
        int cusid = 0;
        // loop till end of array
        for (Room currentRoom : roomArray) {
            // compares Id of room to be searched to all roomId in the array
            if (currentRoom.getRoomId().equalsIgnoreCase(roomToBeSearched)) {
                roomIdFound = true;

                // checks if room to be booked is available
                if (currentRoom.getAvailability() == true) {

                    try {
                        // prompts user to enter cusid
                        cusid = Integer.parseInt(JOptionPane.showInputDialog("Please enter CusId : "));

                        if (cusid <= 0) {
                            // display error message if cusid is less than 0 and break  loop
                            JOptionPane.showMessageDialog(null, "Invalid CusId", "ERROR", JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                        // checks if cusid already booked and exit inner loop
                        for (Room thisRoom : roomArray) {

                            if (thisRoom.getCusId() == cusid) {

                                JOptionPane.showMessageDialog(null, "CusID " + cusid + " is already in use", "ERROR", JOptionPane.ERROR_MESSAGE);
                                cusIdFound = true;
                                break;

                            }

                        }// end of inner for loop
                        // error message if user entered wrong cusid
                    } catch (NumberFormatException e) {

                        JOptionPane.showMessageDialog(null, "Invalid CusId", "ERROR", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    // exit outer loop if user enters duplicate cusid
                    if (cusIdFound == true) {
                        break;
                    }

                    try {
                        // ask user to enter number of days
                        days = Integer.parseInt(JOptionPane.showInputDialog("Please enter number of days to be booked: "));
                        //  rejects invalid number of days
                        if (days <= 0) {
                            JOptionPane.showMessageDialog(null, "Invalid number of days", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                        // books the room
                        if (cusIdFound == false && cusid > 0 && days > 0) {

                            currentRoom.setAvailability(false);
                            currentRoom.setCusId(cusid);
                            currentRoom.setDaysBooked(days);

                        }

                    } catch (NumberFormatException e) {

                        JOptionPane.showMessageDialog(null, "Invalid number of days", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                    // display error message if room is not available
                } else {

                    JOptionPane.showMessageDialog(null, "Room is already being rented", "ERROR", JOptionPane.ERROR_MESSAGE);

                }

            }

        }// end of for loop

        //  display error message if roomId does not exist
        if (!roomIdFound) {
            JOptionPane.showMessageDialog(null, "Invalid roomId", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }// end of book
}
