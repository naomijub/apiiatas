(ns user
  (:require [apiiatas.schema :as schema]
            [apiiatas.core :as core]
            [com.walmartlabs.lacinia :as lacinia]))

(def schema-test (schema/load-schema (core/create-session)))

(defn q [query-string]
  (lacinia/execute schema-test query-string nil nil))
