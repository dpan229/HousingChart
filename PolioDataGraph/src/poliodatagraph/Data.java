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
public class Data {
    private LabelHolder[] dimension;
    private DataPoint[] fact;
    
    public Data() {
    }
    
    public LabelHolder[] getLabels() {
        return dimension;
    }
    
    public DataPoint[] getData() {
        return fact;
    }
}