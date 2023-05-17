docker-compose exec kafka bash -c "
  kafka-console-producer \
  --bootstrap-server kafka:9092 \
  --topic customers \
  --property 'parse.key=true' \
  --property 'key.separator=|' < customers.json"