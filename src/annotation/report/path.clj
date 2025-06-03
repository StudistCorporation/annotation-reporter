(ns annotation.report.path
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:import [java.nio.file
            Path]))

(defn try-git-root
  []
  (try
    (let [command ^String/1 (into-array String ["git" "rev-parse" "--show-toplevel"])
          process (.exec (Runtime/getRuntime) command)]
      (.waitFor process)
      (-> (.getInputStream process)
          slurp
          str/trim
          io/file
          .toPath))
    (catch Throwable _)))

(defn process-root
  []
  (Path/of (System/getProperty "user.dir") ^String/1 (into-array String [])))

(defn relative
  [file]
  (let [root (or (try-git-root) (process-root))]
    (->> file
         io/resource
         io/file
         .toPath
         (.relativize root))))
