# webpack配置

1. css的loader模块化中自动生成的名称叫做localIdentName，而localIdentName可以自定义一些生成规则(参数)：

   1. \[path\] 表示样式表相对于项目根目录所在路径
   2. \[name\] 表示样式表文件名称
   3. \[local\] 表示样式表的类名(className，即样式选择器的名称)定义名称
   4. \[hash:length\] 表示32位的hash值中前length位的值，一般取前5-6位长度

2. 例子
```
例如： { test: /\.css$/ ,use: ['style-loader','css-loader?modules&localIdentName=[path]
[name]-[local]-[hash:5] ']} 
生成的名称如：{title: "src-css-cmt-title-a7087", cmtTile: "src-css-cmt-cmtTile-9a6f7"}
注意：[path]生成的名称后自带一个中划线
```

3. 模块化

默认css样式是使用 :local()包裹起来的，默认全部都会模块化；
使用 :global(你的类名) 来配置,则不会被模块化，全局生效

4. 手动安装新的css

比如这里安装一个bootstrap的css：
```
cnpm i bootstrap -S   
或者指定版本
cnpm i bootstrap@3.3.7 -S    
```

5. 配置第三方样式表不模块化，自定义的模块化css
一般情况下第三方的样式表都是一.css结尾的，而自己写的css一般使用.scss或者.less结尾，这样可以通过后缀名来区分是否启用模块化；
即不对.css文件模块化，只对自定义的格式进行模块化
同时scss和less也是一种特殊的css格式，需要特定的加载loader
```
{ test: /\.css$/ ,use: ['style-loader','css-loader?modules&localIdentName=[path][name]-[local]-[hash:5]']}
```
修改为
```
{ test: /\.scss|less$/ ,use: ['style-loader','css-loader?modules&localIdentName=[path][name]-[local]-[hash:5]']}
```

安装
```
cnpm i sass-loader node-sass -D
```

配置：


# 使用注意

1. 一次使用多个css样式

   ```
   //使用数组传入多个然后手动join空格构造css字符串
   <h1 className = {[a.title,'test',c.title].join(' ')}>   
   或者
   <h1 className = {a.titl + ' test'}>
   ```
