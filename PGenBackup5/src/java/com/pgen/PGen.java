/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;
import java.util.Date;
/**
 *
 * @author Jonathan
 */
public class PGen {
   //Declare Variables
   private int id;
   private String pword;
   private Date tstamp;
   
   //Default constructor do nothing
   public PGen() {}
   //Creates an PGen Object that sets the attributes. 
   public PGen(String passwd, Date dTime) {
      this.pword = passwd;
      this.tstamp = dTime;
   }
   //This Section Contains your getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public Date getTstamp() {
        return tstamp;
    }

    public void setTstamp(Date tstamp) {
        this.tstamp = tstamp;
    }
   
}