<h1 align="center">
  Return Order Management Portal
</h1>

[![](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)]()
[![](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)]()
[![](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)]()
[![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)]()
[![](https://img.shields.io/badge/Sass-CC6699?style=for-the-badge&logo=sass&logoColor=white)]()

#### Single Page Application developed with angular and spring boot microservice architecture.

#### Microservice architecture allows you to maximize deployment velocity and application reliability by helping you move at the speed of the market.

<br/>

<p style="color:#ed8144;font-size:24px;"> Table of contents </p>

- [Overview](#overview)

  - [About](#about)
  - [Build details](#build-details)
  - [Try it now](#run-application)
  - [Glimpse of UI](#frontend)

- [Author](#author)

<p style="color:#ed8144;font-size:24px;" id="overview" >Overview</p>

<p style="color:#ed8144;font-size:18px;" id="about">About</p>

Users will be able to:

- Login/SignUp to the portal.
- Place a return request for the product

<p style="color:#ed8144;font-size:18px;" id="build-details">Build details</p>

- Spring boot microservice architecture
- Angular for best user experience
- Eureka discovery server
- Api gateway for centralized routing
- Spring security for auth service
- JWT validation for extra security
- Configuration server to get application properties from external repositories

<p style="color:#ed8144;font-size:18px;" id="run-application"> Run Application </p>

- <p style="font-size:16px" >Clone this repository</p>
    
    ```
    git clone git@git03.iiht.tech:874382/return_order_management_webapp.git
    ```
    - <p style="color:orange">Note: If you face filename too long error run this command</p>

      ```
      git config --system core.longpaths true
      ```

- <p style="font-size:16px" > Import project in your favourite IDE</p>

  - Cd to angular directory and run this command to start the app

    ```
    npm install

    ng serve
    ```

    Angular application will launch on [localhost](https:localhost//4200)

  - Cd to spring application directory and start each application from IDE

    - can also start application from cmd. cd to each directory of the application and run this command. <p style="color:orange">Note: Requires maven in local machine. [Guide to install maven](https://maven.apache.org/install.html)</p>

    ```
    mvn spring-boot:run
    ```

    <h4 align="center" style="font-size:20px"> OR</h4>

  - Run the executable jar using this command.

    ```
    java -jar <JAR_FILE_NAME>
    ```

      <p style="color:orange">Note: Run the 
        discovery-server-service-executable.jar before running the other services</p>

<h4> Now the web application is ready to use.</h4>

<p style="color:#ed8144;font-size:18px;" id="frontend">Frontend</p>

![Login](UI/login.png)
![Signup](UI/signup.png)
![main-portal](UI/return-order-portal.png)
![ordered](UI/ordered.png)

<hr>

<p style="color:#ed8144;font-size:18px;" id="author">Author</p>

- Name : [Vigneshwaraa K](http://git03.iiht.tech/874382)
