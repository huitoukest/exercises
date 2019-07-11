//这里模拟定义了一个jquery的typescript的定义
//常见的类型定义文件基本都可以在网上找到。
//或者使用npm安装typings工具来管理和下载常见的定义文件
declare module "jquery"{
    export = $;
}

declare let Jquery: (param: any) => string;
declare let $:typeof Jquery;