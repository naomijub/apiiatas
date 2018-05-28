(ns user
  (:require [apiiatas.schema :as schema]
            [apiiatas.server :as server]
            [com.walmartlabs.lacinia :as lacinia]))

(def schema-test (schema/load-schema (server/create-session)))

(defn q [query-string]
  (lacinia/execute schema-test query-string nil nil))
