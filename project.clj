;; Only used for publishing JARs -- use shadow-cljs to build clojurescript
(defproject lt/ulti "1.0.0-SNAPSHOT"
  :description "an experimental web UI framework"
  :url "https://git.sr.ht/~luketurner/ulti"
  :license {:name "MIT License"}

  :dependencies
  [[org.clojure/clojurescript "1.10.520" :scope "provided"]
   [garden "1.3.10"]
   [reagent "1.0.0"]
   [medley "1.3.0"]
   [metosin/malli "0.3.0"]]
    
  :deploy-repositories [["local" {:url "file:///~/.m2"
                                  :sign-releases false}]
                        ["clojars" {:sign-releases false
                                    :username :env/clojars_username
                                    :password :env/clojars_password}]]

  :source-paths
  ["src/main"])