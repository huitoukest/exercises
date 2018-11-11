console.log("14.事件绑定.js")

import React from 'react'
import ReactDOM from 'react-dom'
import BindEvent from '@/components/BindEvent.React中事件绑定标准格式.jsx'
import BindInputValue from '@/components/BindInputValue.jsx'


const myTest = <div id="myDiv" title="div aaa" > 
                    <BindEvent > </BindEvent>
                    <br/>
                    <br/>
                    <BindInputValue></BindInputValue>
                </div>

ReactDOM.render(myTest,document.getElementById('app'))