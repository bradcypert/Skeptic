(defproject app "0.0.1"
  :description "Given-When-Then style testing in Clojure"
  :url "https://github.com/bradcypert/Skeptic"
  :dependencies [[org.clojure/clojure "1.9.0"]]

  :min-lein-version "2.0.0"
  :plugins [[lein-ancient "0.6.14"]
            [test2junit "1.3.3"]
            [jonase/eastwood "0.2.4"]
            [lein-kibit "0.1.5"]])
