package logic;

import java.util.LinkedList;

public class StringCompression {
    MinPriorityQ charFreq;
    HuffmanTree huffmanTree;
    LinkedList<Character> uniqueChars;
    LinkedL encoded = new LinkedL();

    public void compress(String str){

        uniqueChars = getUniqueChars(str);
        charFreq = getCharFreq(uniqueChars, str);
        huffmanTree = new HuffmanTree();
        huffmanTree.insert(charFreq);
        encode(huffmanTree.root, "");
    }

    private LinkedList<Character> getUniqueChars(String str) {

        LinkedList<Character> uniqueChars = new LinkedList<>();
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if( !uniqueChars.contains(ch)){
                uniqueChars.add(ch);
            }
        }
        return uniqueChars;
    }

    MinPriorityQ getCharFreq(LinkedList<Character> chars, String str){
        MinPriorityQ q = new MinPriorityQ();
        for( int i=0; i<chars.size(); i++){
            int count = 0;
            char ch = chars.get(i);
            for(int j=0; j<str.length(); j++){
                char symbol = str.charAt(j);
                if( ch == symbol ){
                    count++;
                }
            }
            q.Enqueue(ch,count);
        }
        return q;
    }

     public void encode(Node node, String prefix){

        if (node == null) {
            return ;
        }
        else if ( node.left == null && node.right == null ){
            encoded.insert(node.val,prefix);
            return ;
        }
        encode(node.left, prefix + "0");
        encode(node.right, prefix + "1");
    }

    public void decode(String bits){
       String match = "";
       for(int i=0 ; i<bits.length(); i++){
           match += bits.charAt(i);
           char ch = encoded.hasBits(match);
           if(ch != '\0'){
               System.out.print(ch);
               match = "";
           }
       }
        System.out.println('\0');

    }

    void getSize(String str){
        System.out.println("Actual bits: "+ str.length() * 8);
        System.out.print("Bits after compression: ");
        int bytes = 0;
        for(int i=0 ; i<str.length(); i++){
            char c = str.charAt(i);
            String st = encoded.getBits(c);
            bytes += st.length();
        }
        System.out.println(bytes);

    }

    public void compressToFile(String fileName, String str){
        BinaryFile f = new BinaryFile(fileName, 'w');
        compress(str);
        String ss = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            LinkedL.Node temp = encoded.head;
            while (temp != null) {
                if (c == temp.ch) {
                    ss += temp.bits;
                    break;
                }
                temp = temp.next;
            }
        }
        for(int i=0; i<ss.length(); i++){
            if( ss.charAt(i) == '1')
                f.writeBit(true);
            else {
                f.writeBit(false);
            }
        }
        f.close();
    }

    public String decompressFile(String fileName){
        BinaryFile f = new BinaryFile(fileName, 'r');
        String decodedText = "";
        String stt = "";
        do{
            if (f.readBit()) {
                stt += "1";
            } else {
                stt += "0";
            }
            char c = encoded.hasBits(stt);
            if (c != '\0') {
                decodedText += c;
                System.out.print(c);
                stt = "";
            }

        }while(f.EndOfFile());
        System.out.println();
        return decodedText;
    }
}
