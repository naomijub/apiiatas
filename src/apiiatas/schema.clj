(ns apiiatas.schema)

(def cities
  [{:iata "SCL"
    :airport ["SCL"]
    :airlines ["lt"]
    :country "CL"
    :name "Santiago"}
   {:iata "SAO"
    :airport ["CGH" "GRU"]
    :airlines ["lt" "gl" "av" "az" "un"]
    :country "BR"
    :name "Sao Paulo"}
   {:iata "UIO"
    :airport ["UIO"]
    :airlines ["lt" "lx"]
    :country "EC"
    :name "Quito"}
   {:iata "QXP"
    :airport ["HND" "NRT"]
    :airlines ["jl" "jp" "dl" "un"]
    :country "JP"
    :name "Tokyo"}])

(defn city-by-iata [context {iata :iata} value]
  (first (filter #(= iata (:iata %)) cities)))

(defn resolver-map []
  {:query/city-by-iata city-by-iata})
