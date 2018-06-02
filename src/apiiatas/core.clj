(ns apiiatas.core
  (:gen-class)
  (:require [com.walmartlabs.lacinia.pedestal :as pedestal]
            [io.pedestal.http :as server]
            [io.pedestal.http.route :as route]
            [qbits.alia :as alia]
            [apiiatas.schema :as schema]))

(defn create-session []
  (let [cluster (alia/cluster {:contact-points ["localhost"]})]
    (alia/connect cluster)))

(defn start-service [service]
  (server/start
   (server/create-server service)))

(defn build-service [session]
  (pedestal/service-map
   (schema/load-schema session)
   {:graphiql true}))

(defn -main [& args]
  (println "\nCreating your server...")
  (let [session (create-session)]
    (alia/execute session "USE geo;")
    (start-service (build-service session))))
