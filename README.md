# mms-elasticsearch-poc
Repo for elasticsearch poc for media-markt-saturn

fart -i -r . userName actualUserName

fart -i -r . password actualUserPassword

vagrant up devvm


next step is to create the product data files. load the elastic-client project into your ide. And run the /elastic-client/src/main/java/org/example/products/Test.java class. Make sure you have correct path to the big xml file. 

docker-compose up -d --build 
docker exec gs-api "node" "server/load_data.js"
