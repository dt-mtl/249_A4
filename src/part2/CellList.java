package part2;

import java.util.NoSuchElementException;

public class CellList {
   //private attribute
    private CellNode head;
    private static int size;

    //default constructor
    public CellList(){
    }
    //copy constructor
    public CellList(CellList old){
        head=old.getHead();
        size=old.getSize();
    }

    //todo f-requiremet "end" of list
    public void addToStart(CellPhone phone){
        if(head==null) {
            head = new CellNode(phone);
        }
        CellNode temp=new CellNode(phone);
        CellNode current= head;
        if(current != null) {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(temp);
        }
       incrementSize();
    }
    //g insert at method
    public void insertAtIndex(CellPhone phone, int index){
        CellNode temp= new CellNode(phone);
        CellNode current= head;
        //exit program if index is invalid
        if(index<0||index>this.getSize()-1){
            throw new NoSuchElementException();
        }
        if(current != null){
            for(int i = 0; i<index&&current.getNext() != null; i++){
                current=current.getNext();
            }
        }
        temp.setNext(current.getNext());

        current.setNext(temp);
        incrementSize();
    }
    //h delete from index method
    public void deleteFromIndex(int index){

        //exit program id index is invalid
        if(index<0||index>this.getSize()-1){
            throw new NoSuchElementException();
        }
        CellNode current=head;
        if(head !=null){
            for(int i=0;i<index;i++){
                if(current.getNext()==null)
                    return;
                current=current.getNext();
            }
            current.setNext(current.getNext().getNext());
            decrementSize();
        }

    }
    //i-j  todo delete from the head (start of the list) && replace at Index

    //k find todo finish this
    public int find(long phone){
        return 0;
    }

    //show contents

    public void showContents() {
       String output="";
       int count=0;
       System.out.println("The current size of the list is "+this.getSize()+". Here are the contents of the list");
       System.out.println("========================================================================");
       if(head != null){
           CellNode current= head.getNext();
           while(current!=null){
               if (count%3==0)
                   output+="\n";
               output += current.getData().toString()+" ---> ";

               current=current.getNext();
               count++;
           }
           output+=" X";
       }
        System.out.println(output);

    }

    //accessor & mutators
    private static int getSize(){
        return size;
    }

    private static void incrementSize(){
        size++;
    }

    private static void decrementSize(){
        size--;
    }
    private CellNode getHead(){
        return this.head;
    }


    //inner Class
    private class CellNode{
        private CellPhone data;
        private CellNode next;

        //default constructor that sets items to null
        public CellNode(){
            data=null;
            next =null;
        }
        //parameter constructor
        public CellNode(CellPhone p,CellNode n){
            data=p;
            next =n;
        }
        //copy Constructor
        public CellNode(CellPhone p){
            data=p;
            next =null;
        }

        public CellPhone getData() {
            return data;
        }

        public void setData(CellPhone data) {
            this.data = data;
        }

        public CellNode getNext() {
            return next;
        }

        public void setNext(CellNode next) {
            this.next = next;
        }
    }
}
