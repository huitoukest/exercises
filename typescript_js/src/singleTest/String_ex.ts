/**
 * 字符串的特性
 */
namespace stringTest{
    //模板字符串与多行字符串
    let  stringa = `
    aaa
      bbb
    ccc
    ` ;
    console.log(stringa);
    let name = `hello ${stringa}`; 
    console.log(name);

    //自动拆分字符串

    function test(template: any,name: string, age: number): void{
        console.log(template,name,age);
    }

    let myName = "zhai liang";

    let getAge = function():number{
        return 18;
    }
    //默认将字符串模板拆分为多个参数（一个数组），其中第一个参数表示模板字符串除以${}分隔的部分组成了一个数组：内容是["hello my name is ", " , i'm ", ""];
    //之后的内容就是每个${}中的内容
    test`hello my name is ${myName} , i'm ${getAge()}`;

}