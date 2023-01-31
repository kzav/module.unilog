(ns duct.module.unilog
  (:require [duct.core :as duct]
            [integrant.core :as ig]))

(defmethod ig/init-key :duct.module/unilog
  [_ options]
  (fn [config]
    (duct/merge-configs
     config
     {:duct.logger/unilog (select-keys options [:level :external :overrides :console :file :files :appenders])})))
