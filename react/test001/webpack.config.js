
//导入插件
const path = require('path')
const HtmlWebPackPlugin = require('html-webpack-plugin')

//创建一个插件的实例

//webpack中将文件映射到内存的插件
const htmlPlugin = new HtmlWebPackPlugin({
      template:  path.join(__dirname,'./src/index.html') ,//源文件
      filename:  'index.html' //生成的内存中首页的名称
});

// 向外部暴露一个打包的配置对象；node语法，因为webpack是基于Node构建的，所以
//webpack支持所有node api与之语法
module.exports = {
      mode: 'development' ,//development or production
      //在webpack 4.x中，默认的打包入口路径是src -> index.js
      plugins:[
            htmlPlugin
      ]
} 