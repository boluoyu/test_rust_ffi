package com.tan.matrix;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Namespace("storage")
@Platform(include = {"storage_lib.cpp"},link = "matrix_lib_rust")

public class StringStorageLib {

    public static class StringStorageCpp extends Pointer  {

        static {
            Loader.load();
        }
//        private native void allocate();
        private native void allocate(String path);


        public StringStorageCpp(String path) {
            allocate(path);
        }

        public native  int get_len();
        public  native void add_words(String word);
        public  native String get_word(int index);
        public native void destroy_cpp();
        public native void destroy_string(String word);

        public  native String create_str();

    }

}
