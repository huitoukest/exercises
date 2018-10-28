import React from 'react'
import CmtItem2 from '@/components/CmtItem.2.jsx'

//手动导入bootstrap下面的css,默认优先查找node_modules下面的文件夹
//也可以手动配置，但是如果此包已经被安装到了node_modules下面，那么可以直接引入
//import bootcss from 'bootstrap/dist/css/bootstrap.css'

import 'bootstrap/dist/css/bootstrap.css'

//console.info(bootcss)

export default class Cmt2 extends React.Component{

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
                        <CmtItem2 {...item}  key= {item.id}></CmtItem2>
                    )
                }
                
                <button className="btn btn-primary">bootstrap按钮</button>
                <div className="panel panel-primary">bootstrap panel</div>
                {
                    /**
                    如果不配置
                    <button className={[bootcss.btn,bootcss['btn-primary']].join(' ') }>bootstrap按钮</button>
                    */
                }
        </div>
    }
}