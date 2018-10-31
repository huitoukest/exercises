import React from 'react' //创建爱你组件，虚拟dom元素，生命周期
//1.第一种创建组件的方式，可以直接把组件以标签的形式使用
//构造函数特点，首字母大写
//创建并导出这个组件
export default function Hello(props){
    //1.如果在一个组件中return null，则表示此组件什么都不渲染
    //return null

    //2.在组建中，必须返回一个合法的JSX虚拟DOM元素
    return <div> 这是一个Hello组件 -- {props.name} </div>
    
    //注意，这里的props参数是只读属性，即内部无法更改外部传入的属性
    //无论是vue还是React，组建中的props都是只读的，无法修改。
}


//把组件暴露出去
// export default Hello