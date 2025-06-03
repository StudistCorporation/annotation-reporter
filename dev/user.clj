#_{:clj-kondo/ignore [:unused-namespace :unused-referred-var]}
(ns user
  (:require [clojure.tools.namespace.repl :refer [refresh set-refresh-dirs]]
            [kaocha.repl :as test]
            [clojure.repl :refer [source doc]]
            [clojure.pprint :refer [pprint]]))

(set-refresh-dirs "env/dev" "src" "test")

(defn run-tests
  ([] (test/run-all))
  ([sym] (test/run sym)))
