(ns apiiatas.schema-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [apiiatas.schema :refer [resolver-map city-by-iata]]))

(deftest resolver-map-test
  (testing "should return query map"
    (is (map? (resolver-map)))
    (is (= (:query/city-by-iata (resolver-map)) city-by-iata))))
