
 <h1 align="center">deserialization_payload_generator</h1>
	
## 项目简介：
一个简易的基于woodpecker框架开发的反序列化利用插件，支持多种方式生成序列化payload。
除了基本的命令执行外，其中大部分利用链都已支持加载java字节码，少部分支持bcel字节码。
## 提示
### 目前支持的Gadget
`"URLDNS","CC2","CC3","CC4","CC5","CC6","CC7","CC8","CC9","CC11","CCK1","CCK2","CCK3","CCK4","CB1","CB1_183","Hibernate1","Hibernate2","C3P0URLClassLoader","C3P0ELInject","URLDNS","JRMPListener","JRMPClient"`

另外项目也加入了一些特定系统的反序列化漏洞Gadget，如`Hibernate_fr`

（以上Gadget利用链的构造代码均来自互联网大师傅们的技术分享文章）

项目不定期更新，如果师傅们有其他Gadget需求可下载源码先自行添加。如果需要需要其他参数引入可到插件项目入口添加，并在相关Gadget上打上注解即可，项目会自行要求输入必要参数（自我感觉是应该比较方便的，即使是小白也能复制粘贴轻松实现^ω^）

### 保命Tip
目前项目中支持的Gadget可能不是很多，一部分是因为某些Gadget受Jdk版本限制无法兼容到项目中，另一部分纯粹是因为我还没有复现成功...

(^-^)

因为项目是我边学java反序列化边写的，可能对某些Gadget理解不是很深，如果代码实现有误，还请轻喷（手动狗头）


## 使用
### 支持参数
```
Gadget                 使用的利用链
Type                   利用类型，支持command,bytecode,bcel
Command                命令
ResourcePath           需要加载的文件绝对路径
UrlClassLocation       远程恶意类地址
DnsLog                 dnslog地址
Ip                     Ip
Port                   端口
Zip                    压缩方式
Encode                 编码方式
LDAP_or_RMI            LDAP或RMI地址
OutPath                输出文件
```

### 使用简述
#### 1.
设置参数生成payload后输出到指定文件后，burp paste from file即可（切勿直接复制粘贴）
<img width="995" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/008cbdbd-8e26-451c-8ea0-5d9996be85db">

<img width="1189" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/41af914a-3867-4aa9-9818-50644e38d343">

#### 2.
也可配合JMG等其他内存马工具实现内存马注入
<img width="992" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/9970a15c-ce0e-4bd9-99cb-e8dcd9a14cef">
<img width="997" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/1a9c935b-b12b-4b35-a5d7-37834f695eff">

<img width="1288" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/89fa1693-55d0-4326-ae29-c169cba685ae">
<img width="692" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/d5d355a8-321b-4547-ae25-88a96f2c7f4b">

### 其他
诸如脏字符填充、针对TemplatesImpl对象的payload缩小技术等功能，由于实战经验不足，不确定其实际效果，所以暂时没有添加。如果有师傅有这方面的需求，后续可以添加。

### 参考引用
https://github.com/woodpecker-framework/ysoserial-for-woodpecker

https://github.com/Y4er/ysoserial

https://github.com/frohoff/ysoserial

p牛 java安全漫谈系列

https://gv7.me/articles/2021/construct-java-detection-class-deserialization-gadget/

https://su18.org/post/ysoserial-su18-3

https://goodapple.top/archives/1749

https://mp.weixin.qq.com/s/IwhoVrdlxdfVTl-yuiaCPg
