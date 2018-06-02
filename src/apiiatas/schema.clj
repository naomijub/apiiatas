(ns apiiatas.schema
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [com.walmartlabs.lacinia.util :as util]
            [com.walmartlabs.lacinia.schema :as schema]
            [apiiatas.resolvers :refer [resolver-map]]))

(defn schema-parser [edn]
  (-> (io/resource edn)
      (slurp)
      (edn/read-string)))

(defn load-schema [session]
  (-> (schema-parser "schema.edn")
      (util/attach-resolvers (resolver-map session))
      (schema/compile)))
