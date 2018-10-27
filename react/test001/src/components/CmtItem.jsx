import React from 'react'
import ReactDOM from 'react-dom'

const borderStyle = {
    style : {
        border: '1px dashed  #ccc',
        margin: '10px',
        padding: '10px',
        boxShadow: '0 0 10px #ccc'
    }
}

const userStyle = {
        fontSize: '14px'
}

//抽离为单独的样式文件/模块
import styles from '@/components/styles.js'

console.info(styles)

//使用function定义普通的的无状态组件
function CmtItem(props){
    return <div {...borderStyle}> 
                <h2 style = {styles.user}> {props.user}</h2>
                <p>  {props.content}</p>
            </div>
}
export default CmtItem