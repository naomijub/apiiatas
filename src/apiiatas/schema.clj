(ns apiiatas.schema
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [com.walmartlabs.lacinia.util :as util]
            [com.walmartlabs.lacinia.schema :as schema]))

(def cities
  [{:iata "SCL"
    :airport ["SCL"]
    :airlines ["lt"]
    :country "CL"
    :name "Santiago"}
   {:iata "SAO"
    :airport ["CGH" "GRU"]
    :airlines ["lt" "gl" "av" "az" "un"]
    :country "BR"
    :name "Sao Paulo"}
   {:iata "UIO"
    :airport ["UIO"]
    :airlines ["lt" "lx"]
    :country "EC"
    :name "Quito"}
   {:iata "QXP"
    :airport ["HND" "NRT"]
    :airlines ["jl" "jp" "dl" "un"]
    :country "JP"
    :name "Tokyo"}])

(defn city-by-iata [context {iata :iata} value]
  (first (filter #(= iata (:iata %)) cities)))

(defn city-by-airport [context {airport-code :iata} value]
  (first (filter #(some #{airport-code} (:airport %)) cities)))

(defn resolver-map []
  {:query/city-by-iata city-by-iata
   :query/city-by-airport city-by-airport})

(defn schema-parser [edn]
  (-> (io/resource edn)
      (slurp)
      (edn/read-string)))

(defn load-schema []
  (-> (schema-parser "schema.edn")
      (util/attach-resolvers (resolver-map))
      (schema/compile)))

#_(user/q  "{ city_by_iata(iata: \"UIO\") { name, airlines } }")
