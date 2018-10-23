console.warn("runing in 06.js")
//es6 中，class关键字是面向对象编程的新型式。

//1.传统的创建对象或者函数的方式
function Person(name,age){
    this.name = name // 这种属性需要通过new出来的实例进行访问，这种属性叫做实例属性
    this.age = age
}

const p1 = new Person("名称",18)
console.log(p1)
console.log(p1.name) //访问实例属性

//2.使用class的方式来创建对象
class Animal{
    //这是类中的构造器
    //每一个类中，都有一个构造器，如果没有显示指定，那么每个类会默认建立一个空的构造器
    //构造器的作用：当new一个类的时候，首先执行构造器中的内容
    constructor(name,age){
        console.log("constructor init")
        //实例属性
        this.name = name
        this.age = age
    }
    //在class 内部，通过static修饰的属性就是静态属性
    static info = "info test22"
}

const a1 = new Animal('大黄',3)
console.log(a1)

Person.info = "info test"
// 3.静态属性 ： 直接通过函数来访问和使用的属性，不需要new实例来访问
console.log(Person.info);
console.log(p1.info); // undefined

console.log(Animal.info); // info test22
console.log(a1.info); // undefined

