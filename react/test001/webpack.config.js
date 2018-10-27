
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
//webpack 默认只能打包处理 .js后缀类型的文件；像其余的.png .vuew等文件类型无法处理，
//所以需要配置一些第三方的loader模块
module.exports = {
      mode: 'development' ,//development or production
      //在webpack 4.x中，默认的打包入口路径是src -> index.js
      plugins:[ //plugins中是配置webpack的插件
            htmlPlugin
      ],
      module:{ //module是配置第三方依赖包和扩展的匹配配置
           rules:[ //第三方匹配规则,这里匹配jsx和js格式的文件使用babel-loader来，同时如果使用到多个loader可以用数组配置
                  // 其次，一定要exclude ，配置排除/node_modules/文件夹下的内容
                 { test: /\.js|\.jsx/ ,use: 'babel-loader' , exclude: /node_modules/ },
                 { test: /\.css$/ ,use: ['style-loader','css-loader']} //打包处理css样式表的第三方loader
           ]

      },
      resolve:{ //extensions:[]补全默认的文件后缀名
            extensions: [".js",".json",".jsx"],
            alias:{ // alias可以配置别名
                  //这里的@符号配置为项目根目录下的src目录
                  '@': path.join(__dirname,'./src')
            }
      }
}