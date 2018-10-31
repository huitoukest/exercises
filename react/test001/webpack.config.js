
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
                 { test: /\.css$/ ,use: ['style-loader','css-loader']}, //打包处理css样式表的第三方loader
                 //这种loader上可以添加参数,参数的格式和get请求的格式类似:modules参数表示对css启用模块化
                 //{ test: /\.scss|less$/ ,use: ['style-loader','css-loader?modules&localIdentName=[path][name]-[local]-[hash:5]']}, //打包处理css样式表的第三方loader
                 //打包处理scss文件
                 { test: /\.scss$/ ,use: ['style-loader','css-loader?modules&localIdentName=[path][name]-[local]-[hash:5]','sass-loader']},
                 //配置图片的加载
                 { test: /\.jpg|png|gif|bmp$/ ,use: ['url-loader']},
                 //配置字体的打包加载，配置完毕好需要安装: cnpm i url-loader -D
                 //文件加载器：cnpm i file-loader -D
                 { test: /\.ttf|woff|woff2|eot|svg$/ ,use: ['url-loader']},
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


//注意：
/**
 * 1.css的loader模块化中自动生成的名称叫做localIdentName，而localIdentName可以自定义一些生成规则(参数)：
 *    1.1 [path] 表示样式表相对于项目根目录所在路径
 *    1.2 [name] 表示样式表文件名称
 *    1.3 [local] 表示样式表的类名(className，即样式选择器的名称)定义名称
 *    1.4 [hash:length] 表示32位的hash值中前length位的值，一般取前5-6位长度
 * 例如： { test: /\.css$/ ,use: ['style-loader','css-loader?modules&localIdentName=[path]
 * [name]-[local]-[hash:5] ']}   
 * 
 * 生成的名称如：{title: "src-css-cmt-title-a7087", cmtTile: "src-css-cmt-cmtTile-9a6f7"}
 * 注意：[path]生成的名称后自带一个中划线
 * 
 */