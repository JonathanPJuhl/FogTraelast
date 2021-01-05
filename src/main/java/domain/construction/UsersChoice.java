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
    private final String customerPhone;
    private final String customerEmail;


    public UsersChoice(int width, int length, String roofChoice, int shedOrNo, int claddingChoice, String customerPhone, String customerEmail) {
        this.width = width;
        this.length = length;
        this.roofChoice = roofChoice;
        this.shedOrNo = shedOrNo;
        this.claddingChoice = claddingChoice;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }

    public UsersChoice(int width, int length, String roofChoice, int shedOrNo, int claddingChoice, String customerPhone, String customerEmail, Material roofCladding,
                       double degree, int shedLength, int shedWidth, Material shedAndCarportCladding ) {
        this.width = width;
        this.length = length;
        this.roofChoice = roofChoice;
        this.shedOrNo = shedOrNo;
        this.claddingChoice = claddingChoice;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.roofCladding = roofCladding;
        this.degree = degree;
        this.shedLength = shedLength;
        this.shedwidth = shedWidth;
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

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    @Override
    public String toString() {
        return "UsersChoice{" +
                "width=" + width +
                ", length=" + length +
                ", roofChoice='" + roofChoice + '\'' +
                ", shedOrNo=" + shedOrNo +
                ", claddingChoice=" + claddingChoice +
                ", roofCladding=" + roofCladding +
                ", degree=" + degree +
                ", shedLength=" + shedLength +
                ", shedwidth=" + shedwidth +
                ", shedAndCarportCladding=" + shedAndCarportCladding +
                '}';
    }
}
