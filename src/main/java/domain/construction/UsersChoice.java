package domain.construction;

import domain.material.Material;

public class UsersChoice {
    private final int width;
    private final int length;
    private final String roofChoice;
    private final int shedOrNo;
    private final int claddingChoice;
    private Material roofCladding;
    private double degree;
    private int shedLength;
    private int shedwidth;
    private Material shedAndCarportCladding;

    //TODO LAV METODER ISTEDET FOR CONSTRUCTER HVIS TID
    public UsersChoice(int width, int length, String roofChoice, int shedOrNo, int claddingChoice) {
        this.width = width;
        this.length = length;
        this.roofChoice = roofChoice;
        this.shedOrNo = shedOrNo;
        this.claddingChoice = claddingChoice;
      /*  this.shedOrNo = convert(shedOrNo);
        this.claddingChoice = convert(claddingChoice);*/
    }

    public UsersChoice(int width, int length, String roofChoice, int shedOrNo, int claddingChoice, Material roofCladding,
                       double degree, int shedLength, int shedwidth, Material shedAndCarportCladding) {
        this.width = width;
        this.length = length;
        this.roofChoice = roofChoice;
        this.shedOrNo = shedOrNo;
        this.claddingChoice = claddingChoice;
        this.roofCladding = roofCladding;
        this.degree = degree;
        this.shedLength = shedLength;
        this.shedwidth = shedwidth;
        this.shedAndCarportCladding = shedAndCarportCladding;
    }

    public boolean roofChoiceConverter(String roofChoice){
        if (roofChoice.equals("Flat")){
            return true;
        }
        else{
            return false;
        }
    }

    public Material getShedAndCarportCladding() {
        return shedAndCarportCladding;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public String getRoofChoice() {
        return roofChoice;
    }

    public int shedOrNo() {
        return shedOrNo;
    }

    public int isCladdingChoice() {
        return claddingChoice;
    }

    public int getShedOrNo() {
        return shedOrNo;
    }

    public int getCladdingChoice() {
        return claddingChoice;
    }

    public Material getRoofCladding() {
        return roofCladding;
    }

    public double getDegree() {
        return degree;
    }

    public int getShedLength() {
        return shedLength;
    }

    public int getShedwidth() {
        return shedwidth;
    }
}
