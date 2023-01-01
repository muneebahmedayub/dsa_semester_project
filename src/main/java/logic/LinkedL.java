    package logic;

    import java.util.Arrays;
    import java.util.BitSet;

    public class LinkedL {
        class Node {
            char ch;
            String bits;
            Node next;
            Node(char ch, String bits){
                this.ch = ch;
                this.bits = bits;
                this.next = null;
            }
        }

        Node head, tail;
        int size;

        void insert(char ch, String bits){
            Node node = new Node(ch,bits);
            if( head == null ){
                head = node;
                tail = node;
                size++;
                return;
            }
            tail.next = node;
            tail = node;
            size++;
        }

        char hasBits(String match){
            Node temp = head;
            boolean flag = false;

            while(temp!=null){
                if(temp.bits.equals(match)){
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if(flag){
                return temp.ch;
            } else {
                return '\0';
            }
        }

        String getBits(char c){
            Node temp = head;
            while(temp!=null){
                if(temp.ch == c){
                    return temp.bits;
                }
                temp = temp.next;
            }
            return null;
        }

        void print(){
            Node temp = head;
            while ( temp != null ){
                System.out.println(temp.ch + " | " + temp.bits);
                temp = temp.next;
            }
        }

    }
