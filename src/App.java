import java.awt.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.SwingUtilities;
import assigment.Earth;

public class App extends Earth {

    private boolean initial = false;
    public App(){}

    public void init() throws FileNotFoundException {

        if (this.initial == true) {
            return;
        }

//        this.readDataMap("C:\\Users\\andre\\Desktop\\Projects\\University\\C152_OOP_Java\\assigment\\earth.xyz");
        this.generateMap(0.5);
        this.getDataMap();
        System.out.println("Depth Given");
        Double data = this.getAltitude(0.0,180.0);
        System.out.println(data);
        this.initial = true;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                try {
                    new App().init();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
