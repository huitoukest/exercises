//rest_and_spread
namespace rest_and_spread{
    //函数特性，rest and spread操作符
    /**
     * 
     * @param args 可变参数
     */
    function test1(...args){
        args.forEach(it => console.info(it))
    }
    test1();
    test1(1,2,3);

    function test2(...args){
        args.forEach(it => console.info(it))
    }

    let arr = [1,2]
    //调用展开时，无法确定参数的个数，所以函数test2接受时最好也使用可变参数
    test2(...arr)

    //函数解构

    const [first, ...rest] = [1, 2, 3, 4, 5];  
    console.info(first,rest);
    //将字符串转为真正的数组
    [..."hello".split("")]
    // [ "h", "e", "l", "l", "o" ]  
}