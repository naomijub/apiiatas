(ns user
  (:require [apiiatas.schema :as schema]
            [com.walmartlabs.lacinia :as lacinia]))

(def schema-test (schema/load-schema))

(defn q [query-string]
  (lacinia/execute schema-test query-string nil nil))
