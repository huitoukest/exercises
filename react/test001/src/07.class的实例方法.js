console.warn("runing in 07.js")
//es6 中，class关键字是面向对象编程的新型式。


function Person(name,age){
    this.name = name // 这种属性需要通过new出来的实例进行访问，这种属性叫做实例属性
    this.age = age
}

Person.prototype.say = function(){
    console.log("这个是Person的实例方法")
}

const p1 = new Person("名称",18)
console.info(p1)
p1.say() //这是实例方法

Person.show = function(){
    console.log("person的静态方法")
}
Person.show();




//2.使用class的方式来创建对象
//静态方法和属性实际上都是挂载到了构造器上，打印对象即可查看到
//注意：1.在class的内部，只能够写构造器、静态方法/属性和实例方法/属性
//2. class关键字还是用原来的function模式实现的，class只是一个语法糖;

class Animal{
    constructor(name,age){
        console.log("constructor init")
        //实例属性
        this.name = name
        this.age = age
    }
    //在class 内部，通过static修饰的属性就是静态属性
    static info = "info test22"
    //挂载到原型对象的实例方法
    roar(){
        console.log("实例方法：咆哮roar")
    }

    //通过static关键字来建立静态方法
    static show(){
        console.log("Animal的静态show方法")
    }
}

const a1 = new Animal('大黄',3)
console.info(a1)
a1.roar()
Animal.show()
