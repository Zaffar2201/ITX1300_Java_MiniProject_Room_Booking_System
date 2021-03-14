package room_booking.system;

import java.io.*;
import java.util.*;

public class Room {

    private String roomId;
    private String type;
    private char availability;
    private float price;
    private int cusId;
    private int days_Booked;

    public Room() {

        roomId = "null";
        type = "null";
        availability = 'N';
        price = 0;
        cusId = 0;
        days_Booked = 0;

    }

    public Room(String r, String t, char a, float p, int c, int d) {
        roomId = r;
        type = t;
        availability = a;
        price = p;
        cusId = c;
        days_Booked = d;

    }

    public void setRoomId(String id) {
        this.roomId = id;

    }

    public void setType(String type) {
        this.type = type;

    }

    public void setAvailability(char available) {
        this.availability = available;

    }

    public void setPrice(float price) {
        this.price = price;

    }

    public void setCusId(int cusId) {
        this.cusId = cusId;

    }

    public void setDaysBooked(int days) {
        this.days_Booked = days;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getType() {
        return type;
    }

    public char getAvailability() {
        return availability;
    }

    public float getPrice() {
        return price;
    }

    public int getCusId() {
        return cusId;
    }

    public int getDays_Booked() {
        return days_Booked;
    }

    

}//end Room
