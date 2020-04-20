package part2;

import java.util.Objects;
import java.util.Scanner;

public class CellPhone {
    private long serialNum; //no two phones have the same serial number
    private String brand;
    private int year;
    private double price;

    //constructors
    public CellPhone(long s,String b,int y,double p){
        serialNum=s;
        brand=b;
        year=y;
        price=p;
    }
    //copy constructor
    public CellPhone(CellPhone copy,long s){
        serialNum=s;
        brand=copy.getBrand();
        year=copy.getYear();
        price=copy.getPrice();

    }
    //clone method
    public CellPhone clone(){
        long num;
        Scanner input= new Scanner(System.in);
        System.out.print("Please input the serial Number of the new cell: ");
        num= input.nextLong();

        CellPhone copy= new CellPhone(num,this.getBrand(),this.getYear(),this.getPrice());
        return copy;
    }
    //accessors & mutators
    public double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setYear(int year) {
        this.year = year;
    }
    //to string method

    public String toString() { //todo modify to see presentation of cell
        return "["+ serialNum +
                ": " + brand +" "+price +"$ "+year+"]";
    }

    //equals methods

    //todo modify this to double check if cellphones match
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPhone cellPhone = (CellPhone) o;
        return getYear() == cellPhone.getYear() &&
                Double.compare(cellPhone.getPrice(), getPrice()) == 0 &&
                Objects.equals(getBrand(), cellPhone.getBrand());
    }


}
