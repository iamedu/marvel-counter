(ns marvel-counter.fetcher
  (:require [org.httpkit.client :as http]
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

