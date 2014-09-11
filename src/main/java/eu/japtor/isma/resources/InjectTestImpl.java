/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.resources;

/**
 *
 * @author Honza
 */
public class InjectTestImpl implements InjectTest {
    private final String text = "ABCD-EFGH  ";
    private int i = 1;
    private double random;

    public InjectTestImpl(double aRandom) {
        random = aRandom;
    }

    @Override
    public String getInjectedText() {
        i++;
        return text + Integer.toString(i);
    }
    
}
