//假设mainjs还是webpack的入口文件
console.log("ok 0")

// 1.这里两个导入的时候，接收的成员名称必须这么些
import React from 'react' //创建爱你组件，虚拟dom元素，生命周期
//这里的React-dom推荐写为ReactDOM
import ReactDOM from 'react-dom' //把创建好的组件和虚拟DOM放到页面上展示

//1.第一种创建组件的方式，可以直接把组件以标签的形式使用
//构造函数特点，首字母大写
function Hello(props){
    //1.如果在一个组件中return null，则表示此组件什么都不渲染
    //return null

    //2.在组建中，必须返回一个合法的JSX虚拟DOM元素
    return <div> 这是一个Hello组件 -- {props.name} </div>
    
    //注意，这里的props参数是只读属性，即内部无法更改外部传入的属性
    //无论是vue还是React，组建中的props都是只读的，无法修改。
}

const user = {
    name: 'bigName',
    age: 110,
    gender: 'male'
}

//如果首字母小写，将不会被jsx当作组件渲染，而你会当成一个普通的标签，则会渲染错误
function Hello2(props){
    return <div> 这是一个Hello2组件 -- {props.name} </div>
}

//<Hello2  {...user} ></Hello2> 这种 三个点表示展开运算，能将对象属性进行展开。


const myTest = <div id="myDiv" title="div aaa" > 
                    aaa 测试内容 myTests 
                   { /*第一种方式 ： <Hello name = {user.name} ></Hello> */  }

                   <Hello2  {...user} ></Hello2>
                </div>


ReactDOM.render(myTest,document.getElementById('app'))

