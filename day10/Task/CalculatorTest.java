package com.example.springbootfirst.controllers;

public class Calculator {


    public int addition(int a, int b) {
        int res = a + b;
        return res;
    }

    public int subtraction(int a, int b) {
        int res = a - b;
        return res;
    }

    public int multiplication(int a, int b) {
        int res = a * b;
        return res;
    }

    public int division(int a, int b) {
        if (b == 0) return 0;
        int res = a / b;
        return res;
    }
}