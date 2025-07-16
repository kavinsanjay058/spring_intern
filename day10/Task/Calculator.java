package com.example.springbootfirst.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    Calculator cal = new Calculator();

    @Test
    public void testAddition(){
        assertEquals(10,cal.addition(5,5));
    }

    @Test
    public void testSubtraction(){
        assertEquals(0,cal.subtraction(5,5));
    }

    @Test
    public void testMultiplication(){
        assertEquals(25,cal.multiplication(5,5));
    }

    @Test
    public void testDivsion(){
        assertEquals(1,cal.division(5,5));
    }
}