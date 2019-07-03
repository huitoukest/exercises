//可选参数，通过?表示参数是否可选
namespace parameter_default{

    function test(a: string ="aa",b?: string,c: string = "cc"){
        console.info(`a=${a},b=${b},c=${c}`)
    }

    test("xxx")
    test("xxx","yy")
    test("xxx","zz")

    var 输出 = `
    a=xxx,b=undefined,c=cc
    a=xxx,b=yy,c=cc
    a=xxx,b=zz,c=cc
    `

}