(ns apiiatas.service-test
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as bootstrap]
            [apiiatas.service :as service]
            [apiiatas.util :as util]))

(deftest GQL-endpoint-funtions
  (testing "city-by-iata"
    (is (= {:data {:city_by_iata {:name "Santiago"}}}
           (util/q "{ city_by_iata(iata: \"SCL\") { name } }"))))
  (testing "city-by-airport"
    (is (= {:data {:city_by_airport {:name "Tokyo"}}}
          (util/q "{ city_by_airport(iata: \"NRT\") { name } }")))))
