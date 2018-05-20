(ns apiiatas.server
  (:gen-class)
  (:require [com.walmartlabs.lacinia.pedestal :as pedestal]
            [io.pedestal.http :as server]
            [io.pedestal.http.route :as route]
            [apiiatas.schema :as schema]
            [apiiatas.service :as service]))

(defonce runnable-service
  (server/create-server (pedestal/service-map (schema/load-schema) {:graphiql true})))

(defn -main [& args]
  (println "\nCreating your server...")
  (server/start runnable-service))
