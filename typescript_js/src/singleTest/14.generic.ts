//typescript泛型
namespace generic{

    let works:Array<string> = [];
    
    //works.push(1); //会提示报错，因为和泛型定义的类型不符合
    
    works.push("1");
    
}