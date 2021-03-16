(ns lt-ui.core
  (:require [reagent.core]
            [garden.core :refer [css]]
            [lt-ui.typography :as typography]
            [lt-ui.containers :as containers]
            [lt-ui.inputs :as inputs]
            [lt-ui.forms :as forms]))

(defn theme-rules [theme]
  (concat [[:* {:box-sizing :border-box}]]
          (typography/rules theme)
          (containers/rules theme)))

(defn themed-stylesheet [theme]
  [:style (css (theme-rules theme))])

