图书管理系统项目报告
====

###第一部分：设计说明
1. **设计目标**

  设计并实现一个基于Java语言的图书管理系统，系统分为读者与管理者两部分，读者可以查询图书信息、查看本人信息、修改本人信息、借阅图书等功能，管理员可以执行图书入库、删除、查询图书、查询读者、还书、挂失、管理借书证等功能。

2. **开发工具与环境**

  + macOs 10.13.1
  + MySQL Version 14.14
  + Sequel Pro
  + IntelliJ IDEA

###第二部分：数据库设计
1.  **数据字典以及表结构**
  数据库中建立五个表，分别为admin、book\_info、lend\_list、reader\_card、reader\_info.

  admin表：
  ![](png/admin.png)

  | 字段名      | 类型          | 说明                  |
  | -------- | ----------- | ------------------- |
  | admin_id | varchar(15) | 管理员id（Not Null， PK） |
  | password | varchar(15) | 管理员密码               |

  book_info表：
  
  ![](png/book_info.png)

  | 字段名       |       类型       |        说明         |
  | --------- | :------------: | :---------------: |
  | book_id   |  varchar(20)   | 图书id（PK，Not Null） |
  | name      |  varchar(20)   |   书名（Not Null）    |
  | author    |  varchar(50)   |  图书作者（Not Null）   |
  | publish   |  varchar(50)   |  出版社信息（Not Null）  |
  | ISBN      |  varchar(30)   |  ISBN（Not Null）   |
  | language  |  varchar(13)   |  图书语言（Not Null）   |
  | price     | decimal(10, 2) |   价格（Not Null）    |
  | pubdate   |      date      |       出版日期        |
  | class_id  |      int       |       图书类型        |
  | pressmark |      int       |     图书所在书架编号      |
  | state     |      int       |       图书状态*       |
  | link      |  varchar(45)   |       豆瓣链接        |

  ​

  ​

  （*）state为1代表图书在馆中，state为0代表图书已被借出，state为-1代表图书已挂失。

  ​

  ​	对于一般的字段，我都使用了varchar类型。出版日期设置为date类型，一般的数字int类型足够使用，价格使用decimal类型来保证精度并且限定范围。

  ​

  lend_list表：

  ![](png/lend_list.png)

  | 字段名       | 类型          | 说明                   |
  | --------- | ----------- | -------------------- |
  | sernum    | int         | 借阅信息序列号（Not Null，PK） |
  | book_id   | varchar(20) | 图书id（Not Null）       |
  | reader_id | varchar(11) | 借阅者id（Not Null）      |
  | lend_date | date        | 借阅日期                 |
  | back_date | date        | 归还日期                 |
  | deadline  | varchar(20) | 归还期限*                |

  （*）归还期限根据读者的借阅卡级别确定。

  reader_card表

  ![](png/reader_card.png)

  | 字段名        | 类型          | 说明                    |
  | ---------- | ----------- | --------------------- |
  | reader_id  | varchar(11) | 读者id（Not Null，PK）     |
  | name       | varchar(16) | 姓名（Not Null）          |
  | passwd     | varchar(15) | 密码（Not Null）默认为111111 |
  | card_state | int         | 借阅证类型* 默认为1           |

  （*）借阅卡状态为1时，读者可以借阅五本书，期限为一个月；借阅卡状态为2时，读者可以借阅十本书，期限为两个月，当借阅卡状态为3时，读者可借阅十五本书，期限为三个月。

  reader_info表：

  ![](png/reader_info.png)

  | 字段名       | 类型          | 说明                |
  | --------- | ----------- | ----------------- |
  | reader_id | varchar(11) | 读者id（PK，Not Null） |
  | name      | varchar(16) | 读者姓名（Not Null）    |
  | gender    | varchar(2)  | 性别                |
  | birth     | date        | 读者生日              |
  | address   | varchar(50) | 读者住址              |
  | telcode   | varchar(11) | 读者电话              |

  该表记录了读者信息。

2. **E-R图**

###   第三部分：系统设计

1. **程序架构**

   本管理系统使用Spring MVC框架开发，采用Java语言。

   文件结构如下：

   ![](png/structure.png)



其中，DAO类被上层的业务类调用，负责数据的访问和操作，在这里使用Spring JDBC作为持久层的实现技术。Domain类是实体类，代表了业务状态，贯穿展现层、业务层、持久层，最终被持久化到数据库中。业务层将持久层与展现层连接起来。展现层为程序提供界面。

前端部分使用了bootstrap框架。

2. **系统组织架构图**

   ![](png/system.png)

3. **使用JdbcTemplate连接数据库**

   JdbcTemplate是Spring MVC内置的对JDBC的一个封装。

   **JdbcTemplate主要提供以下五类方法：**

   - execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句；

   - update方法及batchUpdate方法：update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批处理相关语句；

   - query方法及queryForXXX方法：用于执行查询相关语句；

   - call方法：用于执行存储过程、函数相关语句。

   **使用JdbcTemplate的步骤：**

   + 配置数据源以及JdbcTemplate实例

     ``` xml
         <!-- 配置数据源 -->
         <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
               destroy-method="close"
               p:driverClassName="com.mysql.jdbc.Driver"
               p:url="jdbc:mysql://localhost:3306/library"
               p:username="root"
               p:password="971127" />

         <!-- 配置Jdbc模板  -->
         <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate"
               p:dataSource-ref="dataSource" />
     ```

   + 初始化一个JdbcTemplate对象并且使用@AutoWired注解装配

     ```java

     @Repository
     public class TestDao {
         private JdbcTemplate jdbcTemplate;
        
       @Autowired
         public void setJdbcTemplate (JdbcTemplate jdbcTemplate) {
             this.jdbcTemplate = jdbcTemplate;
         }
     }
     ```

   + 使用对应的方法进行数据库操作

     ```java
     //例如：
     //统计符合条件管理员账号个数
     	private final static String COUNT_ADMIN_SQL = "select count(*) from admin where admin_id = ? and password = ?";
         return jdbcTemplate.queryForObject(COUNT_ADMIN_SQL, new Object[] {admin_id, admin_pwd}, Integer.class);
     //挂失图书
         private final static String REPORT_BOOK_LOSS = "update book_info set state = -1 where book_id = ?";
         jdbcTemplate.update(REPORT_BOOK_LOSS, book_id);
     ```

4. **@RequestMapping注解**

   通过标注在方法上的@RequestMapping注解，实现对该方法的访问；

   value值表示访问该方法的URL地址，method值指定了处理方法可处理的http method类型的请求，将method设置为POST以过滤用户对此页面的直接访问。

5. **系统界面**

   + 用户

     * 登录界面

       ![](png/SignIn.png)

     * 注册界面

       ![](png/SignUp.png)

     * 用户主页

       ![](png/UserMain.png)

     * 查看图书详细信息

       ![](png/UserBookDetail.png)

     * 查看个人信息

       ![](png/UserInfo.png)

     * 修改个人信息

       ![](png/UserModifyInfo.png)

     * 借阅列表

       ![](png/UserLendList.png)

     * 修改密码

       ![](png/UserChangePwd.png)

     * 查询图书

       ![](png/UserQuery.png)

     * 查询结果展示

       ![](png/UserQueryResult.png)

   + 管理员

     * 管理员主页

       ![](png/AdminMain.png)

     * 查询图书

       ![](png/AdminQueryBook.png)

     * 查询图书结果

       ![](png/AdminQueryBookResult.png)

     * 查询读者

       ![](png/AdminQueryReader.png)

     * 查询读者结果

       ![](png/AdminQueryReaderResult.png)

     * 当前借阅列表

       ![](png/AdminLendList.png)

     * 当前读者列表

       ![](png/AdminReaderList.png)

     * 添加图书

       ![](png/AdminAddBook.png)

     * 添加借阅证

       ![](png/AdminAddReader.png)

6. **系统详细设计**

   * 登录模块

     * 流程图

       ![](png/Login.png)

     * 功能实现

       登录界面整合了普通用户的登录以及管理员的登录，通过表单提交的方式获取前端用户或者管理员输入的id以及密码，分别在管理员表和读者表中筛选是否有匹配项，优先匹配管理员，若存在匹配项，则返回对应的界面，并将用户或者管理员信息设置到session中；若不存在匹配项，则弹出提示框。

   * 注册模块

     * 流程图

       ![](png/ReaderSignUp.png)

     * 功能实现

       注册界面通过登录界面上的“create new account”进入，只能够注册读者信息，

       注册过程中会检测用户是否重复以及两次密码输入是否相同，注册成功后跳转至登录界面。

   * 读者借阅模块

     * 流程图

       ![](png/lendBook.png)

     * 功能实现

       通过图书的state属性判断该图书是否可被借阅，若不能借阅则跳转至图书列表，若能够借阅，执行节约操作，首先update图书的state属性，读者借阅图书数量加一，并记录当前日期，根据读者借阅卡属性计算出还书期限，将这些信息展示在读者的借阅列表中。

   * 还书模块

     * 流程图

       ![](png/ReturnBook.png)

     * 功能实现

       还书过程必须由管理员经手，因此还书功能放在了管理员模块中，管理员可以通过借阅列表或者查询借阅信息来归还指定的图书。

       在执行还书的操作过程中，首先会判断该图书是否被借阅，接着会更新还书日期、图书状态、对应读者的借阅图书数量。

   * 图书入库

     * 流程图

       ![](png/AddBook.png)

     * 功能实现

       图书入库也是管理员模块的功能。

       执行图书入库功能时，输入详细的图书信息，确保图书的id不重复，即可实现新书入库的功能，数据库中会添加一条该书的信息记录。

   * 管理借阅证

     * 功能实现

       借书证管理分为两部分，一部分是添加借阅证，另一部分是管理现有的借阅证。

       添加借阅证时，只需要输入读者信息即可；

       管理部分可以查看当前所有借阅证的信息，包括id、姓名、当前借阅图书数量、当前借阅证级别等，还可以挂失借阅证。


### 第四部分：使用手册

* **通过IntelliJ IDEA导入本项目**

  选择Project from Existing Sources

  ![](png/Maven.png)

  ![](png/import1.png)

  指定根目录

  ![](png/import2.png)

  ![](png/import3.png)

  ![](png/import4.png)

  ![](png/import5.png)

* **配置jetty**

  在pom.xml中根据自己的情况配置jetty

  ```xml
  <plugins>
              <!-- jetty插件 -->
              <plugin>
                  <groupId>org.mortbay.jetty</groupId>
                  <artifactId>maven-jetty-plugin</artifactId>
                  <version>6.1.25</version>
                  <configuration>
                      <connectors>
                          <connector implementation="org.mortbay.jetty.nio.SelectChannelConnector">
                              <port>8000</port> 
                              <maxIdleTime>60000</maxIdleTime>
                          </connector>
                      </connectors>
                      <contextPath>/lib</contextPath>
                      <scanIntervalSeconds>0</scanIntervalSeconds>
                  </configuration>
              </plugin>
          </plugins>
  ```

  ​

  ​

* **运行**

  在Maven Projects选项卡中选择jetty插件run选项，运行该项目，在浏览器地址栏输入

  localhost:8000/lib/index.html，即可访问登录界面。



