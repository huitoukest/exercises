console.log("13.css样式表.js")

import React from 'react'
import ReactDOM from 'react-dom'
import Cmt from '@/components/Cmt.2.jsx'


const myTest = <div id="myDiv" title="div aaa" > 
                    <Cmt > </Cmt>
                </div>

ReactDOM.render(myTest,document.getElementById('app'))