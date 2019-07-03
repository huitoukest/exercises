//默认参数,默认参数只能是按顺序从后往前依次定义默认值
namespace parameter_default{

    function test(a: string ="aa",b: string = "bb",c: string = "cc"){
        console.info(`a=${a},b=${b},c=${c}`)
    }

    test("xxx")
    test("xxx","yy")
    test("xxx","zz")
}