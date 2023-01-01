package logic;

import java.util.LinkedList;
import java.util.Queue;

public class HuffmanTree {
    Node root;


    void insert(MinPriorityQ q) {

        while(q.size() > 1){
            Node left = q.Dequeue();
            Node right = q.Dequeue();

            Node parent = new Node('-', left.freq+right.freq);
            parent.left = left;
            parent.right = right;

            q.Enqueue(parent);
        }
        root = q.Dequeue();
    }

    
}
