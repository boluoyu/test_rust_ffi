package com.tan.matrix;

import com.tan.matrix.StringStorageLib.StringStorageCpp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestStringStorage {


    static void test1() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String path =  "/private/tmp/part_data/part_0.txt";
        StringStorageLib.StringStorageCpp storageCpp = new StringStorageCpp(path);
//
        int length = storageCpp.get_len();
        System.out.println(" " + length);
        System.out.println("haha");

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        storageCpp.add_words("11");
        storageCpp.add_words("12");
        int length1 = storageCpp.get_len();
        System.out.println(" " + length1);

        int index = 0;

        String word = storageCpp.get_word(index);

        System.out.println(" get word " + word);

        String word2 = storageCpp.get_word(1);
        System.out.println(" word2 " +word2);


//        storageCpp.destroy_string(word);//会导致 jvm 退出 ， 可能没必要释放 由destroy_cpp

        System.out.println(" destroy_cpp start ");
        storageCpp.destroy_cpp();

        System.out.println(" destroy_cpp over");
        //释放内存后，再次测试，这里length2 有可能是随机
        int length2 = storageCpp.get_len();
        System.out.println(" " + length2);
        //======访问已释放的内存 jvm 会崩================
//        String word3 = storageCpp.get_word(1);
//        System.out.println(" word2 " +word3);


    }

    static void test2() {
        String path =  "/private/tmp/part_data/part_0.txt";
        StringStorageLib.StringStorageCpp storageCpp = new StringStorageCpp(path);

        String haha = storageCpp.create_str();
        System.out.println(" " + haha);

        storageCpp.destroy_string(haha);

        System.out.println("end ");
    }

    static void testReadFile() throws IOException {

        long start = System.currentTimeMillis();
        String path =  "/private/tmp/part_data/part_0.txt";
        String content = FileIOUtil.read(path, "utf-8");
        System.out.println(content.length());

        long end = System.currentTimeMillis();

        System.out.println(" cost time " + (end - start));
    }

    public static void main(String[] args) throws Exception {
        testReadFile();
//        Thread.sleep(1000000);
    }
}
