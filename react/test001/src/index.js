//假设mainjs还是webpack的入口文件
console.log("ok 0")

// 1.这里两个导入的时候，接收的成员名称必须这么些
import React from 'react' //创建爱你组件，虚拟dom元素，生命周期
//这里的React-dom推荐写为ReactDOM
import ReactDOM from 'react-dom' //把创建好的组件和虚拟DOM放到页面上展示

//如果不做单独的配置的话，是不能够省略(.jsx)后缀名的
import Hello from "./components/Hello.jsx"

const user = {
    name: 'bigName',
    age: 110,
    gender: 'male'
}


//<Hello2  {...user} ></Hello2> 这种 三个点表示展开运算，能将对象属性进行展开。


const myTest = <div id="myDiv" title="div aaa" > 
                    aaa 测试内容 myTests 
                   { /*第一种方式 ： <Hello name = {user.name} ></Hello> */  }

                   <Hello  {...user} ></Hello>
                </div>


ReactDOM.render(myTest,document.getElementById('app'))

