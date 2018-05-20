(ns apiiatas.schema-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [apiiatas.schema :refer [resolver-map city-by-iata schema-parser load-schema]]
            [apiiatas.util :refer [every-not-empty?]]))

(deftest resolver-map-test
  (testing "should return query map"
    (is (map? (resolver-map)))
    (is (= (:query/city-by-iata (resolver-map)) city-by-iata))))

(deftest city-by-iata-testing
  (testing "should return iata info"
    (let [city (city-by-iata nil {:iata "SCL"} nil)]
      (is (map? city))
      (is (= (:name city) "Santiago"))
      (is (every-not-empty? #{:iata :airport :airlines :country :name} (keys city))))
    (let [city (city-by-iata nil {:iata "QXP"} nil)]
      (is (= (:name city) "Tokyo")))))

(deftest load-schema-test
  (testing "schema-parser: schema.edn should be read as clj"
    (is (map? (schema-parser "schema.edn")))
    (is (every-not-empty? #{:objects :queries} (keys (schema-parser "schema.edn")))))
  (testing "load-schema: returns lacinia GraphQL Schema"
    (is (map? (load-schema)))
    (is (every-not-empty?
          (load-schema)
          #{:ID :City :SubscriptionRoot :MutationRoot :QueryRoot}))))
