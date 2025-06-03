(ns annotation.report
  (:require [annotation.report.path :as path]
            [clojure.string :as str]
            [clojure.test :refer [with-test-out]]
            [kaocha.hierarchy :as hierarchy]
            [kaocha.output :refer [*colored-output*]]
            [kaocha.report :as report]
            [kaocha.testable :as testable]))

(defn location
  [{:keys [file line] :kaocha/keys [testable]}]
  (let [file (or (some-> testable ::testable/meta :file) file)
        line (or line (some-> testable ::testable/meta :line))
        col (or (some-> testable ::testable/meta :line) 1)]
    (str "file=" (path/relative file) ",line=" line ",row=" col)))

(defmulti annotate :type :hierarchy #'hierarchy/hierarchy)

(defmethod annotate :default [_])

;; annotation内では%0Aで改行できる
;; ref https://github.com/actions/toolkit/issues/193#issuecomment-605394935

(defn annotation-summary
  [m]
  (binding [*colored-output* false]
    (-> (report/fail-summary m)
        with-out-str
        str/trim
        (str/replace #"[\r\n]+" "%0A"))))

;; supported levels
;; ref https://github.com/actions/toolkit/blob/main/docs/commands.md#log-level

(defmethod annotate :error
  [m]
  (with-test-out
    (println (str "::error " (location m) "::" (annotation-summary m)))))

(defmethod annotate :kaocha/fail-type
  [m]
  (with-test-out
    (println (str "::warning " (location m) "::" (annotation-summary m)))))

(def ^:export reporter [annotate])
