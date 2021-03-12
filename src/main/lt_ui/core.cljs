(ns lt-ui.core
  (:require [reagent.core]
            [garden.core :refer [css]]
            [lt-ui.typography :as typography]
            [lt-ui.devcards :as devcards]))

(defn theme-rules [theme]
  (concat []
          (typography/rules theme)))

(defn init []
  (devcards/init!)
  (reset! devcards/styles (css (theme-rules {:font-size 16
                                             :line-height 1.4
                                             :scale-factor 1.5}))))