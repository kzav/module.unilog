(ns duct.logger.unilog-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [duct.core :as duct]
            [duct.logger :as logger]
            [duct.logger.unilog]
            [eftest.output-capture :as outcap]
            [integrant.core :as ig]))

(duct/load-hierarchy)

(def default-config {:level   :debug
                     :console "%p %c - %m%n"})

(defn remove-cr [s] (str/replace s #"\r\n" "\n"))

(deftest key-test
  (is (isa? :duct.logger/unilog :duct/logger)))

(deftest log-level-test
  (let [config {:duct.logger/unilog default-config}
        logger (:duct.logger/unilog (ig/init config))
        output (outcap/with-test-buffer
                 (outcap/with-capture
                   (doto logger
                     (logger/debug  :test/a)
                     (logger/info   :test/b)
                     (logger/warn   :test/c)
                     (logger/error  :test/d)
                     (logger/fatal  :test/e)
                     (logger/report :test/f)))
                 (outcap/read-test-buffer))]
    (is (re-find #"DEBUG duct\.logger\.unilog-test - :test/a\n" (remove-cr output)))
    (is (re-find #"INFO duct\.logger\.unilog-test - :test/b\n" (remove-cr output)))
    (is (re-find #"WARN duct\.logger\.unilog-test - :test/c\n" (remove-cr output)))
    (is (re-find #"ERROR duct\.logger\.unilog-test - :test/d\n" (remove-cr output)))
    (is (re-find #"ERROR duct\.logger\.unilog-test - :test/e\n" (remove-cr output)))
    (is (re-find #"INFO duct\.logger\.unilog-test - :test/f\n" (remove-cr output)))))

(deftest log-data-test
  (let [config {:duct.logger/unilog default-config}
        logger (:duct.logger/unilog (ig/init config))
        output (outcap/with-test-buffer
                 (outcap/with-capture
                   (logger/info logger :test/a {:other "info"}))
                 (outcap/read-test-buffer))]
    (is (re-find #"INFO duct\.logger\.unilog-test - :test/a \{:other \"info\"\}\n" (remove-cr output)))))

(deftest log-exception-test
  (let [config {:duct.logger/unilog default-config}
        logger (:duct.logger/unilog (ig/init config))
        output (outcap/with-test-buffer
                 (outcap/with-capture
                   (logger/warn logger :test/ex (Exception. "a"))
                   (logger/error logger (Exception. "b")))
                 (outcap/read-test-buffer))]
    (is (re-find
          #"WARN duct\.logger\.unilog-test - :test/ex\njava\.lang\.Exception: a\n"
          (remove-cr output)))
    (is (re-find
          #"ERROR duct\.logger\.unilog-test - \njava\.lang\.Exception: b\n"
          (remove-cr output)))
    (is (re-find #"at duct\.logger\.unilog_test" (remove-cr output)))))
