
package room_booking.system;

import java.io.*;
import java.util.*;

public class Files {

    private Scanner x;
    String password;

    public void openFile(String directory) {

        try {

            x = new Scanner(new File(directory));

        } catch (Exception e) {

            System.out.println("Error!!");

        }
    }

    public String passwordReadFile() {

        while (x.hasNext()) {

            password = x.next();

        }

        return password;

    }

    public void closeFile() {

        x.close();

    }

}
