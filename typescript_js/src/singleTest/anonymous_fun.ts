//anonymous_fun 匿名函数，箭头函数
namespace anonymous_fun{


    let oneF = arg => arg + 1;

    let sum = (arg1, arg2) => arg1 + arg2;

    let sum2 = (arg1, arg2) => {
        return arg1 + arg2;
    }

    let myArray = [1, 2, 3, 4];
    console.log( myArray.filter(value => value % 2 == 0) );

    //anonymous_fun能够消除javascript this关键字的问题；
    //比如定时器和setInterval、setTimeout中的参数是一个函数，此函数中的this指的是匿名函数的this；
    //箭头函数中的this是调用此this的外围函数的this。
    


}