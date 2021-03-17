;; Only used for publishing JARs -- use shadow-cljs to build clojurescript
(defproject lt-ui "0.0.1"
  :description "an experimental web UI framework"
  :url "https://git.sr.ht/~luketurner/lt-ui"
  :license {:name "MIT License"}

  :dependencies
  [[org.clojure/clojurescript "1.10.520" :scope "provided"]
   [garden "1.3.10"]
   [reagent "1.0.0"]]
    
  :deploy-repositories [["local" {:url "file:///~/.m2"
                                  :sign-releases false}]]

  :source-paths
  ["src/main"])