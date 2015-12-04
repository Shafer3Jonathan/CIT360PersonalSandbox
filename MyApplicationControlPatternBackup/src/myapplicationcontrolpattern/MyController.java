/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myapplicationcontrolpattern;

import java.util.Scanner;

/**
 *
 * @author Jonathan
 */
public class MyController {
      public void MyController(){
          MyApplicationControlPattern deligate = new MyApplicationControlPattern();
          Integer num1= -5;
          Integer num2= 5;
          String cmd;
          Scanner in = new Scanner(System.in);
          System.out.println("enter a number: ");
          num1= Integer.parseInt(in.nextLine());
          System.out.println("Enter a Command(+, *, -, /): ");
          cmd = in.nextLine();
          System.out.println("enter another number: ");
          num2 = Integer.parseInt(in.nextLine());
          System.out.println("Enter another number: ");
          deligate.cmdHandler(cmd,num1,num2);
    }
}
