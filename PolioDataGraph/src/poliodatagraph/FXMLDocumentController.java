/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poliodatagraph;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author csstudent
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private BarChart barChart;
    
    @FXML
    private Slider maxSlider;
    
    @FXML
    private Slider minSlider;
    
    private Data data;
    
    @FXML
    private void handleChangeFilter(MouseEvent event) {
        XYChart.Series<String, Integer> vaccinations = new XYChart.Series();
        vaccinations.setName("Number of Vaccinations");
        for (DataPoint dpt : data.getData()) {
            if ((dpt != null) && (dpt.getValue() >= minSlider.getValue() && dpt.getValue() <= maxSlider.getValue())) {
                vaccinations.getData().add(new XYChart.Data(dpt.getInfo().getCountry(), dpt.getValue()));
            }
        }
        barChart.getData().remove(0, barChart.getData().size());
        barChart.getData().add(vaccinations);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String s = "http://apps.who.int/gho/athena/data/GHO/WHS4_544.json?profile=simple&filter=YEAR:1980";
        URL myUrl = null;
        try {
            myUrl = new URL(s);
        } catch (Exception e) {
            System.out.println("Invalid url: " + s);
            return;
        }
        Scanner sc = null;
        try {
            sc = new Scanner(myUrl.openStream());
        } catch (Exception e) {
            System.out.println("Unable to connect to " + s);
            return;
        }
        String dataStr = new String();
        while (sc.hasNext()) {
            dataStr += sc.nextLine() + "\n";
        }
        Gson gson = new Gson();
        data = gson.fromJson(dataStr, Data.class);
        
        data.removeInvalid();
        
        XYChart.Series<String, Integer> vaccinations = new XYChart.Series();
        vaccinations.setName("Number of Vaccinations");
        for (DataPoint dpt : data.getData()) {
            if (!(dpt == null)) {
                vaccinations.getData().add(new XYChart.Data(dpt.getInfo().getCountry(), dpt.getValue()));
            }
        }
        barChart.getData().add(vaccinations);
    }    
}
