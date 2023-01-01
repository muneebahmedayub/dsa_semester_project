package logic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StringCompression sc = new StringCompression();
        Scanner s = new Scanner(System.in);
        System.out.print("Enter String to compress:");
        String str = s.nextLine();
        sc.compress(str);
        char ch;
        do{
        System.out.print("Choose One" +
                "\n1.Get size before and after compression." +
                "\n2.Get codes for each Character." +
                "\n3.Create a compressed file of the String." +
                "\n4.Read the compressed file. " +
                "\nEnter: ");
        int inp = s.nextInt();s.nextLine();
        switch(inp){
            case 1:
                System.out.println("\n__________________________________");
                sc.getSize(str);
                System.out.println("__________________________________\n");
                break;
            case 2:
                System.out.println("\n__________________________________");
                sc.encoded.print();
                System.out.println("__________________________________\n");
                break;
            case 3:
                System.out.println("\n__________________________________");
                System.out.print("Enter your desired name:");
                String name = s.nextLine();
                sc.compressToFile(name+".zipp",str);
                System.out.println("__________________________________\n");
                break;
            case 4:
                System.out.println("\n__________________________________");
                System.out.print("Paste the file path: ");
                String n = s.nextLine();
                if(!n.endsWith(".zipp")){
                    System.out.println("Not a valid File.");
                } else {
                    sc.decompressFile(n);
                }

                System.out.println("__________________________________\n");
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
            System.out.print("Exit(Y/N)? ");
            ch = s.next().charAt(0); s.nextLine();

        }while(ch == 'N' || ch == 'n');

    }
}
