package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class MoneyParts {

    private static double[] DENOMINACIONES = {0.05, 0.1, 0.2, 0.5, 1, 2, 5, 10, 20, 50, 100, 200};

    ArrayList<ArrayList<Double>> build(double monto){
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> resp = new ArrayList<>();

        int iDen=DENOMINACIONES.length-1;
        while (true){
            while (Math.round(sum(temp)*1000.0)/1000.0 < monto){
                temp.add(iDen);
            }
            if (Math.round(sum(temp)*1000.0)/1000.0 == monto){
                resp.add(new ArrayList<>(temp));
            }

            temp.set(temp.size()-1,temp.get(temp.size()-1)-1);

            while (temp.get(temp.size()-1) < 0){
                temp.remove(temp.size()-1);
                if(temp.isEmpty()) {
                    return convert(resp);
                }
                temp.set(temp.size()-1,temp.get(temp.size()-1)-1);
            }
            iDen = temp.get(temp.size()-1);
        }
    }

    private double sum(ArrayList<Integer> temp) {
        double sum = 0;
        for (int item:temp) {
            sum+= DENOMINACIONES[item];
        }
        return sum;
    }

    private ArrayList<ArrayList<Double>> convert(ArrayList<ArrayList<Integer>> resp) {
        ArrayList<ArrayList<Double>> response = new ArrayList<>();

        for (ArrayList<Integer> item:resp) {
            ArrayList<Double> temp = new ArrayList<>();
            for (int val:item) {
                temp.add(DENOMINACIONES[val]);
            }
            response.add(new ArrayList<>(temp));
        }
        Collections.reverse(response);
        return response;
    }

}
