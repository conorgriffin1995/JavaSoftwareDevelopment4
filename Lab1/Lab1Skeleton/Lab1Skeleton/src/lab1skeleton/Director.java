/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1skeleton;

import java.text.NumberFormat;

/**
 *
 * @author pmage_000
 */
public class Director extends Manager{
    private double budget;
    
    public Director(int id, String n, String ssn, double sal,String dep,double b)
    {
        super(id,n,ssn,sal,dep);
        budget=b;

    }
    
    public double getBudget()
    {
        return budget;
    }
    public void print()
    {
        super.print();
        System.out.println("Budget:          " + NumberFormat.getCurrencyInstance().format(getBudget()));
    }
}
