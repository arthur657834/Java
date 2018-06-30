对象关系映射（英语：(Object Relational Mapping，简称ORM，或O/RM，或O/R mapping），是一种程序技术，用于实现面向对象编程语言里不同类型系统的数据之间的转换 [1]  。从效果上说，它其实是创建了一个可在编程语言里使用的--“虚拟对象数据库”。

对象关系映射（Object-Relational Mapping）提供了概念性的、易于理解的模型化数据的方法。

ORM方法论基于三个核心原则： 
* 简单：以最基本的形式建模数据。 
* 传达性：数据库结构被任何人都能理解的语言文档化。 
* 精确性：基于数据模型创建正确标准化的结构。

优点:
1. 提高了开发效率。由于ORM可以自动对Entity对象与数据库中的Table进行字段与属性的映射，所以我们实际可能已经不需要一个专用的、庞大的数据访问层。 
2. ORM提供了对数据库的映射，不用sql直接编码，能够像操作对象一样从数据库获取数据

缺点: 
1. 牺牲程序的执行效率  
    - 1.1 采用ORM的系统一般都是多层系统，系统的层次多了，效率就会降低
    - 1.2 在对对象做持久化时，ORM一般会持久化所有的属性，有时，这是不希望的
2. 固定思维模式。

Hibernate:
1. Hibernate作为一个全自动框架，sql语句有系统自动生成，程序员不能对sql进行优化
2. 不适用hql的情况下，不能进行定制化sql语句
3. 如果数据库有大量字段，比如说如果只需要查询id，这时候需要全部映射将一百个字段全部查询出来。

![mybatis_lable]{mybatis_lable.png}


${...} 变量替换
#{...} 预编译

1. select/insert/delete

<select id="userList" parameterType="user" resultType="User">
    select * from user where name =#{name}
</select>

parameterType: 传给此语句的参数的全路径名或别名 例:com.test.poso.User或user
resultType: 语句返回值类型或别名。注意，如果是集合，那么这里填写的是集合的泛型，而不是集合本身（resultType 与resultMap 不能并用）
 resultType="java.lang.String"/"java.lang.Map"


2. resultMap
<resultMap id="getStudentRM" type="EStudnet">
  <id property="id" column="ID"/>
  <result property="studentName" column="Name"/>
  <result property="studentAge" column="Age"/>
</resultMap>
<select id="getStudent" resultMap="getStudentRM">
  SELECT ID, Name, Age
    FROM TStudent
</select>

id:该resultMap的标志
type：返回值的类名，此例中返回EStudnet类子标签：
result：用于设置普通字段与领域模型属性的映射关系

3. if
<if test="studentName!=null and studentName!='' ">     
    WHERE studentName=#{studentName}
</if> 

4. foreach
<foreach collection="array" item="id" index="index" open="(" close=")" separator=",">
    #{id}
</foreach>

(1,2,3,4,5)

collection: collection属性的值有三个分别是list、array、map三种，分别对应的参数类型为：List、数组、map集合，我在上面传的参数为数组，所以值为array
item: 表示在迭代过程中每一个元素的别名
index: 表示在迭代过程中每次迭代到的位置（下标）
open: 前缀
close: 后缀
separator: 分隔符，表示迭代时每个元素之间以什么分隔

5. <choose>
<choose>     
    <when test="studentName!=null and studentName!='' ">     
            ST.STUDENT_NAME LIKE CONCAT(CONCAT('%', #{studentName}),'%')      
    </when>
    <otherwise>     
                      
    </otherwise>     
</choose>   

6. <set>
<set>     
    <if test="studentName!=null and studentName!='' ">     
        STUDENT_TBL.STUDENT_NAME = #{studentName},      
    </if>              
</set>

7. <trim> 

<trim prefix="SET" suffixOverrides=","> 
prefixoverride/suffixoverride：去掉第一个/最后一个逗号
prefix/suffix：后缀/前缀　　　　　　

8. <sql>/<include>

<sql id="sqlcont">
　　select count(*)
</sql>

<include refid="sqlcont"/>

9. <association>
一对一
user:
private String id;//主键
private String userName;//用户姓名
private Article article;//新增的文章属性

Article:
private String id;//主键
private String articleTitle;//文章标题
private String articleContent;//文章内容

user xml文件:
<resultMap id="userResultMap" type="test.mybatis.entity.User">
  <id column="id" property="id" jdbcType="VARCHAR" javaType="java.lang.String"/>
  <result column="userName" property="userName" jdbcType="VARCHAR" javaType="java.lang.String"/>
//这里把user的id传过去
   <association property="article" column="id"                       
            select="test.mybatis.dao.articleMapper.selectArticleByUserId" />//test.mybatis.dao.articleMapper为命名空间
<result column="articleContent" property="articleContent" jdbcType="VARCHAR" javaType="java.lang.String"/> //另一种写法
 </resultMap>

Article xml文件:
<select id="selectArticleByUserId"
parameterType="java.lang.String"
resultMap="ArticleResultMap" >
select * from
tb_article where userId=#{userId} </select>

10. <collection>
一对多
private String id;//主键
private String userName;//用户姓名
private List<Article> articleList;

<collection property="articleList" column="id" select="test.mybatis.dao.articleMapper.selectArticleListByUserId" />

11. <![CDATA[ ]]>
在CDATA内部的所有内容都会被解析器忽略("<"字符 <=和"&"字符等)

<select id="getReportData" resultType="java.util.Map"  >
        <![CDATA[
            select * from tabNm t
        ]]>
</select>

查询的结果每一行为List中的一个map，列名为key,结果数据为value



  