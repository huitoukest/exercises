import React from 'react'
import ReactDOM from 'react-dom'
import CmtItem from '@/components/CmtItem.jsx'
//import CmtItem from './components/CmtItem.jsx'

export default class Cmt extends React.Component{

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
                {/**注意：在jsx中不能够直接写字符串格式的行内样式，必须以js对象的形式来书写 
                 * 在行内js的样式中：数值类型可以不用引号包裹，字符串类型必须用字符串包裹
                */}
                <p style={ {color: 'red',fontSize:'35px',textAlign:'center'} }>评论列表组件</p>
                {
                    this.state.cmList.map(item => 
                        <CmtItem {...item}  key= {item.id}></CmtItem>
                    )
                }
        </div>
    }
}