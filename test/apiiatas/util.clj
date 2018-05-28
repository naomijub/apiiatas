(ns apiiatas.util
  (:require [apiiatas.schema :as schema]
            [com.walmartlabs.lacinia :as lacinia]))

#_(def schema-util (schema/load-schema))

#_(defn q [query-string]
    (lacinia/execute schema-util query-string nil nil))

(defn every-not-empty? [pred s2]
  (and (not (empty? s2))
       (every? pred s2)))
