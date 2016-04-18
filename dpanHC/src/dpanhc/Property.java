/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpanhc;

/**
 *
 * @author csstudent
 */
public class Property {
    private String property_type;
    private String zip_code;
    private Integer units;
    
    public Property() {
    }
    
    public String getType() {
        return property_type;
    }
    
    public String getZip() {
        return zip_code;
    }
    
    public Integer getUnits() {
        return units;
    }
    
    @Override
    public String toString() {
        return units + " " + property_type + " units at " + zip_code;
    }
}
