console.log("使用extends继承")
//es6 中，class关键字是面向对象编程的新型式。


class Person{
    constructor(name,age){
        this.name = name // 这种属性需要通过new出来的实例进行访问，这种属性叫做实例属性
        this.age = age
    }
    say(){
        console.log("say hello")
    }
}

const a1 = new Person("your name",20)
console.log(a1)

//在class类中，可以使用extends关键字 实现子类继承父类
class Chinese extends Person{
      constructor(name,age){
          super(name,age)
      }
}
/**
 * 继承的说明
 * 1.一定要在子类中的constructor中调用super函数
 *  因为如果一个子类通过extend继承了一个父类，则必须在constructor中首行调用super函数
 * 2.super是一个函数，实际上它就是对父类中构造器的引用，调用此super方法则相当于掉用了
 *   父类中的构造函数
 * 3.调用super中传入的参数会传入到父类的构造器中执行  
 * 
 */

const ch1 = new Chinese('chinese name',21)
console.log(ch1)
ch1.say()