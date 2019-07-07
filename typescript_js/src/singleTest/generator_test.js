//import "core-js/modules/es7.symbol.async-iterator"
//generator_test
    //通过 generator： function* 和 yeild 控制函数的执行过程，手动暂停和恢复代码执行
    //通过
function*  doSomething(){
    console.log("start");
    yield;
    console.log("finish");
}

let functionA = doSomething();
functionA.next();