(ns lt-ui.core
  (:require [reagent.core]
            [garden.core :refer [css]]
            [lt-ui.typography :as typography]
            [lt-ui.containers :as containers]
            [lt-ui.devcards :as devcards]
            [lt-ui.inputs]
            [lt-ui.forms]))

(defn theme-rules [theme]
  (concat [[:* {:box-sizing :border-box}]]
          (typography/rules theme)
          (containers/rules theme)))

(defn init []
  (devcards/init!)
  (reset! devcards/styles (css (theme-rules {:font-size 16
                                             :line-height 1.4
                                             :scale-factor 1.5}))))