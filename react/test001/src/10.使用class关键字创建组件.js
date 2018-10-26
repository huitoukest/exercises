console.log("10.使用class关键字创建组件.js")
//当我们自己的类继承了React.Componet类之后就会自动成为一个组件，
//在组建的内部，必须有一个render函数，在render函数中必须返回合法的jsx虚拟DOM结构

import React from 'react'
import ReactDOM from 'react-dom'

class A extends React.Component{

    constructor(){
        //由于基础了父类，首先调用super()，之后就可以使用this关键字
        super()
        this.state={ //这里的this.state={}就相当于Vue中的data(){return{}};
            msg:"大家好 A组件msg",
    
        }
    }

    render(){
        this.state.msg += "--->可读写数据" 
        //在class关键字创建的组建中，如果想使用外接传递的参数，
        //不需要接收，直接使用this.props.XXX来使用即可
        //这里的this就表示当前的实例对象
        return <div>class extends component,
            通过class 创建的组件：
            <hr/>
            {this.props.name}--{this.props.age}
            <h5>{this.state.msg}</h5>
        </div>
    }
}

const user = {
    "name":"张三",
    "age":99
}

const myTest = <div id="myDiv" title="div aaa" > 
                    aaa 测试内容 myTests
                    <A {...user}> </A>
                    {/** 这里的组件标签实际上就相当于使其class的一个实例对象，并且默认使用render方法的返回值作为渲染 **/}

                </div>

ReactDOM.render(myTest,document.getElementById('app'))

/**
 * 注意：
 *  使用class关键字创建的组件有自己的私有数据；
 *  但是使用function创建的组件，只有props，没有自己的私有数据和生命周期函数；
 * 
 * 1.所有用构造函数构造的组件叫做"无状态组件"，用class关键字创建处理的组件
 *   叫做"有状态组件"
 * 2.有状态和无状态组件之间的区别就是：有无state属性和有无生命周期函数
 *
 * 3.如果一个组件需要有自己的私有数据，则推荐使用有状态组件;
 *   如果一个组件不需要有私有的数据，则推荐使用无状态组件;
 *   React官方：无状态组件由于没有自己的state和生命周期函数，所以运行效率比有状态组件稍微高一些;
 *      但是一般使用推荐使用有状态组件;
 * 4.组件中的props和state/data之间的区别：
 *   4.1 props中的数据都是外界传递过来的,只读数据;
 *   4.2 state/data中的数据都是私有的；(如通过ajax获取的数据)
 *   
 * 
 */