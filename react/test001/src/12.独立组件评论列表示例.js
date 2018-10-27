console.log("12.独立组件评论列表示例.js")

import React from 'react'
import ReactDOM from 'react-dom'
import Cmt from '@/components/Cmt.jsx'


const myTest = <div id="myDiv" title="div aaa" > 
                    <Cmt > </Cmt>
                </div>

ReactDOM.render(myTest,document.getElementById('app'))