/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 * 
 * @author Jonathan
 */
public class PGenIntParser {
    private static String failval = null;
    @Test
    public void myJUnitTester(){
        assertThat(failval, containsString(null));
    }
    public Integer PGenIntParser(String PassID){
        int PasswordID=0;
        try {
            PasswordID = Integer.parseInt(PassID);
        } catch (NumberFormatException e) { 
            failval = e.getMessage();
            fail("Error Parsing PasswordID");
            //System.out.println("Error Parsing PasswordID");
        }
        return PasswordID;
    }
}
