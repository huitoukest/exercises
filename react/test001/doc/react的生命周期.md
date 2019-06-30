# react的生命周期(2018年)

# 生命周期函数说明

## 组件创建阶段
特点是，只创建一次
1. componetWillMount
2. render
3. componentDidMount

## 组件的运行阶段
根据props属性或者state状态的改变，有选择性的执行0到多次
1. componentWillReceiveProps
2. shouldComponentUpdate
3. componentWillUpdate
4. render
5. componentDidUpdate

##  组件销毁阶段
只执行一次
1. componentWillUnmount