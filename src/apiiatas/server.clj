(ns apiiatas.server
  (:gen-class)
  (:require [com.walmartlabs.lacinia.pedestal :as pedestal]
            [io.pedestal.http :as server]
            [io.pedestal.http.route :as route]
            [qbits.alia :as alia]
            [qbits.hayt :as cql]
            [apiiatas.schema :as schema]))

(defn create-session []
  (let [cluster (alia/cluster {:contact-points ["localhost"]})]
    (alia/connect cluster)))

(defn -main [& args]
  (println "\nCreating your server...")
  (let [session (create-session)
        runnable-service (server/create-server (pedestal/service-map (schema/load-schema session) {:graphiql true}))]
    (server/start runnable-service)))
