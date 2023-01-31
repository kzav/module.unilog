(defproject ten-dimensions/module.unilog "0.1.0"
  :description "Integrant methods for the unilog library"
  :url "https://github.com/kzav/logger.unilog"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/tools.logging "1.2.4"]
                 [duct/core "0.8.0"]
                 [duct/logger "0.3.0"]
                 [integrant "0.8.0"]
                 [spootnik/unilog "0.7.31"]]
  :profiles
  {:dev {:dependencies [[eftest "0.5.9"]]}}
  :repl-options {:init-ns duct.logger.unilog}
  :plugins [[lein-ancient "0.6.14"]])
