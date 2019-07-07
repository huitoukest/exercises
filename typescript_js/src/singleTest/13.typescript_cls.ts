//typescript_cls
namespace typescript_cls{
    class Person{
        name;//default is public
        private age;//私有访问
        protected woman;//子类和当前类能够访问protected

        constructor(name,age,woman = false){
            this.name = name;
            this.age = age;
            this.woman = woman;
        }

        eat(){
            console.log(`i'm(${name}) eating`);
        }

        public getAge(){
            return this.age;
        }
    }

    let p1 = new Person("a",18);
    p1.name = '张';
    p1.eat();


    class WomanPerson extends Person{

        //子类的构造函数必须使用super调用父类的构造函数
        constructor(name,age,){
            super(name,age,true)
        }

        public work(){
            super.eat();
            console.log("eat first ,then work!");
        }

    }

}