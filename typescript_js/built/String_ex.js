var __makeTemplateObject = (this && this.__makeTemplateObject) || function (cooked, raw) {
    if (Object.defineProperty) { Object.defineProperty(cooked, "raw", { value: raw }); } else { cooked.raw = raw; }
    return cooked;
};
/**
 * 字符串的特性
 */
var stringTest;
(function (stringTest) {
    //模板字符串与多行字符串
    var stringa = "\n    aaa\n      bbb\n    ccc\n    ";
    console.log(stringa);
    var name = "hello " + stringa;
    console.log(name);
    //自动拆分字符串
    function test(template, name, age) {
        console.log(template, name, age);
    }
    var myName = "zhai liang";
    var getAge = function () {
        return 18;
    };
    test(__makeTemplateObject(["hello my name is ", " , i'm ", ""], ["hello my name is ", " , i'm ", ""]), myName, getAge());
})(stringTest || (stringTest = {}));
