(ns apiiatas.util)

(defn every-not-empty? [pred s2]
  (and (not (empty? s2))
       (every? pred s2)))
