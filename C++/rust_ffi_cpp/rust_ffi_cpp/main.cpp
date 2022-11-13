//
//  main.cpp
//  rust_ffi_cpp
//
//  Created by 菠萝鱼 on 2022/11/11.
//  Copyright © 2022 谭英干. All rights reserved.
//

#include <iostream>

#include "storage_lib.hpp"
using namespace::storage;

int main(int argc, const char * argv[]) {
   
    std::cout << "Hello, World!\n";
    const char*  path = "/private/tmp/part_data/part_1.txt";
    StringStorageCpp *ss = new StringStorageCpp(path);
    
    size_t len = ss->get_len();
    std::cout << " len "<< len << std::endl;
    
    const char* word = ss->get_word(0);
       std::cout << " word "<< word << std::endl;
    
    ss->destroy_string(word);
    
    
    ss->destroy_cpp();
    
    cout<< "end "<<endl;
    
    
    return 0;
}
