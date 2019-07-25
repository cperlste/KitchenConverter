package com.example.kitchenconverter;

class Measurement {
    private double cup, tbsp, tsp, oz;

    public Measurement() {
        cup = 0.0;
        tbsp = 0.0;
        tsp = 0.0;
        oz = 0.0;
    }

    public double ozToCup(double oz) {
        return oz / 8;
    }

    public double cupToOz(double cup) {
        return cup * 8;
    }

    public double cupToTbsp(double cup) {
        return cup * 16;
    }

    public double tbspToCup(double tbsp) {
        return tbsp / 16;
    }

    public double cupToTsp(double cup) {
        return cup * 48;
    }

    public double tspToCup(double tsp) {
        return tsp / 48;
    }

    public double ozToTbsp(double oz) {
        return oz * 2;
    }

    public double tbspToOz(double tbsp) {
        return tbsp / 2;
    }

    public double ozToTsp(double oz) {
        return oz * 6;
    }

    public double tspToOz(double tsp) {
        return tsp / 6;
    }

    public double tspToTbsp(double tsp) {
        return tsp / 3;
    }

    public double tbspToTsp(double tbsp) {
        return tbsp * 3;
    }
}
