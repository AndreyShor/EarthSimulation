package EarthLIb;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class  Earth {
    private ArrayList<ArrayList<Double>> arrayOfEarth ;
    private Map<Double, Map<Double, Double>> mapOfEarth;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public void readDataArray(String fileName)  throws FileNotFoundException {
        this.arrayOfEarth = new ArrayList<>();

        Scanner input = new Scanner(new File(fileName));

        while (input.hasNextLine()) {
            String firstLine = input.nextLine();
            String[] stringArr = firstLine.split("\t");
            ArrayList<Double> doubleArr = new ArrayList<>();

            for (String s: stringArr){
                double var = Double.parseDouble(s);
                doubleArr.add(var);
            }

            this.arrayOfEarth.add(doubleArr);

        }
        input.close();

    }

    public void readDataMap(String fileName)  throws FileNotFoundException {
        this.mapOfEarth = new TreeMap();

        Scanner input = new Scanner(new File(fileName));

        while (input.hasNextLine()) {
            String firstLine = input.nextLine();
            String[] stringArr = firstLine.split("\t");
            Map<Double, Double> doubleMap = new TreeMap();

            Double longitude = Double.parseDouble(stringArr[0]);


            if(this.mapOfEarth.containsKey(longitude)){
                Double latitude = Double.parseDouble(stringArr[1]);
                Double meterHigh = Double.parseDouble(stringArr[2]);
                doubleMap = this.mapOfEarth.get(longitude);

                doubleMap.put(latitude, meterHigh);
                this.mapOfEarth.put(longitude, doubleMap);
            } else {
                Double latitude = Double.parseDouble(stringArr[1]);
                Double meterHigh = Double.parseDouble(stringArr[2]);

                doubleMap.put(latitude, meterHigh);
                this.mapOfEarth.put(longitude, doubleMap);

            }

        }
        input.close();

    }


    public List<ArrayList<Double>> coordinatesBelow(double altitude){
        ListIterator<ArrayList<Double>> listDouble = this.arrayOfEarth.listIterator();
        List<ArrayList<Double>> coordinatesBelowGiven = new ArrayList();
        while(listDouble.hasNext()){
            ArrayList<Double> checkedSet = listDouble.next();
            Double compareValue = checkedSet.get(2);
            if (altitude > compareValue){
                coordinatesBelowGiven.add(checkedSet);
            }
        }
//        List<ArrayList<Double>> showLIst = new ArrayList();
//        for (int i = 0; i < 5; i++){
//            showLIst.add(coordinatesBelowGiven.get(i));
//        }
        return coordinatesBelowGiven;
    }




    public List<ArrayList<Double>> coordinatesAbove(double altitude){
        ListIterator<ArrayList<Double>> listDouble = this.arrayOfEarth.listIterator();
        List<ArrayList<Double>> coordinatesAboveGiven = new ArrayList();
        while(listDouble.hasNext()){
            ArrayList<Double> checkedSet = listDouble.next();
            Double compareValue = checkedSet.get(2);
            if (altitude < compareValue){
                coordinatesAboveGiven.add(checkedSet);
            }
        }

//        List<ArrayList<Double>> showLIst = new ArrayList();
//        for (int i = 0; i < 5; i++){
//            showLIst.add(coordinatesAbowGiven.get(i));
//        }
        return coordinatesAboveGiven;
    }

    public void percentageAbove(double altitude){
        List<ArrayList<Double>> coordinatesAbove = this.coordinatesAbove(altitude);
        double countAbove = (double) coordinatesAbove.size();
        double countAll = (double) this.arrayOfEarth.size();

        double perAbove = countAbove / countAll * 100;

        System.out.println("Percentage Above: ");
        System.out.println(df2.format(perAbove));
    }

    public void percentageBelow(double altitude){
        List<ArrayList<Double>> coordinatesBelow = this.coordinatesBelow(altitude);
        double countBelow = (double) coordinatesBelow.size();
        double countAll = (double) this.arrayOfEarth.size();

        double perBelow = countBelow / countAll * 100;

        System.out.println("Percentage Below: ");
        System.out.println(df2.format(perBelow));
    }


    public void generateMap(double resolution) {
        this.mapOfEarth = new TreeMap();

        final Double lanRange = 90.0 / resolution;
        final Double lonRange = 360.0 / resolution;
        final Double minDepth = -4000.0;
        final Double maxDepth = 4000.0;

        for(Double j = 0.0 ; j < lonRange + 1; j++){
            Map<Double, Double> lan_depthMap = new TreeMap();
            Double longitude = j;
            for ( Double i = lanRange * -1.0 ; i < lanRange + 1; i++){
                Double latitude = i;
                Double depth = ThreadLocalRandom.current().nextDouble(minDepth, maxDepth + 1);
                lan_depthMap.put(latitude, depth);
            }
            this.mapOfEarth.put(longitude, lan_depthMap);
        }

    }

    public double getAltitude(double longitude, double latitude){
        double depth;
        Map<Double, Double> Data = this.mapOfEarth.get(longitude);
        depth = Data.get(latitude);
        return depth;
    }

    public ArrayList<Double> getData(){
        return this.arrayOfEarth.get(8);
    }

    public void getDataMap() {
        int i = 0;
        for (Map.Entry<Double, Map<Double, Double>> entry : this.mapOfEarth.entrySet()) {
            Double key = entry.getKey();
            Map<Double, Double> value = entry.getValue();
            int j = 0;
            for (Map.Entry<Double, Double> entry2 : value.entrySet()) {
                Double key2 = entry2.getKey();
                Double depth = entry2.getValue();
                System.out.println(key + " -> " + key2 + " + " + depth);
                j++;

                if( j == 4){
                    break;
                }
            }
            i++;

            if( i == 10){
                break;
            }
        }
    }

}
