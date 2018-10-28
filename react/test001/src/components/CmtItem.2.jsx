import React from 'react'

//抽离为单独的样式文件/模块
import styles from '@/components/styles.2.js'
//导入列表组件需要的样式,如果没有安装css的loader则会报错，所以这里需要安装
//cnpm i install style-loader  css-loader -D
//同时在webpack的配置中查找rules来找到匹配的文件格式，用对应的loader来加载
import cmtCss from '@/css/cmt.scss'

console.info(styles)

//空的对象：因为导入的是css文件，此文件没有export导出成员，所以为null
//同时此css中定义属性，在直接导入时，会在当前引入的整个页面生效，因为默认的css是没有作用域的
//到那时在Vue组件的样式表中，冲突的时候可以使用<style scoped><style>来配置作用域。
//注意：在React中，没有类似于scoped这样的指令，因为在React中没有指令的概念。
//在React的css中可以启用css模块化，在webpack的配置中启用
console.info(cmtCss)


//使用function定义普通的的无状态组件
export default function CmtItem2(props){
    return <div style = {styles.item}> 
                <h1 style = {styles.user} id={cmtCss.cmtTile}> {props.user}</h1>
                <p className = {cmtCss.title}>  {props.content}</p>
                
            </div>
}