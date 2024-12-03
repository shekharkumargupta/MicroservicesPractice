cd discovery-service
mvn clean package
mvn jib:dockerBuild
cd ..

cd gateway-service
mvn clean package
mvn jib:dockerBuild
cd ..

cd product-service
mvn clean package
mvn jib:dockerBuild
cd ..

cd order-service
mvn clean package
mvn jib:dockerBuild

cd customer-service
mvn clean package
mvn jib:dockerBuild
cd ..


