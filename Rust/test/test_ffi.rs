
use std::slice;
use crate::test::test_mem::StringStorage;
use crate::{string_to_CString,char_to_string};
use std::os::raw::c_char;
use std::mem::transmute;
use std::ffi::{CStr, CString};

#[no_mangle]
pub extern "C" fn test_print_array( c_array: *const f32,len:usize) {

    // let array = unsafe {
    //     assert!(!c_array.is_null());
    //     slice::from_raw_parts(c_array, len)
    // };
    // let matrix = RustSparseMatrix{};
    // let array = array.try_into().expect("test_print_array into error");
    // matrix.print_array(array);


}


#[no_mangle]
pub extern "C" fn create_storage(path:*const c_char)->*mut StringStorage {
    let path = char_to_string(path);
    StringStorage::create_storage(path)
}
#[no_mangle]
pub extern "C" fn get_storage_len(storage: *mut StringStorage)-> usize  {
    let len =  StringStorage::get_storage_len(storage);
    len
}

#[no_mangle]
pub extern "C" fn add_words_raw(storage: *mut StringStorage,word: *const c_char) {
    let word = char_to_string(word);
    StringStorage::add_words_raw(storage,word)
}
#[no_mangle]
pub extern "C" fn get_word_raw(storage: *mut StringStorage,index:usize)-> *mut c_char {
    let word = StringStorage::get_word_raw(storage,index);
    let cstr = string_to_CString(word);
    // cstr.as_ptr() //不能返回*const c_char
    cstr.into_raw()
}


#[no_mangle]
pub extern "C" fn destroy(storage: *mut StringStorage) {
    unsafe { Box::from_raw(storage); }
    // let _storage: Box<StringStorage> = unsafe{ transmute(storage) };
}

#[no_mangle]
pub extern "C" fn destroy_c_char(str_ptr: *const c_char) {
    let str_ptr = str_ptr as *mut c_char;
    // unsafe { Box::from_raw(str_ptr); }
    // let _: Box<c_char> = unsafe{ transmute(str_ptr) };
    let c_string = unsafe{ CString::from_raw(str_ptr) };
}

#[no_mangle]
pub extern "C" fn create_string() -> *mut c_char {
    let str = string_to_CString("hahaha".to_string());
    str.into_raw()
}


#[test]
fn test_storage_read() {
    let path = "/private/tmp/part_data/part_1.txt".to_string();
    let path = CString::new(path).expect(" error path ");
    let path = path.to_str().unwrap().to_string();
    let ss = StringStorage::create_storage(path);
    let len = get_storage_len(ss);
    println!("{:?}",len);
    // add_words_raw(ss,word);

    let word = get_word_raw(ss,0);
    // println!("## c_char: {:?}",word);
    let word = char_to_string(word);
    // let storage_ptr = create_storage()
    println!("{}",word);

    destroy(ss);
    // println!("{:?}",len);

}