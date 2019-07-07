//for_of 匿名函数，箭头函数
namespace for_of{

    let array1 = [1,2,3,4,"aa","b"]

    //break在forEach中是无效的
    array1.forEach(value => console.log(value));
    //键循环
    for(let value in array1){
        console.log(value)
    }
    //值循环
    for(let value of array1){
        console.log(value)
    }

    //打印出每一个字符串中的字符
    let str = "abcdefg";
    for(let c of str){
        console.log(c);
    }

}