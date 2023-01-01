package logic;

class Element{
        Node node;
        Element next;
        Element prev;

        Element(Node node){
            this.node = node;
            this.next = null;
            this.prev = null;
        }
    }

public class MinPriorityQ {

    Element head,tail;
    int size;

    void Enqueue(char val, int freq){
       Node node = new Node(val,freq);
       Enqueue(node);
    }

    void Enqueue(Node node){
        Element e = new Element(node);

        if(head == null){
            head = e;
            tail = e;

        } else if ( head.node.getFreq() >= node.getFreq()){
            e.next = head;
            head.prev = e;
            head = e;

        } else if ( tail.node.getFreq() <= node.getFreq() ){
            tail.next = e;
            e.prev = tail;
            tail = e;

        } else {
            Element temp = head;
            while( temp.node.getFreq() < node.getFreq() ){
                temp = temp.next;
            }
            e.next = temp;
            if( temp.prev != null ){
                temp.prev.next = e;
            }
            e.prev = temp.prev;
            temp.prev = e;
        }
        size++;

    }

    void print(){
        Element temp = head;
        System.out.println("Node Val\tNode Freq");
        while(temp != null){
            System.out.println(temp.node.getVal()+"\t\t\t"+temp.node.getFreq());
            temp = temp.next;
        }
    }

    Node Dequeue(){
        if (head == null){
            return null;
        } else {
            Node temp = head.node;
            if (head == tail){
               tail = null;
               head = null;
            }
            else{
                head = head.next;
                head.prev = null;
            }
            size--;
            return temp;
        }
    }

    int size(){
        return size;
    }

}
