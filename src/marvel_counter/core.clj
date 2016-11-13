(ns marvel-counter.core
  (:require [marvel-counter.fetcher :as f]
            [marvel-counter.cache :refer [write-cache load-cache]]
            [aero.core :refer (read-config resource-resolver)]
            [clojure.edn :as edn]
            [clojure.java.io :as io])
  (:gen-class))

(defn load-config []
  (let [config-source (io/resource "config.edn")]
    (read-config config-source {:resolver resource-resolver})))

(defn print-characters [characters & {:keys [print-comics]
                                      :or {print-comics false}}]
  (doseq [{:keys [name available-comics]} characters]
    (let [s (if print-comics
              (str name ", who appears in " available-comics " comics")
              name)]
      (println s))))

(defn fetch [config]
  (try 
    (let [characters (f/marvel-characters config)]
      (write-cache "characters.edn" characters)
      (println "Finished writing character cache to characters.edn")) 
    (catch Exception _
      (println "An error occured, check you have internet connection and that your public and private keys are OK"))))

(defn list-all []
  (try
    (let [characters (->> (load-cache "characters.edn")
                          (sort-by :name))]
      (print-characters characters)) 
    (catch Exception _
      (println "Check that the characters.edn file is in this directory and that it was generated correctly")  )))

(defn list-popular []
  (try
    (let [characters (->> (load-cache "characters.edn")
                          (sort-by :available-comics)
                          (reverse)
                          (take 10))]
      (print-characters characters :print-comics true)) 
    (catch Exception _
      (println "Check that the characters.edn file is in this directory and that it was generated correctly"))))

(defn print-options []
  (println "I did not understand that, you need to specify either: fetch, list-all or list-popular")) 

(defn -main [& args]
  (let [action (first args)
        config (load-config)]
    (case action
      "fetch" (fetch config)
      "list-all" (list-all)
      "list-popular" (list-popular)
      (print-options))))
