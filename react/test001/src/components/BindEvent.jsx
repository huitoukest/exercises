import React from 'react'

export default class BindEvent extends React.Component{

    constructor(){
        super()
        this.state = {

        }
    }



    render(){
        return <div>
            BindEvent组件
            <hr ></hr>
            {/* 在React中有一套自己的事件绑定机制,c采用小驼峰原则 */}
            <button onClick={
                function(){
                    console.log('hello')
                }
            }>按钮，点我</button>
            <br /><br />
            <button onClick={this.myClickHandler}>按钮2，点我</button>

            {/*这里的() => xx 就表示一个lambda匿名函数
             注意：箭头函数中的this不是此匿名函数自身，而是指向其上一层的this；
             普通的函数中的this就是指的是当前的实例。这里用箭头函数的好处就是this的作用域的问题（作用域确定，this不会发生指向问题）；
            */}
            <br /><br />
            <button onClick={ () => this.say("按钮3")}>按钮3，点我</button>
            
        </div>
    }

    myClickHandler(){
        console.log("按钮2")
    }

    say = (props) => {
        console.log(props)
    }
}