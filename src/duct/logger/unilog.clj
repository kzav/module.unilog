(ns duct.logger.unilog
  (:require [clojure.tools.logging :as log]
            [clojure.tools.logging.impl :as impl]
            [duct.logger :as logger]
            [integrant.core :as ig]
            [unilog.config :as unilog]))

(defrecord Logger
  []
  logger/Logger
  (-log [_ level ns _ _ _ event data]
    (let [level  (if (= level :report) :info level)
          logger (impl/get-logger log/*logger-factory* ns)]
      (cond
        (instance? Throwable event)
        (log/log* logger level event nil)
        (instance? Throwable data)
        (log/log* logger level data (pr-str event))
        (nil? data)
        (log/log* logger level nil (pr-str event))
        :else
        (log/log* logger level nil (pr-str event data))))))

(defmethod ig/init-key :duct.logger/unilog
  [_ config]
  (unilog/start-logging! config)
  (->Logger))
