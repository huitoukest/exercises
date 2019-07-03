namespace parameterType{
    //参数类型测试
    let myName: String = "abc"; 
    
    let alias: any = "123";
    alias = 456;

    let age: number = 222;

    let isMan: boolean = true;
    /*
     * void 类型也是一种类型
     */
    function voidRe():void{

    }

    class Person{
        name: String;
        age: number;

    }

    let zhangSan: Person = new Person();
    zhangSan.age = 85;
    zhangSan.name = "zhangSan";
    console.log(zhangSan);

}