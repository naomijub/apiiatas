run-cassandra:
	docker run -it --rm --name graphql-cassandra -p 9042:9042 -v "$(PWD)/dev-resources/seed.cql:/seed.cql" cassandra:3.11.1

seed-cassandra:
	docker exec -t graphql-cassandra cqlsh --debug -f /seed.cql
