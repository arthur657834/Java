JSON Web Token（JWT）


https://your.awesome-app.com/make-friend/?from_user=B&target_user=A
上面的URL主要通过URL来描述这个当然这样做有一个弊端，那就是要求用户B用户是一定要先登录的。可不可以简化这个流程，让B用户不用登录就可以完成这个操作。JWT就允许我们做到这点。

一个JWT实际上就是一个字符串，它由三部分组成，头部、载荷与签名。

载荷（Payload）:
```json
{
    "iss": "John Wu JWT",
    "iat": 1441593502,
    "exp": 1441594722,
    "aud": "www.example.com",
    "sub": "jrocket@example.com",
    "from_user": "B",
    "target_user": "A"
}
```
iss: 该JWT的签发者
sub: 该JWT所面向的用户
aud: 接收该JWT的一方
exp(expires): 什么时候过期，这里是一个Unix时间戳
iat(issued at): 在什么时候签发的

将上面的JSON对象进行[base64编码]可以得到下面的字符串。这个字符串我们将它称作JWT的Payload（载荷）。
eyJpc3MiOiJKb2huIFd1IEpXVCIsImlhdCI6MTQ0MTU5MzUwMiwiZXhwIjoxNDQxNTk0NzIyLCJhdWQiOiJ3d3cuZXhhbXBsZS5jb20iLCJzdWIiOiJqcm9ja2V0QGV4YW1wbGUuY29tIiwiZnJvbV91c2VyIjoiQiIsInRhcmdldF91c2VyIjoiQSJ9

头部（Header）:
```json
{
  "typ": "JWT",
  "alg": "HS256"
}
```
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9

签名（签名）:
将上面的两个编码后的字符串都用句号.连接在一起（头部在前），就形成了
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcm9tX3VzZXIiOiJCIiwidGFyZ2V0X3VzZXIiOiJBIn0

最后，我们将上面拼接完的字符串用HS256算法进行加密。在加密的时候，我们还需要提供一个密钥（secret）。如果我们用mystar作为密钥的话，那么就可以得到我们加密后的内容
rSWamyAYwuHCo7IFAgd1oRpSP7nzL7BF5t7ItqpKViM

最后将这一部分签名也拼接在被签名的字符串后面，我们就得到了完整的JWT
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcm9tX3VzZXIiOiJCIiwidGFyZ2V0X3VzZXIiOiJBIn0.rSWamyAYwuHCo7IFAgd1oRpSP7nzL7BF5t7ItqpKViM

https://your.awesome-app.com/make-friend/?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcm9tX3VzZXIiOiJCIiwidGFyZ2V0X3VzZXIiOiJBIn0.rSWamyAYwuHCo7IFAgd1oRpSP7nzL7BF5t7ItqpKViM
