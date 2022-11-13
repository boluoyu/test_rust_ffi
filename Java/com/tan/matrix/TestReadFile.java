package com.tan.matrix;

import java.io.IOException;

public class TestReadFile {

    static void test2() {
        String path =  "/private/tmp/part_data/part_0.txt";
        String content = "";
        try {
            content = FileIOUtil.read(path,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(16000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(content.length());

    }
    public static void main(String[] args) {
        test2();

    }
}
