(ns duct.module.unilog-test
  (:require
   [clojure.test :refer :all]
   [duct.core :as duct]))

(duct/load-hierarchy)

(deftest module-test
  (testing "config without options"
    (let [config {:duct.profile/base
                  {:duct.core/project-ns 'some-api}
                  :duct.profile/prod {}
                  :duct.module/unilog {}}]
      (is (= {:duct.core/project-ns 'some-api
                :duct.core/environment :production
                :duct.logger/unilog {}}
               (duct/prep-config config)))))
  (testing "config with options"
    (let [config {:duct.profile/base
                  {:duct.core/project-ns 'some-api}
                  :duct.profile/prod {}
                  :duct.module/unilog {:level :info :console true}}]
      (is (= {:duct.core/project-ns 'some-api
                :duct.core/environment :production
                :duct.logger/unilog {:level :info :console true}}
               (duct/prep-config config))))))
