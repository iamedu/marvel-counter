(ns marvel-counter.fetcher
  (:require [org.httpkit.client :as http]
            [clojure.data.json :as json]
            [digest :refer [md5]]))

(defn auth-params [{:keys [public-key private-key]}]
  (let [ts (System/currentTimeMillis)
        origin-string (str ts private-key public-key)]
    {:hash (md5 origin-string)
     :ts ts
     :apikey public-key}))


(defn fetch-marvel-resource [config url & {:as params}]
  (let [query-params (merge (auth-params config)
                            params)
        options {:query-params query-params}
        url (str "http://gateway.marvel.com/v1/public/" url)]
    (http/get url options)))

(defn marvel-characters [config]
  (let [character-list (atom [])
        limit 100]
    (loop [offset 0] 
      (let [resp @(fetch-marvel-resource config "characters"
                                         :offset offset
                                         :limit limit
                                         :orderBy "name")
            {:keys [count results]} (-> resp
                                        :body
                                        (json/read-str :key-fn keyword)
                                        :data) 
            character-data (for [{:keys [name comics]} results]
                             {:name name
                              :available-comics (:available comics)})]
        (swap! character-list concat character-data)
        (if (> count 0)
          (recur (+ offset limit)))))
    @character-list))

