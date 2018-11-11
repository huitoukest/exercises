import React from 'react'

export default class BindInputValue extends React.Component{

    constructor(){
        super()
        this.state = {
            msg: '哈哈哈哈',
            msg2: '123456'
        }
    }

    render(){
        return <div>
            <h2>BindInputValue组件:</h2>
            <br/>
            {/** 如果我们只是把之设置到input的value上，那么我们得到的文本框实际上只是一个只读的文本框；
                 所以这样的话就需要设置一个readOnly属性或者提供一个onChange函数。
                 不然就会有使用警告。
            */}
            <input type="text" style={{width:'80%',}} value={this.state.msg} onChange={ (e) => this.txtChange(e) }></input>
            <input type="text" style={{width:'80%',}} value={this.state.msg2} onChange={ (e) => this.txtChange2(e) } ref="randomInputId" ></input>
        </div>
    }
    //文本框变化时调用
    txtChange = (e) =>{
        console.log("changed:")
        console.log(e.target)
        this.setState({
            msg:e.target.value
        });
    }

    txtChange2 = () =>{
        console.log(this.refs.randomInputId)
        this.setState({
            msg2:this.refs.randomInputId.value
        });
    }
   
}

/**
 * 注意：
 *   在react中，在页面表单元素绑定了state数据的情况下，当state上的值变化了，
 *  那么此变化会自动同步到页面上。
 *  但是如果UI页面上的表单元素值变化了，那么React不会自动同步此值。
 * 此时，需要程序员手动监听文本框的onChange事件，并在此实践中手动
 * 通过this.setState({})来修改值
 * 
 * 在onChange事件中，获取文本框的值有两种方案：
 * 1.通过事件参数e来获取值
 * 2.使用ref：
 *  在Vue中，为页面上的元素提供了ref的属性，如果想要获取元素的引用，
 *  则需要使用this.$refs.引用名称
 *  而在React中，如果需要获取元素的引用，使用this.refs.引用名称
 */