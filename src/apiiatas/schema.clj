(ns apiiatas.schema
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [qbits.alia :as alia]
            [qbits.hayt :as cql]
            [com.walmartlabs.lacinia.util :as util]
            [com.walmartlabs.lacinia.schema :as schema]))

(defn city-by-iata [session context {iata :iata} _]
  (alia/execute session "USE geo;")
  (first (alia/execute session (cql/select :cities (cql/where [[= :iata iata]])))))

(defn cities-by-iatas [session context {iatas :iatas} _]
  (alia/execute session "USE geo;")
  (alia/execute session (cql/select :cities (cql/where [[:in :iata iatas]]))))

(defn city-by-airport [session context {iata :iata} _]
  (alia/execute session "USE geo;")
  (first (alia/execute session (cql/select :cities (cql/where [[:contains :airports iata]])))))

(defn resolver-map [session]
  {:query/city-by-iata (partial city-by-iata session)
   :query/cities-by-iatas (partial cities-by-iatas session)
   :query/city-by-airport (partial city-by-airport session)})

(defn schema-parser [edn]
  (-> (io/resource edn)
      (slurp)
      (edn/read-string)))

(defn load-schema [session]
  (-> (schema-parser "schema.edn")
      (util/attach-resolvers (resolver-map session))
      (schema/compile)))

#_(user/q  "{ city_by_iata(iata: \"UIO\") { name, airlines } }")
