// 1.这里两个导入的时候，接收的成员名称必须这么些
import React from 'react' //创建爱你组件，虚拟dom元素，生命周期
//这里的React-dom推荐写为ReactDOM
import ReactDOM from 'react-dom' //把创建好的组件和虚拟DOM放到页面上展示

//import '@/06.class的基本使用.js'
//import '@/07.class的实例方法.js'
//import '@/08.class使用extends继承.js'
//import '@/09.子类属性与方法.js'
import '@/10.使用class关键字创建组件.js'


const myTest = <div id="myDiv" title="div aaa" > 
                    aaa 测试内容 myTests
                </div>

ReactDOM.render(myTest,document.getElementById('app'))

