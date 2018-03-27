# backend

  ## Spring Boot + H2 + JPA
    - Spring boot CRUD
    - H2 used (im memory DB)
    - issue coupon by email
    - port 9000 / 실행은 jar로 빌드한 것 cmd에서 run (Springboot-vue 프로젝트 참고)
    - Controller
      - get all list by paging
      - check email --> condition fitted, coupon code return in web + insert db (email, coupon code) 
     


# frontend

> Vue.js project - Give you a coupon!

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
