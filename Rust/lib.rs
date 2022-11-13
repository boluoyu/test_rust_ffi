extern crate core;

// pub mod test3;
// pub mod test1;
// pub mod test2;

pub mod matrix;
pub mod io;
pub mod nlp;
pub mod test;

use std::ffi::{CStr, CString};
use std::mem::transmute;
// use test2::test_ffi::RustSparseMatrix;
use std::os::raw::c_char;

pub fn char_to_string(input: *const c_char) ->String {
    let input = unsafe {
        CStr::from_ptr(input)
    };
    let input = input.to_str();
    let input = input.unwrap().to_string();
    input
}

//这种方法是错误的，这样会导致*const 丢失生命周期，会被销毁，得到错误结果
pub fn _string_to_char_(input:String) ->  *const c_char {
    let cstr = CString::new(input).unwrap();
    let ptr = cstr.as_ptr();
    ptr
}

//返回 有生命周期的 CString，由调用方 调用 CString.as_ptr  得到 pointer
pub fn string_to_CString (input:String) -> CString {
    let cstr = CString::new(input).unwrap();
     cstr
}


#[test]

fn test_string_to_char() {
    let haha = " 北京".to_string();
    println!(" {}",&haha);
    // let cstr = _string_to_char_(haha); //这是错误的，*const 会被销毁

    let cstr = string_to_CString(haha);
    let c_char = cstr.as_ptr(); //这样是可以的

    let ss = char_to_string(c_char);
    // println!("{:?}",r);
    println!("|| {:?}",ss); //会有双引号
}

#[test]
fn test_raw() {
    let input = "北京";
    let cstr = CString::new(input).unwrap();
    let ptr = cstr.as_ptr();
    let input = unsafe {
        let input = CStr::from_ptr(ptr);
        input
    };
    let input = input.to_str();
    let input = input.unwrap().to_string();
    println!("{:?}",input);

}