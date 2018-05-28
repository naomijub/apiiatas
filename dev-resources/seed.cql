CREATE KEYSPACE IF NOT EXISTS geo WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};

USE geo;

CREATE TABLE IF NOT EXISTS cities (
    name varchar,
    iata varchar,
    country varchar,
    airlines set<varchar>,
    airports set<varchar>,
    PRIMARY KEY (iata));

CREATE INDEX IF NOT EXISTS cities_airports_idx ON geo.cities (airports);

INSERT INTO cities (name, iata, country, airlines, airports) VALUES('Santiago',  'SCL', 'CL', {'lt'}, {'SCL'});
INSERT INTO cities (name, iata, country, airlines, airports) VALUES('Sao Paulo', 'SAO', 'BR', {'lt', 'gl', 'av', 'az', 'un' }, {'CGH', 'GRU'});
INSERT INTO cities (name, iata, country, airlines, airports) VALUES('Quito',     'UIO', 'EC', {'lt', 'lx'}, {'UIO'});
INSERT INTO cities (name, iata, country, airlines, airports) VALUES('Tokyo',     'QXP', 'JP', {'jl', 'jp', 'dl', 'un'}, {'HND', 'NRT'});
