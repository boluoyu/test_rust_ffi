//
//  storage_lib.cpp
//  rust_ffi_cpp
//
//  Created by 菠萝鱼 on 2022/11/11.
//  Copyright © 2022 谭英干. All rights reserved.
//

#include "storage_lib.hpp"


namespace storage {

//"/private/tmp/part_data/part-00000"
StringStorageCpp::StringStorageCpp(const char* path) :
 
storage_ins(new_storage(path)) {
    
    
    
}


StringStorage StringStorageCpp::new_storage(const char* path) {
//    const char* c_path = path.c_str();
   return create_storage(path);
   
}


size_t StringStorageCpp::get_len() {
    
    return get_storage_len(storage_ins);
}


void StringStorageCpp::add_words(const char* word){
    add_words_raw(storage_ins, word);
}


const char* StringStorageCpp::get_word(size_t index) {
    return get_word_raw(storage_ins, index);
}

void StringStorageCpp::destroy_cpp() {
    
    destroy(storage_ins);
}

void StringStorageCpp::destroy_string(const char * word) {
    
    destroy_c_char(word);
}

const char* StringStorageCpp::create_str() {
    return create_string();
}


}
