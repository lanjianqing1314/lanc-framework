# 1. 应用架构图
![DDD分层架构.png](DDD%E5%88%86%E5%B1%82%E6%9E%B6%E6%9E%84.png)
# 2. 架构目录

```txt
lanc-framework
│  .gitignore
│  DDD分层架构.eddx
│  DDD分层架构.png
│  list.txt
│  pom.xml
│  reade.md
│         
├─ddd-mongodb  #DDD防腐层
│  │  pom.xml
│  │  
│  └─src
│      └─main
│          └─java
│              └─com
│                  └─lanc
│                      └─mongodb
│                          ├─po
│                          │      DemoMongo.java
│                          │      
│                          ├─repository
│                          │      DemoRepo.java
│                          │      
│                          └─service
│                                  MongoDemoServiceImpl.java
│                                  
├─ddd-mybatis  #DDD防腐层
│  │  pom.xml
│  │  
│  └─src
│      └─main
│          └─java
│              └─com
│                  └─lanc
│                      └─mysql
│                          └─ibatis
│                              ├─mapper
│                              │      DemoMapper.java
│                              │      
│                              ├─po
│                              │      DemoIbatis.java
│                              │      
│                              └─service
│                                      MybatisDemoServiceImpl.java
│                                      
├─local-app  #用户应用层
│  │  pom.xml
│  │  
│  └─src
│      └─main
│          └─java
│              └─com
│                  └─lanc
│                      └─app
│                          ├─controller
│                          │      DemoController.java
│                          │      
│                          └─service
│                              │  DemoServiceImpl.java
│                              │  
│                              └─plus
│                                  │  DemoServicePlus.java
│                                  │  
│                                  └─validated
│                                          DemoValidated.java
│                                          
├─local-domain  #领域层
│  │  pom.xml
│  │  
│  └─src
│      └─main
│          └─java
│              └─com
│                  └─lanc
│                      ├─business
│                      │  ├─exec
│                      │  │      DemoEvent.java
│                      │  │      DemoListener.java
│                      │  │      
│                      │  └─service
│                      │          DemoService.java
│                      │          MongoDemoService.java
│                      │          MybatisDemoService.java
│                      │          
│                      └─domain
│                          ├─dos
│                          │      DemoDO.java
│                          │      
│                          ├─dto
│                          │      DemoDTO.java
│                          │      
│                          ├─po
│                          │      Demo.java
│                          │      
│                          └─vo
│                                  DemoVO.java
│                                  
└─local-start  #用户界面/工程start入口
    │  pom.xml
    │  
    └─src
        ├─main
        │  ├─docker
        │  │  │  cicd.sh
        │  │  │  dev-cicd.sh
        │  │  │  run.sh
        │  │  │  
        │  │  ├─dev
        │  │  │      config.sh
        │  │  │      
        │  │  ├─prod
        │  │  │      config.sh
        │  │  │      
        │  │  ├─sit
        │  │  │      config.sh
        │  │  │      
        │  │  └─uat
        │  │          config.sh
        │  │          
        │  ├─java
        │  │  └─com
        │  │      └─lanc
        │  │              LancFrameworkApplication.java
        │  │              
        │  └─resources
        │      │  bootstrap-dev.yml
        │      │  bootstrap-prod.yml
        │      │  bootstrap-sit.yml
        │      │  bootstrap-uat.yml
        │      │  bootstrap.yml
        │      │  logback-spring.xml
        │      │  
        │      └─mapper
        │              DemoMapper.xml
        │              
        └─test #单元测试
            ├─java
            │  └─com
            │      └─lanc
            │              BaseTest.java
            │              DemoServiceTest.java
            │              
            └─resources
                └─sql
                        clean.sql
                        create.sql
                     
```
# 3. 架构简介

架构这个词源于英文里的“Architecture“，源头是土木工程里的“建筑”和“结构”，而架构里的”架“同时又包含了”架子“（scaffolding）的含义，意指能快速搭建起来的固定结构。而今天的应用架构，意指软件系统中固定不变的代码结构、设计模式、规范和组件间的通信方式。在应用开发中架构之所以是最重要的第一步，因为一个好的架构能让系统安全、稳定、快速迭代。在一个团队内通过规定一个固定的架构设计，可以让团队内能力参差不齐的同学们都能有一个统一的开发规范，降低沟通成本，提升效率和代码质量。

在做架构设计时，一个好的架构应该需要实现以下几个目标：

## 3.1 独立于框架
架构不应该依赖某个外部的库或框架，不应该被框架的结构所束缚。

## 3.2 独立于UI
前台展示的样式可能会随时发生变化（今天可能是网页、明天可能变成console、后天是独立app），但是底层架构不应该随之而变化。

## 3.3 独立于底层数据源
无论今天你用MySQL、Oracle还是MongoDB、CouchDB，甚至使用文件系统，软件架构不应该因为不同的底层数据储存方式而产生巨大改变。

## 3.4 可测试
无论外部依赖了什么数据库、硬件、UI或者服务，业务的逻辑应该都能够快速被验证正确性。

## 3.5 独立于外部依赖
无论外部依赖如何变更、升级，业务的核心逻辑不应该随之而大幅变化。

这就好像是建筑中的楼宇，一个好的楼宇，无论内部承载了什么人、有什么样的活动、还是外部有什么风雨，一栋楼都应该屹立不倒，而且可以确保它不会倒。但是今天我们在做业务研发时，更多的会去关注一些宏观的架构，比如SOA架构、微服务架构，而忽略了应用内部的架构设计，很容易导致代码逻辑混乱，很难维护，容易产生bug而且很难发现。

## 3.6 可维护性

一个应用最大的成本一般都不是来自于开发阶段，而是应用整个生命周期的总维护成本，所以代码的可维护性代表了最终成本。

可维护性 = 当依赖变化时，有多少代码需要随之改变。

## 3.7 可拓展性

事务脚本式代码的第二大缺陷是：虽然写单个用例的代码非常高效简单，但是当用例多起来时，其扩展性会变得越来越差。

可扩展性 = 做新需求或改逻辑时，需要新增/修改多少代码。

## 3.8 可测试性

除了部分工具类、框架类和中间件类的代码有比较高的测试覆盖之外，我们在日常工作中很难看到业务代码有比较好的测试覆盖，而绝大部分的上线前的测试属于人肉的“集成测试”。低测试率导致我们对代码质量很难有把控，容易错过边界条件，异常case只有线上爆发了才被动发现。而低测试覆盖率的主要原因是业务代码的可测试性比较差。

可测试性 = 运行每个测试用例所花费的时间 * 每个需求所需要增加的测试用例数量。

# 4. 总结
DDD不是一个什么特殊的架构，而是任何传统代码经过合理的重构之后最终一定会抵达的终点。DDD的架构能够有效的解决传统架构中的问题。

## 4.1 高可维护性
当外部依赖变更时，内部代码只用变更跟外部对接的模块，其他业务逻辑不变。

## 4.2 高可扩展性
做新功能时，绝大部分的代码都能复用，仅需要增加核心业务逻辑即可。

## 4.3 高可测试性
每个拆分出来的模块都符合单一性原则，绝大部分不依赖框架，可以快速的单元测试，做到100%覆盖。
代码结构清晰：通过POM module可以解决模块间的依赖关系， 所有外接模块都可以单独独立成Jar包被复用。当团队形成规范后，可以快速的定位到相关代码。


