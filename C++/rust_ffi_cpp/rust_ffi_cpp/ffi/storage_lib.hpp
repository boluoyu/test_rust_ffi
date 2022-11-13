//
//  storage_lib.hpp
//  rust_ffi_cpp
//
//  Created by 菠萝鱼 on 2022/11/11.
//  Copyright © 2022 谭英干. All rights reserved.
//

#ifndef storage_lib_hpp
#define storage_lib_hpp

#include <stdio.h>
#include "bridge.h"

#endif /* storage_lib_hpp */

using namespace::std;

namespace storage {


class StringStorageCpp {
    
    
public:
    
    StringStorageCpp(const char*) ;
    ~StringStorageCpp(){};
    

  
    size_t get_len();
    void add_words(const char*);
    const char* get_word(size_t index);

 
    StringStorage new_storage(const char*);
    
    void destroy_cpp();
    
    void destroy_string(const char*);
    
    const char* create_str();
    
private:
    //const char* path;
    StringStorage storage_ins;
    
    
    
};

}


