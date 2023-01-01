package logic;

import java.io.*;
class Assert {

    public static void notFalse(boolean flag, String message) {
        if (!flag) {
            System.out.println(message);
            throw new IllegalArgumentException();
        }
    }

    public static void notFalse(boolean flag) {
        if (!flag) {
            System.out.println("Failed notFalse Assertion");
            throw new  IllegalArgumentException();
        }
    }

    public static void notNull(Object obj, String message) {
        if (obj == null) {
            System.out.println(message);
            throw new IllegalArgumentException();
        }
    }

    public static void notNull(Object obj) {
        if (obj == null) {
            System.out.println("Failed notNull Assertion");
            throw new IllegalArgumentException();
        }
    }
}

public class BinaryFile {

    private boolean inputFile;
    private RandomAccessFile file;
    private byte buffer;
    private int buf_length;
    private int total_bits;
    private int bitsleft;
    private int bitsread;

    public BinaryFile(String filename, char readOrWrite) {
        buffer = (byte) 0;
        int buf_length = 0;
        total_bits = 0;
        bitsleft = 0;
        bitsread = 0;
        total_bits = 0;
        buffer=0;
        bitsread = 0;
        try {
            if (readOrWrite == 'w' || readOrWrite == 'W') {
                inputFile = false;
                file = new RandomAccessFile(filename, "rw");
                file.writeInt(0); /* header -- # of bits in the file */
            } else if (readOrWrite == 'r' || readOrWrite == 'R') {
                inputFile = true;
                file = new RandomAccessFile(filename, "r");
                total_bits = file.readInt();
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public boolean EndOfFile()
    {
        Assert.notFalse(inputFile,"EndOfFile only relevant for input files");
        return bitsread < total_bits;
    }

    public void writeBit(boolean bit)
    {
        byte bit_;
        Assert.notFalse(!inputFile,"Can't write to an input file");
        total_bits++;

        if (bit)
            bit_ = 1;
        else
            bit_ = 0;
        buffer |= (bit_ << (7 - buf_length++));
        try
        {
            if (buf_length == 8)
            {
                file.writeByte(buffer);
                buf_length = 0;
                buffer = 0;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public boolean readBit()
    {
        Assert.notFalse(inputFile,"Can't read from an output file");
        Assert.notFalse(bitsread < total_bits,"Read past end of file");
        try
        {
            if (bitsleft == 0)
            {
                buffer = file.readByte();
                bitsleft = 8;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        bitsread++;
        return (((buffer >> --bitsleft) & 0x01) > 0);
    }

    public void close() {
        try
        {
            if (!inputFile)
            {
                if (buf_length != 0)
                {
                    while (buf_length < 8)
                    {
                        buffer |= (0 << (7 - buf_length++));
                    }
                    file.writeByte(buffer);
                }
                file.seek(0);
                file.writeInt(total_bits);
            }
            file.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

}
