(ns marvel-counter.cache
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

(defn write-cache [filename characters]
  (spit filename (pr-str characters)))

(defn load-cache [filename]
  (edn/read-string (slurp filename)))

