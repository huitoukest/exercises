//析构表达式，将对象或者数组拆为对应的变量
namespace destructuring_code{
    
    function getStock(){
        return {
            code: "IBM",
            price: {
                price1: 100,
                price2: 500,
            }
        }
    }
    let stock = getStock();
    let code1 = stock.code;

    let {code,price: {price2}} = getStock();
    console.log(code)
    //codex这里是别名
    let {code: codex,price: {price1}} = getStock();
    console.log(codex)


    //数组析构
    let array1 = [1, 2, 3, 4];
    let [a, b, c, d] = array1;
    console.log(a,b,c,d)

    let [x,...y] = array1;
    console.log(a,y)

    //方法参数

    function funA(num1, num2 , ...num3 ){
        console.log(num1);
    }
    //funA(array1);
}