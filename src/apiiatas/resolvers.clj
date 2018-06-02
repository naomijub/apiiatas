(ns apiiatas.resolvers
  (:require [qbits.alia :as alia]
            [qbits.hayt :as cql]))

(defn city-by-iata [session context {iata :iata} _]
  (first (alia/execute session
            (cql/select :cities (cql/where [[= :iata iata]])))))

(defn cities-by-iatas [session context {iatas :iatas} _]
  (alia/execute session
            (cql/select :cities (cql/where [[:in :iata iatas]]))))

(defn city-by-airport [session context {iata :iata} _]
  (first (alia/execute session
            (cql/select :cities (cql/where [[:contains :airports iata]])))))

(defn resolver-map [session]
  {:query/city-by-iata (partial city-by-iata session)
   :query/cities-by-iatas (partial cities-by-iatas session)
   :query/city-by-airport (partial city-by-airport session)})
