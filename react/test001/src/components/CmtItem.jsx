import React from 'react'
import ReactDOM from 'react-dom'

//使用function定义普通的的无状态组件
function CmtItem(props){
    return <div > 
                <h2> {props.user}</h2>
                <p>  {props.content}</p>
            </div> 
}
export default CmtItem