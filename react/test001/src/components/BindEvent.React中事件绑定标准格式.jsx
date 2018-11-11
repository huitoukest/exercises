import React from 'react'

export default class BindEvent extends React.Component{

    constructor(){
        super()
        this.state = {
            msg: '哈哈哈哈',
            msg2: '123456'
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
            <br /><br />
            {/**注意：在React中，如果想为state中的数据重新复制，不能直接通过this.state.xxx的方式赋值修改
                而应该通过React提供的this.setState({msg:'123'})这种方式来调用
            */}
            <h3>{this.state.msg}</h3>
            <button onClick={ () => this.change("11111")}>改变值</button>
            
        </div>
    }

    myClickHandler(){
        console.log("按钮2")
    }

    say = (props) => {
        console.log(props)
    }

    show = () =>{
        console.log("调用了show方法")
    }

    change = (args1) =>{
        //在React中，推荐使用this.setState({})来修改状态的值。
        //同时，在setState中只会把对应的state状态更新，而不会覆盖和修改其他的状态值。（即这里只会更新msg的数据，其余的属性值不会被修改）
        this.setState({
            msg:args1
        });
        console.log(this.state)
        //注意，由于这里的setState方法时异步的，所以这里第一次console.log的值可能还是之前的值。
        //而如果这里需要立即拿到返回的值，则需要通过传入setSate的回调得到值。
        //this.setState({},function(){这里获取的this.state才是最新的值}；
        
    }
}