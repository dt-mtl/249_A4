package part1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Subdictionary {
    public static void main(String[] args)throws FileNotFoundException {
        Scanner input= new Scanner(System.in);
        String fileName;
        String information;
        int entries;
        ArrayList dictionaryInfo = new ArrayList();

        System.out.println("Welcome to the Sub Dictionary program");
        System.out.println("\nPlease make sure you have input the desired file into the src/part1 folder, once this is done please enter the name of the file\n"+
                "(capitalization doesnt matter, nor do you need to put \".txt\"): ");
        fileName=input.nextLine();

        information=readFile(fileName);


        dictionaryInfo=new ArrayList(processinfo(information));


        //order list in alphabetical order
        dictionaryInfo =order(dictionaryInfo);

        //get rid of duplicate words
        dictionaryInfo=deleteDuplicate(dictionaryInfo);
        entries=dictionaryInfo.size();
        //organize list into proper dictonary format
        dictionaryInfo=dictionary(dictionaryInfo);

        //write the subdictionary file
        writeList(dictionaryInfo,entries);



        //todo delete only for debugging
        /*
        for(Object word: dictionaryInfo){
            System.out.println(word);
        } */



    }
    /*
    this program reads the file and transforms it into one single String
     */
    public static String readFile(String name) throws FileNotFoundException{
    String text="";
    FileInputStream foo = new FileInputStream("src/part1/"+name+".txt");
    Scanner sc = new Scanner(foo.getChannel());

        while(sc.hasNext()){
            text+=sc.next()+" ";
        }

        return text;
}
/*
This program will create the subdictionary arraylist
 */
    public static ArrayList processinfo(String info){
        ArrayList defs= new ArrayList();
        Scanner reader = new Scanner(info);

        int oneA=0;
        int oneI=0;


        while (reader.hasNext()) { //iterate through the entire string and extract info
            String sentence = reader.nextLine();
            for (String word : sentence.split(" ")) {

                word = word.toUpperCase();

                if (word.contains("MC")&&word.charAt(3)==','){ //encoding gave me a god awful time F this!!!

                    defs.add("MC²");
                }else  if (word.matches("\\w+\\\u0092(S|M)")){ //taking care of any words with apostrophe
                    int ending=word.length();
                    word=word.substring(0,ending-2);
                    defs.add(word);
                }else if(word.matches("[A-Z]+([,.!?:;])")){
                    int ending=word.length();
                    word=word.substring(0,ending-1);
                    defs.add(word);

                }else if(word.matches("\\b\\w+\\-\\w+\\b")){

                    defs.add(word);
                } else if(word.matches("\\b[^\\d\\W]+\\b")) { //here we make sure that we have a word
                        if (word.matches("\\bA\\b")) { //only takes an A and will only add it once
                            if(oneA==0)
                                defs.add(word);
                            oneA++;
                        } else if (word.matches("\\bI\\b")) { //only takes an I and will only add it once
                            if (oneI == 0)
                                defs.add(word);
                            oneI++;
                        }else {
                            defs.add(word);
                        }
                }

            }
        }// end of While loop

        return defs;
    }
    /*
    This program will organize the array in alphabetical order
     */
    public static ArrayList order(ArrayList dict){
        int count=dict.size();
        String temp;
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                String comp=(String)dict.get(i);
                if (comp.compareTo((String)dict.get(j))>0)
                {
                    temp = comp;
                    dict.set(i,dict.get(j));
                    dict.set(j,temp);
                }
            }
        }
        return dict;
    }

    /*
    this program will remove redundancies
     */
    public static ArrayList deleteDuplicate(ArrayList info){
       String next;
       ArrayList updatedList=new ArrayList();
        for( Object s: info ){
            next=(String)s;
            if(!updatedList.contains(next))
                updatedList.add(next);
        }
        return updatedList;
    }
    /*
    this program adds the letter and "= =" into the array
     */
    public static ArrayList dictionary(ArrayList dict){
        String cur;
        String next;
        String lastword=(String)dict.get(dict.size()-1);


        ArrayList newlist=new ArrayList();
        for(int i=0;i<dict.size()-1;i++){
            if(i==0)
                newlist.add("\nA\n==");
            cur=(String)dict.get(i);
            next=(String) dict.get(i+1);

            if(cur.charAt(0)!=next.charAt(0)){
                   newlist.add(cur);
                   newlist.add("\n"+next.charAt(0)+"\n==");

                //   newlist.add(i+1,next);
            }else {
                newlist.add(dict.get(i));

            }


       }
        newlist.add(lastword);
        newlist.remove((String)"E");

        return newlist;
    }
    /*
    this program greates and writes the .txt file
     */
    public static void writeList(ArrayList dict,int entries)throws FileNotFoundException{
        PrintWriter pen = new PrintWriter(new FileOutputStream("SubDictionary.txt"));
       String s;
       pen.write("The document produced this sub-dictionary, which includes "+(entries-1)+" Entries\n");
        for(Object words :dict){
           s=(String) words;
            pen.println(s);
        }
        pen.close();

    }
}
