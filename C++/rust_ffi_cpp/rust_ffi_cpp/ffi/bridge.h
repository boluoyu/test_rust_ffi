//
//  bridge.h
//  rust_ffi_cpp
//
//  Created by 菠萝鱼 on 2022/11/11.
//  Copyright © 2022 谭英干. All rights reserved.
//


typedef void* StringStorage;


#ifndef bridge_h
#define bridge_h


#endif /* bridge_h */




#ifdef __cplusplus

 

 
#include <cstdlib>
#include <string.h>
#include <iostream>


using namespace std;
extern "C" {
#endif
 



//StringStorage create_storage(const char*);
StringStorage create_storage(const char*);

size_t get_storage_len(StringStorage);

void add_words_raw(StringStorage,const char*);

const char* get_word_raw(StringStorage,size_t);

void destroy(StringStorage);
void destroy_c_char(const char*);

const char* create_string();


#ifdef __cplusplus
} // extern "C"
#endif
