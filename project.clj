(defproject apiiatas "1.0.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [io.pedestal/pedestal.service "0.5.3"]
                 [io.pedestal/pedestal.jetty "0.5.3"]
                 [com.walmartlabs/lacinia "0.25.0"]
                 [com.walmartlabs/lacinia-pedestal "0.7.0"]
                 [cc.qbits/alia "4.2.1"]
                 [cc.qbits/hayt "4.0.1"]
                 [ch.qos.logback/logback-classic "1.1.8" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.22"]
                 [org.slf4j/jcl-over-slf4j "1.7.22"]
                 [org.slf4j/log4j-over-slf4j "1.7.22"]]
  :plugins [[com.jakemccrary/lein-test-refresh "0.22.0"]
            [lein-cljfmt "0.5.7"]
            [lein-ancient "0.6.15"]]
  :min-lein-version "2.0.0"
  :resource-paths ["config", "resources"]
  ;; If you use HTTP/2 or ALPN, use the java-agent to pull in the correct alpn-boot dependency
  ;:java-agents [[org.mortbay.jetty.alpn/jetty-alpn-agent "2.0.5"]]
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "apiiatas.server/run-dev"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.3"]]}
             :uberjar {:aot [apiiatas.server]}}
  :main ^{:skip-aot true} apiiatas.core
  :test-refresh {:growl false
                   :notify-on-success false
                   :quiet true
                   :changes-only true
                   :stack-trace-depth nil
                   :run-once false
                   :watch-dirs ["src" "test"]
                   :refresh-dirs ["src" "test"]})
