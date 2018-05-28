(ns apiiatas.schema-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [apiiatas.schema :refer [resolver-map city-by-iata city-by-airport
                                              schema-parser load-schema]]
            [apiiatas.util :refer [every-not-empty?]]))

#_(deftest resolver-map-test
    (testing "should return query map"
      (is (map? (resolver-map)))
      (is (= (:query/city-by-iata (resolver-map)) city-by-iata))
      (is (= (:query/city-by-airport (resolver-map)) city-by-airport))))

#_(deftest city-by-iata-testing
    (testing "should return iata info"
      (let [city (city-by-iata nil {:iata "SCL"} nil)]
        (is (map? city))
        (is (= (:name city) "Santiago"))
        (is (every-not-empty? #{:iata :airport :airlines :country :name} (keys city))))
      (let [city (city-by-iata nil {:iata "QXP"} nil)]
        (is (= (:name city) "Tokyo")))))

#_(deftest city-by-airport-testing
    (testing "should return airport city info"
      (let [city (city-by-airport nil {:iata "GRU"} nil)]
        (is (map? city))
        (is (= (:name city) "Sao Paulo"))
        (is (every-not-empty? #{:iata :airport :airlines :country :name} (keys city))))
      (let [city (city-by-airport nil {:iata "NRT"} nil)]
        (is (= (:name city) "Tokyo")))))

#_(deftest load-schema-test
    (testing "schema-parser: schema.edn should be read as clj"
      (is (map? (schema-parser "schema.edn")))
      (is (every-not-empty? #{:objects :queries} (keys (schema-parser "schema.edn"))))))
