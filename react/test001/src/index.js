//假设mainjs还是webpack的入口文件
console.log("ok 0")

// 1.这里两个导入的时候，接收的成员名称必须这么些
import React from 'react' //创建爱你组件，虚拟dom元素，生命周期
import ReactDom from 'react-dom' //把创建好的组件和虚拟DOM放到页面上展示

// 2.创建虚拟DOM元素

//参数1 = 创建的元素的类型、字符串、表示元素的名称；
//参数2 = 使一个对象或者null，表示当前的这个DOM元素的属性
//参数3 = 子节点（包括其它虚拟DOM 获取文本 子节点）
//参数 n = 其余的子节点
// <h1 id = "myh1" tile = "this is a h1">xxx</h1> 

// const myh1 = React.createElement('h1',null,'这是一个H1')

const myh1 = React.createElement('h1',{ id :'myh1' , title: 'this is a div'},'这是一个H1')

const myDiv = React.createElement('div',null,'这是一个div元素',myh1)

//3. 使用ReactDOM把虚拟DOM渲染到页面上

//参数1 = 要渲染的虚拟DOM元素
//参数2 = 一个dom元素，不是选择器，指定页面上的容器
// ReactDom.render(myDiv,document.getElementById('app'))

const myTest = <div> aaa </div>
ReactDom.render(myTest,document.getElementById('app'))

