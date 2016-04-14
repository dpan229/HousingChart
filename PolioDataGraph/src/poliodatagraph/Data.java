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

    public void removeInvalid() {
        for (int i = 0; i < fact.length; i++) {
            if (!(fact[i] == null) && (fact[i].getInfo() == null || fact[i].getInfo().getCountry() == null)) {
                fact[i] = null;
            }
        }
        for (int i = 0; i < fact.length; i++) {
            if (!(fact[i] == null)) {
                for (int j = i+1; j < fact.length; j++) {
                    if (!(fact[j] == null)) {
                        if (fact[i].getInfo().getCountry().equals(fact[j].getInfo().getCountry())) {
                            fact[j] = null;
                        }
                    }
                }
            }
        }
    }
}