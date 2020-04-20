package part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CellListUtilization {
    public static void main(String[] args){
        CellList list1=new CellList();
        CellList list2= new CellList();
        ArrayList cells= new ArrayList();

        try {
            cells = readItems();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //todo privacy leaks
        list2=readList(cells);


        CellPhone cell1=new CellPhone(3890909, "Samsung",2015,857.28 );
        CellPhone cell2=new CellPhone(2787985, "Acer",2013,572.20 );
        CellPhone cell3=new CellPhone(4900088, "LG"   ,2017,232.99 );
        CellPhone cell4=new CellPhone(1989000, "Nokia"  ,2006,237.24 );
        CellPhone cell5=new CellPhone(89076, "Sharp" ,2009,564.22 );
        CellPhone cell6=new CellPhone(2887685, "Motorola",2012,569.28 );
        CellPhone cell7=new CellPhone(7559090, "Pansonic",2005,290.90 );

        list1.addToStart(cell1);
        list1.addToStart(cell2);
        list1.addToStart(cell3);
        list1.addToStart(cell4);
        list1.addToStart(cell5);
        list1.addToStart(cell6);
        list1.addToStart(cell7);

        list2.showContents();//todo rearrange the order


    }

    public static ArrayList readItems() throws FileNotFoundException {
        ArrayList cells= new ArrayList();
        File text=new File("src/part2/Cell_info.txt");
        Scanner reader=new Scanner(text);

        while(reader.hasNext()){
            String data= reader.nextLine();
            cells.add(data);
        }

        return cells;
    }
    public static CellList readList(ArrayList cells){
        CellList list=new CellList();
        for(Object phone: cells){
            String s=(String) phone;
            long serial=0;
            String brand="";
            int year=0;
            double price=0;

            for(String info:s.split("\\s")){
                if(info.matches("\\b[a-zA-Z]\\w+\\b")){
                    brand=info;
                }else if(info.matches("\\b\\d{7}\\b")){
                    serial=Long.parseLong(info);
                }else if(info.matches("\\d{3}[.]\\d{2}")){

                    price=Double.parseDouble(info);
                }else if(info.matches("\\b\\d{4}\\b")){
                    year=Integer.parseInt(info);
                }

            }
            CellPhone temp =new CellPhone(serial,brand,year,price);
            list.addToStart(temp); //todo change order of the add
        }
        return list;
    }
}
