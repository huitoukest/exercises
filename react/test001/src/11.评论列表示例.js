console.log("11.评论列表示例.js")

import React from 'react'
import ReactDOM from 'react-dom'

//使用function定义普通的的无状态组件
function CmtItem(props){
    return <div > 
                <h2> {props.user}</h2>
                <p>  {props.content}</p>
            </div> 
}

class Cmt extends React.Component{

    constructor(){
        //由于基础了父类，首先调用super()，之后就可以使用this关键字
        super()
        this.state = { //这里的this.state={}就相当于Vue中的data(){return{}};
            cmList : [
                {
                    'id': 1,'user': 'user A ' ,'content': "AAA"
                },
                {
                    'id': 2,'user': 'user B' ,'content': "BBB"
                },
                {
                    'id': 3,'user': 'user C' ,'content': "CCCC"
                }
        
            ]
    
        }
    }

    render(){
        return <div>
                <h1>评论列表组件</h1>
                {
                    this.state.cmList.map(item => 
                        <CmtItem {...item}  key= {item.id}></CmtItem>
                    )
                }
        </div>
    }
}

const user = {
    "name":"张三",
    "age":99
}

const myTest = <div id="myDiv" title="div aaa" > 
                    <Cmt {...user}> </Cmt>
                    {/** 这里的组件标签实际上就相当于使其class的一个实例对象，并且默认使用render方法的返回值作为渲染 **/}

                </div>

ReactDOM.render(myTest,document.getElementById('app'))