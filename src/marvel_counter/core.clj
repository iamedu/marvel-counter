(ns marvel-counter.core
  (:require [aero.core :refer  (read-config resource-resolver)]
            [clojure.java.io :as io])
  (:gen-class))

(defn load-config []
  (let [config-source (io/resource "config.edn")]
    (read-config config-source {:resolver resource-resolver})))

(defn -main
  "I can do a few things"
  [& args]
  (println "Hello, World!"))
