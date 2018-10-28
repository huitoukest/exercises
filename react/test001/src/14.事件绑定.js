console.log("14.事件绑定.js")

import React from 'react'
import ReactDOM from 'react-dom'
import BindEvent from '@/components/BindEvent.jsx'


const myTest = <div id="myDiv" title="div aaa" > 
                    <BindEvent > </BindEvent>
                </div>

ReactDOM.render(myTest,document.getElementById('app'))