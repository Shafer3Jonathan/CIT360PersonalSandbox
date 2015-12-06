/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

import java.util.HashMap;


/**
 * This is basically a simple password generator it generates one password this can be further improved at a later time
 * @author Jonathan
 */
public class PGenerator implements Runnable{
    //declare session variables
    private String aPass; 
    //Threading implementation
    @Override
    public void run() {
        aPass = "fhqgwads";
    }
    //getter for the password
    public String getaPass() {
        return aPass;
    }
}
