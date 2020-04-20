import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import EarthLIb.Earth;

public class App extends Earth {

    private boolean initial = false;
    public App(){}

    public void init() throws IOException {

        if (this.initial == true) {
            return;
        }

        Scanner in = new Scanner(System.in);
//        this.readDataMap("C:\\Users\\andre\\Desktop\\Projects\\University\\C152_OOP_Java\\assigment\\earth.xyz");
        System.out.println("Start of program");
        System.out.println("PLease choice option of work");
        System.out.println("1) Per cent above given depth ");
        System.out.println("2) Depth in a given coordinate");
        System.out.println("Enter: ");
        int option = in.nextInt();

        if(option == 1){
            this.readDataArray("C:\\Users\\andre\\Desktop\\Projects\\University\\C152_OOP_Java\\Earth_Simulation_Project\\earth.xyz");
            System.out.println("Program will print proportion of coordinates above given point");
            System.out.println("Please Enter altitude: ");
            Double altitude = in.nextDouble();
            this.percentageAbove(altitude);
        }

        if(option == 2){
            this.readDataMap("C:\\Users\\andre\\Desktop\\Projects\\University\\C152_OOP_Java\\Earth_Simulation_Project\\earth.xyz");
            boolean exit = false;
            do {
                System.out.println("Program will print depth in a given point on Earth");
                System.out.println("Enter longitude: ");
                Double lon = in.nextDouble();
                System.out.println("Enter latitude: ");
                Double lat = in.nextDouble();
                try {
                    Double depth = this.getAltitude(lon, lat);
                    System.out.println("Depth at this point is: " + depth);
                } catch (Exception e) {
                    System.out.println("Program haven't found this coordinate");
                }
                System.out.println("Enter 1 to exit, enter 0 to repeat ");
                System.out.println("Enter: ");
                option = in.nextInt();

                if (option == 1){
                    exit = true;
                }

            } while (exit == false);
        }

//        this.generateMap(0.5);
//        this.getDataMap();
//        System.out.println("Depth Given");
//        Double data = this.getAltitude(0.0,180.0);
//        System.out.println(data);
//        this.initial = true;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                try {
                    new App().init();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
