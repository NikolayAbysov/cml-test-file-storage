# File-Storage
# Table of Contents

* [Project structure](#structure)
* [Start application](#developer-start)

# <a name="structure"></a>Project Structure
* Java 11
* Maven 3.6.0
* maven-checkstyle-plugin 3.1.1
* Spring Boot
* Elastic Search
<hr>

# <a name="developer-start"></a>Start application

0. Elasticsearch should be installed and started locally on port 9200;
    as alternative, docker image can be used: 
    <br>
    <br>
    docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2
    <br>
    <br>
    application is using port 8081 - it should not be blocked.

1. Open the project in your IDE.

2. Run the project.

3. For testing this API you can use Postman or another analogue.
