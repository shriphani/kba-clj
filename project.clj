(defproject kba_clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[byte-streams "0.1.9"]
                 [ch.qos.logback/logback-classic "1.0.13"]
                 [org.bovinegenius/exploding-fish "0.3.4"]
                 [org.clojure/clojure "1.5.1"]
                 [thrift-clj "0.2.1"]]
  :java-source-paths ["src/java"]
  :main kba-clj.core)
