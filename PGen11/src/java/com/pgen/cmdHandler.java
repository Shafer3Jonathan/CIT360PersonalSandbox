/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Interface to be used by ACP 
 * @author Jonathan
 */
public interface cmdHandler {

    String execute(String strIn, String cmdIn, Date dteIn, Integer intIn);
}
