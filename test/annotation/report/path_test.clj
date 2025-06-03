(ns annotation.report.path-test
  (:require [clojure.test :refer [deftest is testing]]
            [annotation.report.path :as path]))

(deftest relative-test
  (testing "Path relative to project root"
    (let [file "annotation/report.clj"]
      (is (= "src/annotation/report.clj" (path/relative file))))))
