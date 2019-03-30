# [![Fork me on Gitee](https://gitee.com/owenwangwen/open-capacity-platform/widgets/widget_5.svg)](https://gitee.com/owenwangwen/open-capacity-platform)open-capacity-platform 微服务能力开发平台 
[![star](https://gitee.com/owenwangwen/open-capacity-platform/badge/star.svg?theme=white)](https://gitee.com/owenwangwen/open-capacity-platform/stargazers)
[![Fork me on Gitee](https://gitee.com/owenwangwen/open-capacity-platform/widgets/widget_6.svg)](https://gitee.com/owenwangwen/open-capacity-platform)
[![fork](https://gitee.com/owenwangwen/open-capacity-platform/badge/fork.svg?theme=white)](https://gitee.com/owenwangwen/open-capacity-platform/members)

简称ocp是基于layui+springcloud的企业级微服务框架(用户权限管理，配置中心管理，应用管理，....),其核心的设计目标是分离前后端，快速开发部署，学习简单，功能强大，提供快速接入核心接口能力，其目标是帮助企业搭建一套类似百度能力开放平台的框架；



# **项目地址** #
http://59.110.164.254:8066/login.html 用户名/密码：admin/admin

# 技术介绍  #


<table>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0325/201938_48f25b7d_869801.png "01.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0325/201950_44da7587_869801.png "02.png"/></td>
		<td><img src="https://images.gitee.com/uploads/images/2019/0325/202000_dad634f9_869801.png "03.png"/></td>
    </tr>
</table>

# 技术文档 #
[试读](https://www.kancloud.cn/owenwangwen/open-capacity-platform/content)
[正式文档](https://www.kancloud.cn/owenwangwen/open-capacity-platform) 


### 欢迎进群（群内领资料）

`一键加群`
<a target="_blank" href="https://jq.qq.com/?_wv=1027&k=5JSjd5D"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="open-capacity-platform交流" title="open-capacity-platform交流"></a>        

<table>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0330/111355_2a8db09a_869801.png "屏幕截图.png"/></td>
        
    </tr>
</table>

 
# 开发环境   #
redis3.X  
jdk1.8  
MySQL Server 5.6  
maven3.3.9  
sts-3.8.0.RELEASE  

 

# 项目组织结构分析   #
![输入图片说明](https://images.gitee.com/uploads/images/2019/0325/202632_d6e2e2ad_869801.png "屏幕截图.png")


1.下载代码

```
 git clone  https://gitee.com/owenwangwen/open-capacity-platform.git
```

2.启动对应的服务

a.先启动 register-center 注册中心的 eureka-server 注册服务

b.在启动 api-gateway 网关服务

c.再启动 oauth-center 认证中心 oauth-server 认证服务

d.在启动 business-center 业务中心的 对应服务 file-center user-center back-center

e.启动 monitor-center 监控中心 admin-server zipkin-center

## 一. open-capacity-platform能力开放平台管理    

<table>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0330/112405_4b826028_869801.png "屏幕截图.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0110/231916_74fcbc85_1441068.png"/></td>
    </tr>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0110/231924_0ab3f997_1441068.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0110/231924_0ab3f997_1441068.png"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0110/231923_4e42ff5d_1441068.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0329/212209_2ba53e32_869801.png "服务治理.png"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0126/125449_9b960f05_1147840.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0126/125449_baa02383_1147840.png"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0110/231932_6e2ce5f5_1441068.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0126/125449_7a3dec37_1147840.png"/></td>
    </tr>
    <tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0329/212515_6b74c76a_869801.png "屏幕截图.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0329/212356_27ecb030_869801.png "111.png"/></td>
    </tr>
     
</table>



 
# 容器化部署   


<table>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0126/125453_6682dba8_1147840.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0126/125453_3831567a_1147840.png"/></td>
    </tr>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0126/125454_b04fbc0d_1147840.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0126/125454_1f9ce4e8_1147840.png"/></td>
    </tr>
     
</table>
 

# api-gateway vs new-api-gateway #
内网测试网关/api-user/users/current
## zuul-->api-gateway ##

<table>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0327/095846_3bf37b11_869801.png "zuul01.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0327/095928_f0e7d48e_869801.png "zuul02.png"/></td>
    </tr>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0327/095938_bc868c69_869801.png "zuul03.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0327/095946_a0c5542c_869801.png "zuul04.png"/></td>
    </tr>
     
</table>
 
## spring cloud gateway -->new-api-gateway ##


<table>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0327/095957_d3cbdeb3_869801.png "gateway01.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0327/100005_d7861ff6_869801.png "gateway02.png"/></td>
    </tr>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0327/100014_8528653c_869801.png "gateway03.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0327/100021_7933cefd_869801.png "gateway04.png"/></td>
    </tr>
     
</table> 

#  灰度发布功能演示   
 
ocp灰度发布功能(参考dev分支) 
a.先启动 register-center 注册中心的 eureka-server 注册服务  
b.在启动 api-gateway 网关服务 
c.再启动 oauth-center 认证中心 oauth-server 认证服务 
d.在启动 business-center 业务中心的 对应服务 user-center 
d.启动gray-center的discovery-console  
e.启动gray-center的discovery-console-desktop    
 
灰度管理UI  
用户名:admin      
密码  :admin  


<table>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0126/125451_c3b6224d_1147840.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0126/125450_b42073c5_1147840.png"/></td>
    </tr>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0126/125450_66e3a8db_1147840.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0126/125451_28b1bc41_1147840.png"/></td>
    </tr>
     
</table>


请参考
[https://github.com/Nepxion/Docs/blob/master/discovery-doc/README_QUICK_START.md](https://github.com/Nepxion/Docs/blob/master/discovery-doc/README_QUICK_START.md)，感谢军哥分享  

#   链路跟踪 
<table>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0330/105610_52def254_869801.png "屏幕截图.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0330/105638_5c7ab9ac_869801.png "屏幕截图.png"/></td>
    </tr>
	<tr>
        <td><img src="https://images.gitee.com/uploads/images/2019/0330/105713_c9c94365_869801.png "屏幕截图.png"/></td>
        <td><img src="https://images.gitee.com/uploads/images/2019/0330/105736_ac478159_869801.png "屏幕截图.png"/></td>
    </tr>
     
</table>

