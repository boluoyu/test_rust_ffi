use std::ffi::CString;
use crate::io::file_utils::read_file;
use std::mem::transmute;
use std::time::Instant;
//1363257406850654208     @_@北京 冬奥会 和 冬 残奥会 举行 测试 活动      @_@近日 为了


fn parse_line(line:String)->Vec<String> {
    let mut all_words = Vec::new();
    let words = line.split("\t@_@");
    for many_words in  words {
        let words:Vec<String> = many_words.to_string().split(" ")
            .filter(|x| { let count = x.chars().count();count>=2 && count < 6})
            .map(|x|x.to_string()).collect();
        all_words.extend(words);
    }
    all_words
}

#[repr(C)]
pub struct StringStorage{

    words:Vec<String>,

}

impl StringStorage {

    pub fn new(words:Vec<String>) ->Self {
        Self{ words}
    }

    pub fn get_len(&self)->usize {
        self.words.len()
    }

    pub fn add_words(&mut self,word:String) {
        self.words.push(word);
    }

    pub fn get_word(&self,index:usize)->String {
        let opt = self.words.get(index);
        if opt.is_some() {
            opt.unwrap().to_owned()
        }else {
            "no exit ".to_string()
        }
    }

    //=============C interface==============
    pub fn create_storage(path:String)-> *mut StringStorage {
        let words = read_all_words(path);
        // let mut  words = Vec::new();
        // words.push("11".to_string());

        let storage = StringStorage::new(words);
        let box_storage = Box::new(storage);
        let storage = Box::into_raw(box_storage);
        // let storage = unsafe { transmute(box_storage) };
        storage
    }

    pub fn get_storage_len(storage:  *mut StringStorage)-> usize  {
        let mut _storage = unsafe { &mut *storage };
        _storage.get_len()
    }

    pub fn add_words_raw(storage: *mut StringStorage, word:String) {
        // let mut storage = unsafe {
        //     Box::from_raw(storage) };

        let mut _storage = unsafe { &mut *storage };
        _storage.add_words(word);
    }

    pub fn get_word_raw(storage: *mut StringStorage, index:usize) ->String {
        let mut _storage = unsafe { &mut *storage };
        _storage.get_word(index)
    }
    //===================

}

fn read_all_words(path:String)-> Vec<String> {
    let content = read_file(path);
    let mut all_words = Vec::with_capacity(100000);
    for line in content.split("\n") {
        let words = parse_line(line.to_string());
        all_words.extend(words);
    }
    all_words
}
#[test]
fn test_read() {
    let path = "/private/tmp/part_data/part-00000";
    let content = read_file(path);
    for line in content.split("\n") {
        let words = parse_line(line.to_string());
        let len = 10.min(words.len());
        let words = &words.as_slice()[..len];
        println!("{:?}",words);
    }
}

#[test]
fn test_storage_read() {
    let path = "/private/tmp/part_data/part-00000".to_string();
    let path = CString::new(path).expect(" error path ");
    let path = path.to_str().unwrap().to_string();
    let ss = StringStorage::create_storage(path);
    let len = StringStorage::get_storage_len(ss);
    let word = "haha".to_string();
    StringStorage::add_words_raw(ss,word);
    println!("{:?}",ss);


    println!("{:?}",len);

}

#[test]
fn test_storage_read2() {
    let path = "/private/tmp/part_data/part-00000".to_string();
    let vec = read_all_words(path);
    let storage = StringStorage::new(vec);
    let len = storage.get_len();
    println!("len {}",len);

    let word = storage.get_word(0);
    println!("word {} ",word);

}

#[test]
fn test_read3() {

    let now = Instant::now();
    let path = "/private/tmp/part_data/part_0.txt";
    let content = read_file(path);
    let cost = now.elapsed().as_millis();
    // let count = content.chars().count();
    let count = 0;
    println!(" {}\t{}",count,cost)


}

#[test]
fn test_vec() {
    let vec = vec![1,2,3,4,5,6];

    let vec = vec.chunks(3);
    vec.for_each(|x | {println!("{:?}",x )})
}