(ns apiiatas.schema-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [apiiatas.schema :refer [resolver-map city-by-iata]]
            [apiiatas.util :refer [every-not-empty?]]))

(deftest resolver-map-test
  (testing "should return query map"
    (is (map? (resolver-map)))
    (is (= (:query/city-by-iata (resolver-map)) city-by-iata))))

(deftest city-by-iata-testing
  (testing "should return iata info"
    (let [city (city-by-iata nil {:iata "SCL"} nil)]
      (is (map? city))
      (is (every-not-empty? #{:iata :airport :airlines :country :name} (keys city))))))
