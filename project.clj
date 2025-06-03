(defproject jp.studist/annotation-reporter "0.2.0-alpha1"
  :description "GitHub annotation test reporter for Kaocha"
  :url "https://github.com/StudistCorporation/annotation-reporter"
  :license {:name "MIT"
            :url "https://opensource.org/license/mit/"}
  :dependencies []
  :scm {:name "git"
        :tag "v0.2.0-alpha1"}
  :deploy-repositories {"clojars" {:url "https://repo.clojars.org/"
                                   :username :env/clojars_user
                                   :password :env/clojars_token}}
  :profiles
  {:dev {:dependencies [[clj-kondo "2025.04.07"]
                        [org.clojure/clojure "1.12.1"]
                        [lambdaisland/kaocha "1.91.1392"]]
         :plugins [[lein-ancient "0.7.0"]]
         :aliases {"lint" ["run" "-m" "clj-kondo.main"
                           "--config" ".clj-kondo/config.edn"
                           "--lint" "src" "test"]
                   "test" ["run" "-m" "kaocha.runner"]}}
   :repl {:dependencies [[org.clojure/tools.namespace "1.5.0"
                          :exclusions [org.clojure/clojure]]]
          :source-paths ["dev"]}})
