/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poliodatagraph;

/**
 *
 * @author csstudent
 */
class DataPoint {
    private DataInfo dim;
    private String Value;
    
    public DataPoint() {
    }
    
    public DataInfo getInfo() {
        return dim;
    }
    
    public String getValue() {
        return Value;
    }
}