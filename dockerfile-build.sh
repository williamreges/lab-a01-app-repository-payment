NAME_IMAGE=lab-a01-app-repository-payment

./mvnw clean install -Dtest.Skip=true

echo "=== Build Image Version ===="
docker build -t ${NAME_IMAGE} .

