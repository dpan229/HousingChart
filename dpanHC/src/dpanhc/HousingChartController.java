/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpanhc;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author csstudent
 */
public class HousingChartController implements Initializable {
    
    @FXML
    private StackedBarChart stackedBarChart;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String s = "https://data.cityofchicago.org/resource/s6ha-ppgi.json?$select=zip_code,units,property_type";
        URL myUrl = null;
        try {
            myUrl = new URL(s);
        } catch (Exception e) {
            System.out.println("Problem finding URL " + s);
            System.exit(-1);
        }
        Scanner sc = null;
        try {
            sc = new Scanner(myUrl.openStream());
        } catch (Exception e) {
            System.out.println("Problem connecting to " + s);
        }
        String dataStr = new String();
        while (sc.hasNext()) {
            dataStr += sc.nextLine() + "\n";
        }
        sc.close();
        Gson gson = new Gson();
        Property[] properties = gson.fromJson(dataStr, Property[].class);
        
        for (int i = 0; i < properties.length; i++) {
            if (properties[i] != null && properties[i].anyNull()) {
                properties[i] = null;
            }
        }
        
        ArrayList<String> propertyTypes = new ArrayList<String>();
        
        Map<String, Map<String, Integer>> zipMap = new TreeMap();
        for (Property p : properties) {
            if (p != null) {
                if (!(zipMap.containsKey(p.getZip()))) {
                    zipMap.put(p.getZip(), new TreeMap());
                }
                if (!(zipMap.get(p.getZip())).containsKey(p.getType())) {
                    zipMap.get(p.getZip()).put(p.getType(), p.getUnits());
                    if (!(propertyTypes.contains(p.getType()))) {
                        propertyTypes.add(p.getType());
                    }
                } else {
                    zipMap.get(p.getZip()).put(p.getType(), zipMap.get(p.getZip()).get(p.getType())+p.getUnits());
                }
            }
        }
        
        ArrayList<XYChart.Series<String, Integer>> series = new ArrayList<>();
        for (String pType : propertyTypes) {
            XYChart.Series<String, Integer> serie = new XYChart.Series();
            serie.setName(pType);
            for (String zip : zipMap.keySet()) {
                if (zipMap.get(zip).containsKey(pType)) {
                    serie.getData().add(new XYChart.Data(zip, zipMap.get(zip).get(pType)));
                }
            }
            series.add(serie);
        }
        stackedBarChart.getData().addAll(series);
    }
}
