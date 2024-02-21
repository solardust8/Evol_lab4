package lab3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;

public class DistanceGetter {
    private Point cityIndexToPoint[];
    public DistanceGetter(String pathToTsp) {
        // узнать dimension
        // создать и заполнить cityIndexToPoint
        int dimension = getDimension(pathToTsp);
        cityIndexToPoint = new Point[dimension];
        fillCityIndexToPoint(pathToTsp);
    }

    public double getDistance(int cityIndex1, int cityIndex2) {
        Point cityPoint1 = cityIndexToPoint[cityIndex1];
        Point cityPoint2 = cityIndexToPoint[cityIndex2];

        return Math.sqrt(Math.pow(cityPoint1.x - cityPoint2.x, 2) + Math.pow(cityPoint1.y - cityPoint2.y, 2));
    }

    private int getDimension(String pathToTsp) {
        int dimension = -1;

        FileInputStream stream = null;
        try {
            stream = new FileInputStream(pathToTsp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.startsWith("DIMENSION")) {
                    dimension = Integer.parseInt(line.substring(line.lastIndexOf(" ")+1));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dimension;
    }

    private void fillCityIndexToPoint(String pathToTsp) {
        FileInputStream stream = null;

        // ! А если я хочу, чтобы програма упала если нет файла
        try {
            stream = new FileInputStream(pathToTsp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));


        try {
            // get_to_coord_section
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.startsWith("NODE_COORD_SECTION")) {
                    break;
                }
            }

            for (String line = reader.readLine(); !line.startsWith("EOF"); line = reader.readLine()) {
                String city_info[] = line.split(" ");
                int i = Integer.parseInt(city_info[0]) - 1;
                int x = Integer.parseInt(city_info[1]);
                int y = Integer.parseInt(city_info[2]);
                cityIndexToPoint[i] = new Point(x, y);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
