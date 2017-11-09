##JSW 如何应用
一般是在请求头里加入Authorization，并加上Bearer标注：
``` javascript
    fetch('api/user/1', {
      headers: {
        'Authorization': 'Bearer ' + token
      }
    })
```