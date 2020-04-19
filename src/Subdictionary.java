
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;//todo get rid of this?
import java.util.ArrayList;
import java.util.Scanner;


public class Subdictionary {
    public static void main(String[] args) throws IOException {
        Scanner input= new Scanner(System.in);
        String fileName;
        String information;
        ArrayList dictionaryInfo = new ArrayList();

        System.out.println("Welcome to the Sub Dictionary program");
        System.out.println("\nPlease make sure you have input the desired file into the src folder, once this is done please enter the name of the file\n"+
                "(capitalization doesnt matter, nor do you need to put \".txt\"): ");
        //fileName=input.nextLine();
        fileName="personofthecentury"; //todo delete once completed the assingment and undo the above comment
        information=readFile(fileName);

        //System.out.println("This is what was read\n"+information); todo debugging line remove once finished

        dictionaryInfo=new ArrayList(processinfo(information));
        System.out.println("the array size at reading: "+dictionaryInfo.size());
        //todo sort array
        dictionaryInfo =order(dictionaryInfo);
        System.out.println("the array size at order: "+dictionaryInfo.size());
        //todo get rid of extras
        dictionaryInfo=deleteDuplicate(dictionaryInfo);
        System.out.println("the array size after deleting duplicates: "+dictionaryInfo.size());
        //todo add spaces
        dictionaryInfo=dictionary(dictionaryInfo);
        System.out.println("the array size: "+dictionaryInfo.size());

        for(Object word: dictionaryInfo){
            System.out.println(word);
        }


    }
    /*
    this program reads the file and transforms it into one single String
     */
    public static String readFile(String name) throws IOException{
    String text="";
    try{
        FileInputStream doc = new FileInputStream("src/"+name+".txt");
        int info;
        while((info=doc.read())!=-1){
            text=text+Character.toString(info);

        }

    }catch (FileNotFoundException e){
        System.err.println("cant find the file");
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

    //todo put while loop that goes through entire string
        while (reader.hasNext()) { //todo remove comment
            String sentence = reader.nextLine();
            for (String word : sentence.split(" ")) {
                word = word.toUpperCase();


                if (word.matches(".*MC².*")) { //if the word contains mc^2
                    defs.add(word);
                }else  if (word.matches("\\w+\\\u0092(S|M)")) { //taking care of any words with apostrophe
                    int ending=word.length();
                    word=word.substring(0,ending-2);
                    defs.add(word);
                }/*  //todo this will need to be submitted since ide doesnt recognize apostrophe :/
                if (word.matches("\\w+\\’(m|s)")) { //taking care of any words with apostrophe
                    int ending=word.length();
                    word=word.substring(0,ending-2);
                    defs.add(word);
                }*/else if(word.matches("\\b[^\\d\\W]+\\b")) { //here we make sure that we have a word
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
        }//todo while loop ends

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
       // int count=0;
        ArrayList newlist=new ArrayList();
        for(int i=0;i<dict.size()-1;i++){
            if(i==0)
                newlist.add("\nA\n==");
            cur=(String)dict.get(i);
            next=(String) dict.get(i+1);

            if(cur.charAt(0)!=next.charAt(0)){

                   newlist.add("\n"+next.charAt(0)+"\n==");

                //   newlist.add(i+1,next);
            }else {
                newlist.add(dict.get(i));

            }


       }
        newlist.add(lastword);

        return newlist;
    }
}
