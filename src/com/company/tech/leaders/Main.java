package com.company.tech.leaders;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter x and term's count:");
        int x = scanner.nextInt();
        int count = scanner.nextInt();
        double result = CosCalculator.calculateWithTermsCount(x, count);
        System.out.println("cos(" + x + ") = " + result);

    }
}
