//typescript 接口interface定义，implements实现
namespace _interface{

    interface IPerson{
        name: string;
        age: number;
    }

    class Person implements IPerson{
        name: string;        
        age: number; // 自动继承构造方法

        //或者手动定义构造方法，此方法中会自动检测传入的参数是否符合要求
        constructor(public ip: IPerson){
            this.name = ip.name;
            this.age = ip.age;
        }

    }
    
    let userA = new Person({
        name: "A",
        age: 18
    })

    interface Animal{
        //接口的第二种使用方式就是申明方法
        eat();
    }

    class Cat implements Animal{
        eat() {
            console.log("eating");
        }

    }

}